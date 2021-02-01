package com.wavenet.maintenance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.AddEventInfoAndEventDispatch;
import com.wavenet.maintenance.dao.entity.TWelloperation;
import com.wavenet.maintenance.dao.mapper.EmergencyUserStateMapper;
import com.wavenet.maintenance.dao.mapper.TWelloperationMapper;
import com.wavenet.maintenance.service.EventInfoService;
import com.wavenet.maintenance.service.TWelloperationService;
import com.wavenet.maintenance.web.query.TWelloperationCountQueryVo;
import com.wavenet.maintenance.web.query.TWelloperationQueryParamS;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

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
 * @since 2020-08-12
 */
@Slf4j
@Service
public class TWelloperationServiceImpl extends BaseServiceImpl<TWelloperationMapper, TWelloperation> implements TWelloperationService {

    @Autowired
    private TWelloperationMapper tWelloperationMapper;

    @Autowired
    private EmergencyUserStateMapper emergencyUserStateMapper;

    @Autowired
    private EventInfoService eventInfoService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveTWelloperation(TWelloperation tWelloperation) throws Exception {
        String replace = UUID.randomUUID().toString().replace("-", "");
        String wellstate = tWelloperation.getWellstate();
        String result="";

        if(("异常").equals(wellstate)){
            AddEventInfoAndEventDispatch addEventInfoAndEventDispatch = new AddEventInfoAndEventDispatch();
            addEventInfoAndEventDispatch.setEventCode(replace);
            addEventInfoAndEventDispatch.setAddress(tWelloperation.getAddr());
            addEventInfoAndEventDispatch.setEventType(tWelloperation.getWelltype());
            addEventInfoAndEventDispatch.setX(Double.parseDouble(tWelloperation.getX()));
            addEventInfoAndEventDispatch.setY(Double.parseDouble(tWelloperation.getY()));

            addEventInfoAndEventDispatch.setPersonCode(tWelloperation.getManId());
            addEventInfoAndEventDispatch.setPersonName(tWelloperation.getManName());
            addEventInfoAndEventDispatch.setRoadSectionStart(tWelloperation.getRoadStart());
            addEventInfoAndEventDispatch.setRoadSectionEnd(tWelloperation.getRoadEnd());
            addEventInfoAndEventDispatch.setProjectTeam(tWelloperation.getProjectTeam());
            addEventInfoAndEventDispatch.setTown(tWelloperation.getTownid());
            addEventInfoAndEventDispatch.setType(tWelloperation.getWelltype());
            addEventInfoAndEventDispatch.setSource("检查");
            addEventInfoAndEventDispatch.setEventStandard("1");
            addEventInfoAndEventDispatch.setBigCategory(tWelloperation.getWelltype());
            addEventInfoAndEventDispatch.setAttachment(tWelloperation.getEnclosure());
            addEventInfoAndEventDispatch.setReportDate(tWelloperation.getUpdatetime());
            addEventInfoAndEventDispatch.setCompany(tWelloperation.getComName());
            result = eventInfoService.saveEventInfoNew(addEventInfoAndEventDispatch);
        }
        tWelloperation.setMangeId(replace);
        super.save(tWelloperation);
        return result ;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTWelloperation(TWelloperation tWelloperation) throws Exception {
        return super.updateById(tWelloperation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTWelloperation(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public TWelloperationQueryVo getTWelloperationById(Serializable id) throws Exception {
        return tWelloperationMapper.getTWelloperationById(id);
    }

    @Override
    public PageInfo<TWelloperationQueryVo> getTWelloperationPageList(TWelloperationQueryParamS tWelloperationQueryParam) throws Exception {
        PageHelper.startPage(tWelloperationQueryParam.getFindArticleDto().getPage(), tWelloperationQueryParam.getFindArticleDto().getPageSize(),"");
        if (tWelloperationQueryParam.getState().equals("1")&&tWelloperationQueryParam.getState()!=null){
            tWelloperationQueryParam.setYhwork("养护");
            tWelloperationQueryParam.setState(null);
        }
        if (tWelloperationQueryParam.getState()!=null&&tWelloperationQueryParam.getState().equals("2")){
            tWelloperationQueryParam.setYhwork("检查");
            tWelloperationQueryParam.setState("正常");
        }
        if (tWelloperationQueryParam.getState()!=null&&tWelloperationQueryParam.getState().equals("3")){
            tWelloperationQueryParam.setYhwork("检查");
            tWelloperationQueryParam.setState("异常");
        }

        List<TWelloperationQueryVo> iPage = tWelloperationMapper.getTWelloperationPageList(tWelloperationQueryParam);
        //人员编号转换成人员中文名
        if (tWelloperationQueryParam.getManId()!=null&&tWelloperationQueryParam.getManId()!=""){
            EmergencyUserStateQueryVo emergencyUserStateById = emergencyUserStateMapper.getEmergencyUserStateById(tWelloperationQueryParam.getManId());
            if (emergencyUserStateById!=null) {
                String userName = emergencyUserStateById.getUserName();
                for (int i = 0; i < iPage.size(); i++) {
                    iPage.get(i).setManName(userName);
                    if (iPage.get(i).getUpdatetime()!=null){

                    }

                }
            }
        }
        PageHelper.clearPage();
        PageInfo<TWelloperationQueryVo> pageInfo=new PageInfo<>(iPage);
        return pageInfo;
    }

    @Override
    public TWelloperationCountQueryVo selectCountByYh(TWelloperationQueryParamS tWelloperationQueryParam) {
        TWelloperationCountQueryVo tWelloperationCountQueryVo = null;
        if (tWelloperationQueryParam.getState().equals("1")) {
             tWelloperationCountQueryVo = tWelloperationMapper.selectCountByYh(tWelloperationQueryParam);
        }
        if (tWelloperationQueryParam.getState().equals("2")){
            tWelloperationCountQueryVo = tWelloperationMapper.selectCountByZc(tWelloperationQueryParam);
        }
        if (tWelloperationQueryParam.getState().equals("3")){
            tWelloperationCountQueryVo = tWelloperationMapper.selectCountByYc(tWelloperationQueryParam);
        }
        return tWelloperationCountQueryVo;
    }

    @Override
    public WellRecordCountListVo statisticCount(String times, String type, String district) throws ParseException {
        WellRecordCountListVo pratolInfooCountListVo = new WellRecordCountListVo();
        List<Date> getdates = getdates(times);
        Date startDate = getdates.get(0);
        Date  endDate= getdates.get(1);

        List<WellRecordVo>  pratolInfoVos=new ArrayList<>();
        // 获取 里程数 次数 问题数
        WellRecordVoCount integers=new WellRecordVoCount();
        if (("1").equals(type)){
            pratolInfoVos= tWelloperationMapper.statisticCount(startDate,endDate);
            // 获取 里程数 次数 问题数
           List<Integer> integer=tWelloperationMapper.getCount(startDate,endDate);
            integers.setAbnormal(integer.get(0));
            integers.setNormal(integer.get(1));
        }else {
            pratolInfoVos= tWelloperationMapper.statisticCountDistrict(startDate,endDate,district);
            // 获取 里程数 次数 问题数
            List<Integer>  integer=tWelloperationMapper.getCountDistrict(startDate,endDate,district);
            integers.setAbnormal(integer.get(0));
            integers.setNormal(integer.get(1));
        }




        pratolInfooCountListVo.setWellRecordVoList(pratolInfoVos);
        pratolInfooCountListVo.setWellRecordVoCount(integers);
        return pratolInfooCountListVo;
    }





    public  List<Date> getdates(String date) throws ParseException {
        String month1 = "";
        String day2 = "";
        String year2 = "";
        String[] split = date.split("-");
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
}
