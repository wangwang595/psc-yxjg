package com.wavenet.maintenance.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wavenet.maintenance.dao.entity.*;
import com.wavenet.maintenance.dao.mapper.*;
import com.wavenet.maintenance.service.PatrolInfoService;
import com.wavenet.maintenance.web.query.PatrolInfoObjectQuerParam;
import com.wavenet.maintenance.web.query.PatrolInfoQueryParam;
import com.wavenet.maintenance.web.query.QueryParameterObjectPage;
import com.wavenet.maintenance.web.query.QueryParameterQueryPage;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
@Slf4j
@Service
public class PatrolInfoServiceImpl extends BaseServiceImpl<PatrolInfoMapper, PatrolInfo> implements PatrolInfoService {

    @Autowired
    private PatrolInfoMapper patrolInfoMapper;

    @Autowired
    private EventInfoMapper eventInfoMapper;

    @Autowired
    private EventDispatchMapper mapper;

    @Autowired
    private EventDispatchRelMapper relMapper;

    @Resource
    private RoadWatchMapper roadWatchMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String savePatrolInfo(PatrolInfo patrolInfo) throws Exception {
        if (super.save(patrolInfo)) {
            return patrolInfo.getPatrolCode();
        } else {
            return null;
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PushResult updatePatrolInfo(PatrolInfo patrolInfo) throws Exception {
      //  JPushClient client = new JPushClient("f2701e28264cddd73dede39b", "15c755b4845ffd3f6adfffec");
        patrolInfoMapper.updateById(patrolInfo);
        //点击'结束'后计算平均速度
        //根据patrolCode查询信息
//        PatrolInfo list = patrolInfoMapper.selectById(patrolInfo.getPatrolCode());
//        String code = list.getPatrolCode();
//        Date start = list.getDateStart();
//        //对时间格式进行处理
//        Date end = list.getDateEnd();
//        Double mileage = list.getMileage();
//        if (list.getDateEnd() != null&&mileage!=null) {
//            //日期相减得出毫秒级
//            long diff = end.getTime() - start.getTime();
//            //得出分钟
//            long min = diff / 1000 / 60;
//            double km = mileage / 1000;
//            double avg = km / min * 60;
//            //求出平均速度
//            String stringAvg = String.valueOf(avg);
//            //如果结果为NaN或者Infinity 直接赋0
//            if (stringAvg.equals("NaN") || stringAvg.equals("Infinity")) {
//                stringAvg = "0";
//            }
//            roadWatchMapper.updatePatrolSpeed(stringAvg, code);
//            //算出的平均速度只保留5位
//            if (stringAvg.length() > 5) {
//                String speed = stringAvg.substring(0, 4);
//                roadWatchMapper.updatePatrolSpeed(speed, code);
//            }
//
//        } else {
//            roadWatchMapper.updatePatrolSpeed("0", code);
//        }
//
//        if (patrolInfo.getState() != null && patrolInfo.getState().equals("已完成")) {
//            List<EventInfoQueryVo> eventInfoList = eventInfoMapper.selectByPatrolCode(patrolInfo.getPatrolCode());
//            if (eventInfoList.size() > 0) {
//                for (int i = 0; i < eventInfoList.size(); i++) {
//                    if (eventInfoList.get(i).getEventStandard().equals("1")) {
//                        String dispatchCode = UUID.randomUUID().toString();
//                        String eventCode = eventInfoList.get(i).getEventCode();
//                        EventDispatch eventDispatch = new EventDispatch();
//                        eventDispatch.setDispatchCode(dispatchCode);
//                        eventDispatch.setDispatcherDate(new Date());
//                        eventDispatch.setTown(eventInfoList.get(i).getTown());
//                        eventDispatch.setCompany(eventInfoList.get(i).getCompany());
//                        eventDispatch.setType(eventInfoList.get(i).getEventType());
//                        eventDispatch.setRoadCode(eventInfoList.get(i).getRoadCode());
//                        eventDispatch.setRoadName(eventInfoList.get(i).getRoadName());
//                        eventDispatch.setRoadSectionCode(eventInfoList.get(i).getRoadSectionCode());
//                        eventDispatch.setRoadSectionStart(eventInfoList.get(i).getRoadSectionStart());
//                        eventDispatch.setRoadSectionEnd(eventInfoList.get(i).getRoadSectionEnd());
//                        eventDispatch.setDispatchState("已派遣");
//                        eventDispatch.setProjectTeam(eventInfoList.get(i).getProjectTeam());
//                        eventDispatch.setEventType(eventInfoList.get(i).getType());
//                        eventDispatch.setEventNum(1);
//                      //  mapper.insert(eventDispatch);
//                        //建立关联关系
//                        EventDispatchRel rel = new EventDispatchRel();
//                        rel.setEventCode(eventCode);
//                        rel.setDispatchCode(dispatchCode);
//                      //  relMapper.insert(rel);
//
//                    }
//                }
//                PushPayload.Builder pushPayload = PushPayload.newBuilder()
//                        //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
//                       .setPlatform(Platform.all());
//                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
//                if (eventInfoList.get(0).getType().equals("路面")) {
//                    //pushPayload.setAudience(Audience.all());
//                    pushPayload.setAudience(Audience.tag("路面"));
//                }
//                if (eventInfoList.get(0).getType().equals("桥梁")) {
//                    pushPayload.setAudience(Audience.tag("桥梁"));
//                }
//                if (eventInfoList.get(0).getType().equals("护栏")) {
//                    pushPayload.setAudience(Audience.tag("护栏"));
//                }
//
//
//                pushPayload.setNotification(Notification.alert("您有一条新消息，请尽快处理"))
//                        .setNotification(Notification.android("请尽快处理", "你有一条新消息", null))
//                        .setMessage(Message.content("你好"))
//                        .setOptions(Options.newBuilder().setApnsProduction(true).build());
//                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
//                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
//                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
//                try {
//                    PushResult pushResult = client.sendPush(pushPayload.build());
//                    System.out.println(pushResult);
//                    return  pushResult;
//                } catch (APIConnectionException e) {
//                    e.printStackTrace();
//                } catch (APIRequestException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }

        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deletePatrolInfo(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public PatrolInfoQueryVo getPatrolInfoById(Serializable id) throws Exception {
        return patrolInfoMapper.getPatrolInfoById(id);
    }

    @Override
    public Paging<PatrolInfoQueryVo> getPatrolInfoPageList(PatrolInfoQueryParam patrolInfoQueryParam) throws Exception {
        Page page = setPageParam(patrolInfoQueryParam, OrderItem.desc("date_start"));
        IPage<PatrolInfoQueryVo> iPage = patrolInfoMapper.getPatrolInfoPageList(page, patrolInfoQueryParam);
        return new Paging(iPage);
    }

    @Override
    public ApiResult<Integer> updateMileageByPatrolCode(String patrolCode, String realMileage) {
        int i = patrolInfoMapper.updateMileageByPatrolCode(patrolCode, realMileage);
        return ApiResult.ok(i);
    }

    @Override
    public boolean updateByPatrolCode(PatrolInfo patrolInfo) {

        return super.updateById(patrolInfo);
    }

    @Override
    public PatrolInfoAndEventInfoQueryVo selectInfoByPatrolCode(String patrolCode) {
        PatrolInfoAndEventInfoQueryVo data = new PatrolInfoAndEventInfoQueryVo();
        PatrolInfo patrolInfo = patrolInfoMapper.selectById(patrolCode);
        data.setPatrolInfoQueryVo(patrolInfo);
        List<EventInfoQueryVo> eventInfoQueryVos = eventInfoMapper.selectByPatrolCode(patrolCode);
        data.setEventInfoQueryVos(eventInfoQueryVos);
        return data;
    }


    @Override
    public MileagesFreVOs pAcquiredQuantity(PatrolInfoObjectQuerParam patrolInfoObjectQuerParam) throws ParseException {
      //  String date = patrolInfoObjectQuerParam.getDate();
        //根据时间 确定时间
        List<String> getdate = getdate(patrolInfoObjectQuerParam.getDate());

        //获取里程数 次数
        String startDate = getdate.get(0);
        String endDate = getdate.get(1);
        List<String> list=new ArrayList<>();
        String project = patrolInfoObjectQuerParam.getProject();
        if (project != null) {
            project = "[" + project + "]";
            //json转换为list
            Gson gson = new Gson();
            list = gson.fromJson(project, new TypeToken<List<String>>() {
            }.getType());
            if (project.equals("[杨浦市政养护建设工程有限公司]")){
                list=new ArrayList<>();
            }
        }
        MileagesFreVOs mileage =eventInfoMapper.getMileage(startDate,list,endDate);
        //获取问题数
        Integer questions = eventInfoMapper.getQuestions(startDate, list,endDate);
        mileage.setQuerys(questions);
        Integer integers=eventInfoMapper.getTotalReview();
        mileage.setReview(integers);
        return mileage;
    }

    @Override
    public PageInfo<PatrolInfoS> listInformation(QueryParameterObjectPage queryParameter) throws ParseException {
        PageHelper.startPage(queryParameter.getFindArticleDto().getPage(), queryParameter.getFindArticleDto().getPageSize(),"");
        String date = queryParameter.getDate();
        List<Date> getdate = getdates(date);
        Date startDate = getdate.get(0);
        Date endDate = getdate.get(1);
        String project = queryParameter.getProject();
        String type = queryParameter.getType();
        String twon = queryParameter.getTwon();
        List<String > projectList=new ArrayList<>();
        if (project != null&&!(project.equals(""))) {


        project = "["+project+"]";
        //json转换为list
        Gson gson = new Gson();
            projectList = gson.fromJson(project, new TypeToken<List<String>>(){}.getType());
            if (project.equals("[杨浦市政养护建设工程有限公司]")){
                projectList=new ArrayList<>();
            }
        }
        List<PatrolInfoS> patrolInfos1= eventInfoMapper.listInformation(projectList,twon,type,endDate,startDate);
        for (PatrolInfoS patrolInfoS : patrolInfos1) {
            String patrolCode = patrolInfoS.getPatrolCode();
            Integer data =0;
            if (patrolCode!=null) {
            if (!(patrolCode.equals(""))) {
                    data = patrolInfoMapper.getNumberQuestions(patrolCode);
                }
            }
            patrolInfoS.setQuerys(data);
        }
        PageHelper.clearPage();
        PageInfo<PatrolInfoS> pageInfo=new PageInfo<>(patrolInfos1);
        return pageInfo;
    }

    public  List<Date> getdates(String date) throws ParseException {
        //Date date1 = new Date();
        String month1 = "";
        String day2 = "";
        String year2 = "";

        // Calendar instance = Calendar.getInstance();
        //instance.set(Calendar.YEAR);
        String[] split = date.split("-");
//        Integer year=instance.get(Calendar.YEAR);
//        Integer month=instance.get(Calendar.MONTH);
//        Integer day=instance.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> dates = new ArrayList<>();
        Date startDate = new Date();
        Date endDate = new Date();
        String startTime = "";
        String endTime = "";
        if (split.length == 3) {
            year2 = split[0];
            day2 = split[2];
            month1 = split[1];
            startTime = year2 + "-" + month1 + "-" + day2 + " 00:00:00";
            endTime = year2 + "-" + month1 + "-" + day2 + " 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
        }
        if (split.length == 2) {
            year2 = split[0];
            month1 = split[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Date dd = format.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd);
            Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            String day = cc.toString();
            startTime = year2 + "-" + month1 + "-01 00:00:00";
            // 日期问题
            endTime = year2 + "-" + month1 + "-" + day + " 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
        }
        if (split.length == 1) {
            year2 = split[0];
            // 01 12
            startTime = year2 + "-" + "01-01 00:00:00";
            endTime = year2 + "-" + "12-31 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
            //01 12
        }
        dates.add(startDate);
        dates.add(endDate);
        return dates;
    }

    @Override
    public PageInfo<EventInfo> listInformationQuer(QueryParameterQueryPage queryParameter) throws ParseException {
        PageHelper.startPage(queryParameter.getFindArticleDto().getPage(), queryParameter.getFindArticleDto().getPageSize(),"");
        String date = queryParameter.getDate();
        List<Date> getdate = getdates(date);
        Date startDate = getdate.get(0);
        Date endDate = getdate.get(1);
        String project = queryParameter.getProject();
        String type = queryParameter.getType();
        String twon = queryParameter.getTwon();
        String status = queryParameter.getStatus();
        List<String > projectList=new ArrayList<>();
        if (project != null&&!(project.equals(""))) {


        project = "["+project+"]";
        //json转换为list
        Gson gson = new Gson();
            projectList  = gson.fromJson(project, new TypeToken<List<String>>(){}.getType());
            if (project.equals("[杨浦市政养护建设工程有限公司]")){
                projectList=new ArrayList<>();
            }
        }
        List<EventInfo> eventInfos=eventInfoMapper.listInformationQuer(startDate,endDate,projectList,type,twon,status);
        PageHelper.clearPage();
        PageInfo<EventInfo> pageInfo=new PageInfo<>(eventInfos);

        return pageInfo;
    }

    @Override
    public PatrolInfoS getInfoById(String id) {

        PatrolInfoS infoById = patrolInfoMapper.getInfoById(id);
        String patrolCode = infoById.getPatrolCode();
        Integer numberQuestions = patrolInfoMapper.getNumberQuestions(patrolCode);
        infoById.setQuerys(numberQuestions);
        return infoById;
    }

    @Override
    public PratolInfooCountListVo statisticCount(String times,String type,String district) throws ParseException {
        PratolInfooCountListVo pratolInfooCountListVo = new PratolInfooCountListVo();
        List<Date> getdates = getdates(times);
        Date startDate = getdates.get(0);
        Date  endDate= getdates.get(1);

        List<PratolInfoVo>  pratolInfoVos=new ArrayList<PratolInfoVo>();
        // 获取 里程数 次数 问题数
        PratolInfoCountVo integers=new PratolInfoCountVo();
        if (("1").equals(type)){
          pratolInfoVos= patrolInfoMapper.statisticCount(startDate,endDate);
            // 获取 里程数 次数 问题数
            integers=patrolInfoMapper.getCount(startDate,endDate);
        }else {
            pratolInfoVos= patrolInfoMapper.statisticCountDistrict(startDate,endDate,district);
            // 获取 里程数 次数 问题数
            integers=patrolInfoMapper.getCountDistrict(startDate,endDate,district);
        }
        pratolInfooCountListVo.setPratolInfoVos(pratolInfoVos);
        pratolInfooCountListVo.setPratolInfoCountVo(integers);
        return pratolInfooCountListVo;
    }


    public  List<String> getdate(String date) throws ParseException {
        String month1="";
        String day2="";
        String year2="";
        String[] split = date.split("-");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       List<String> dates = new ArrayList<>();
        Date startDate = new Date();
        Date endDate = new Date();
        String startTime="";
        String endTime="";
        if(split.length==3){
          year2  =split[0];
           day2  =split[2];
           month1  =split[1];
            startTime=year2+"-"+month1+"-"+day2+" 00:00:00";
             endTime=year2+"-"+month1+"-"+day2+" 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
       }
        if(split.length==2){
            year2=split[0];
            month1=split[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Date dd = format.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd);
            Integer cc=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            String day = cc.toString();
           startTime=year2+"-"+month1+"-01 00:00:00";
            // 日期问题
            endTime=year2+"-"+month1+"-"+day+" 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
        }
        if(split.length==1){
            year2=split[0];
            // 01 12
          startTime=year2+"-"+"01-01 00:00:00";
           endTime=year2+"-"+"12-31 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
            //01 12
        }
        dates.add(startTime);
        dates.add(endTime);
        return dates;

    }

}
