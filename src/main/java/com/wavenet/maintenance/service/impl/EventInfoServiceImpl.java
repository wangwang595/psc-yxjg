package com.wavenet.maintenance.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wavenet.maintenance.common.TreeUtil;
import com.wavenet.maintenance.convert.OrgDepartmentConvert;
import com.wavenet.maintenance.dao.entity.*;
import com.wavenet.maintenance.dao.mapper.*;
import com.wavenet.maintenance.manager.common.util.TreeUtil1;
import com.wavenet.maintenance.service.EventInfoService;
import com.wavenet.maintenance.web.query.*;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
public class EventInfoServiceImpl extends BaseServiceImpl<EventInfoMapper, EventInfo> implements EventInfoService {

    @Autowired
    private EventInfoMapper eventInfoMapper;
    @Autowired
    private EventDispatchMapper mapper;
    @Autowired
    private EventDispatchRelMapper relMapper;
    @Autowired
    private EventDisposalMapper disposalMapper;
    @Autowired
    private  PushServiceImpl pushService;
    @Autowired
    private  ImagesInfoMapper imagesInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveEventInfo(EventInfo eventInfo) throws Exception {
        // 生成编号

        return super.save(eventInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateEventInfo(EventInfo eventInfo) throws Exception {
        return super.updateById(eventInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteEventInfo(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public EventInfoQueryVoPiager getEventInfoById(Serializable id) throws Exception {
        return eventInfoMapper.getEventInfoById(id);
    }

    @Override
    public List<EventInfoQueryVo> findById(Serializable id) {
        List<EventInfoQueryVo> list = eventInfoMapper.findById(id);
        for (int i=0;i<list.size();i++) {
            if (list.get(i).getType()!=null) {
                if (list.get(i).getType().equals("桥梁") || list.get(i).getType().equals("护栏") || list.get(i).getType().equals("下水")) {
                    list.get(i).setEventType(list.get(i).getType());
                }
            }
        }
        return list;
    }

    @Override
    public Paging<EventInfoQueryVoStatus> getEventInfoPageList(EventInfoQueryParam eventInfoQueryParam) throws Exception {
        if (eventInfoQueryParam.getSource()!=null) {
            if (!(eventInfoQueryParam.getSource().equals(""))) {
                String source = eventInfoQueryParam.getSource();
                String replace = "'" + source + "'";
                if (source.contains(",")) {
                    replace = source.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setSource(replace);
            }
        }
        if (eventInfoQueryParam.getType()!=null) {
            if (!(eventInfoQueryParam.getType().equals(""))) {
                String type = eventInfoQueryParam.getType();
                String replace = "'" + type + "'";
                if (type.contains(",")) {
                    replace = type.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setType(replace);
            }
        }
        if (eventInfoQueryParam.getEventState()!=null) {
            if (!(eventInfoQueryParam.getEventState().equals(""))) {
                String type = eventInfoQueryParam.getEventState();
                String replace = "'" + type + "'";
                if (type.contains(",")) {
                    replace = type.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setEventState(replace);
            }
        }

        Page page = setPageParam(eventInfoQueryParam, OrderItem.desc("report_date"));
        IPage<EventInfoQueryVoStatus> iPage = null;


        if (eventInfoQueryParam.getEventType().equals("全部")) {
            iPage = eventInfoMapper.selectTypeAll(page, eventInfoQueryParam);

        } else {
            eventInfoQueryParam.getAgent();
            if (eventInfoQueryParam.getAgent()==null||"".equals(eventInfoQueryParam.getAgent())){
                String time = eventInfoQueryParam.getTime();
                if (time!=null&&!(("").equals(time))){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    List<Date> getdate = getdates(time);
                    Date startDate = getdate.get(0);
                    Date endDate = getdate.get(1);
                    eventInfoQueryParam.setBeginTime(simpleDateFormat.format(startDate));
                    eventInfoQueryParam.setEndTime(simpleDateFormat.format(endDate));
                    eventInfoQueryParam.setTime("");
                }
                iPage = eventInfoMapper.getEventInfoPageList(page, eventInfoQueryParam);

            }else {
                iPage=eventInfoMapper.getEventInfoPageLists(page, eventInfoQueryParam);
            }
        }
        List<EventInfoQueryVoStatus> list = iPage.getRecords();
        for (int i=0;i<list.size();i++) {
            if (list.get(i).getType()!=null) {
                if (list.get(i).getType().equals("桥梁") || list.get(i).getType().equals("护栏") || list.get(i).getType().equals("下水")) {
                    list.get(i).setEventType(list.get(i).getType());
                }
            }
        }
        iPage.setRecords(list);
        return new Paging(iPage);
    }
    @Override
    public PageInfo<EventInfoQueryVoStatus> getPageLists(EventInfoQueryParamPage eventInfoQueryParam) {

        if (eventInfoQueryParam.getSource()!=null) {
            if (!(eventInfoQueryParam.getSource().equals(""))) {
                String source = eventInfoQueryParam.getSource();
                String replace = "'" + source + "'";
                if (source.contains(",")) {
                    replace = source.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setSource(replace);
            }
        }
        if (eventInfoQueryParam.getType()!=null) {
            if (!(eventInfoQueryParam.getType().equals(""))) {
                String type = eventInfoQueryParam.getType();
                String replace = "'" + type + "'";
                if (type.contains(",")) {
                    replace = type.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setType(replace);
            }
        }
        if (eventInfoQueryParam.getEventState()!=null) {
            if (!(eventInfoQueryParam.getEventState().equals(""))) {
                String type = eventInfoQueryParam.getEventState();
                String replace = "'" + type + "'";
                if (type.contains(",")) {
                    replace = type.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setEventState(replace);
            }
        }

     //   Page page = setPageParam(eventInfoQueryParam, OrderItem.desc("report_date"));
        List<EventInfoQueryVoStatus> list = null;


        if (eventInfoQueryParam.getEventType().equals("全部")) {
            list = eventInfoMapper.selectTypeAlls(eventInfoQueryParam);

        } else {
            eventInfoQueryParam.getAgent();
            if (eventInfoQueryParam.getAgent()==null||"".equals(eventInfoQueryParam.getAgent())){
                list = eventInfoMapper.getEventInfoPageListss(eventInfoQueryParam);
            }else {
                list=eventInfoMapper.getEventInfoPageListsss(eventInfoQueryParam);
            }
        }

        for (int i=0;i<list.size();i++) {
            if (list.get(i).getType()!=null) {
                if (list.get(i).getType().equals("桥梁") || list.get(i).getType().equals("护栏") || list.get(i).getType().equals("下水")) {
                    list.get(i).setEventType(list.get(i).getType());
                }
            }
        }
        // 结果集进行排序 两小时工单在上面 网格单在下面 最后是巡查发现
        // 三个集合对应 两小时工单 网格单 巡查发现
       List<EventInfoQueryVoStatus> twoHoursList = new ArrayList<>();
        List<EventInfoQueryVoStatus> wangGeList = new ArrayList<>();
        List<EventInfoQueryVoStatus> xunChaList = new ArrayList<>();
        for (EventInfoQueryVoStatus eventInfoQueryVoStatus : list) {
            Integer twoHours = eventInfoQueryVoStatus.getTwoHours();
            String source = eventInfoQueryVoStatus.getSource();
            if(twoHours.equals(1)){
                twoHoursList.add(eventInfoQueryVoStatus);
            }else if(("市网格").equals(source)||("区网格").equals(source)||("自派遣").equals(source)){
                wangGeList.add(eventInfoQueryVoStatus);
            }else {
                xunChaList.add(eventInfoQueryVoStatus);
            }
        }
        if (twoHoursList.size()>0){
            Collections.sort(twoHoursList, new Comparator<EventInfoQueryVoStatus>() {
                @Override
                public int compare(EventInfoQueryVoStatus o1, EventInfoQueryVoStatus o2) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                       // format.format(o1.getTime()) 表示 date转string类型 如果是string类型就不要转换了
                        Date dt1 = o1.getReportDate();
                        Date dt2 = o1.getReportDate();
                     // 这是由大向小排序 如果要由小向大转换比较符号就可以
                        if (dt1.getTime() < dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() > dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }

            });

        }
        if (wangGeList.size()>0){
            Collections.sort(wangGeList, new Comparator<EventInfoQueryVoStatus>() {
                @Override
                public int compare(EventInfoQueryVoStatus o1, EventInfoQueryVoStatus o2) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        // format.format(o1.getTime()) 表示 date转string类型 如果是string类型就不要转换了
                        Date dt1 = o1.getReportDate();
                        Date dt2 = o1.getReportDate();
                        // 这是由大向小排序 如果要由小向大转换比较符号就可以
                        if (dt1.getTime() < dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() > dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }

            });
        }
        if (xunChaList.size()>0){
            Collections.sort(xunChaList, new Comparator<EventInfoQueryVoStatus>() {
                @Override
                public int compare(EventInfoQueryVoStatus o1, EventInfoQueryVoStatus o2) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        // format.format(o1.getTime()) 表示 date转string类型 如果是string类型就不要转换了
                        Date dt1 = o1.getReportDate();
                        Date dt2 = o1.getReportDate();
                        // 这是由大向小排序 如果要由小向大转换比较符号就可以
                        if (dt1.getTime() < dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() > dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }

            });
        }

        list.clear();
        list.addAll(twoHoursList);
        list.addAll(wangGeList);
        list.addAll(xunChaList);
        PageHelper.startPage(eventInfoQueryParam.getFindArticleDto().getPage(), eventInfoQueryParam.getFindArticleDto().getPageSize(),"");

        PageInfo<EventInfoQueryVoStatus> eventInfoQueryVoStatusPageInfo = new PageInfo<>(list);
        return eventInfoQueryVoStatusPageInfo;
    }

    @Override
    public Boolean timeCode() {
        String date="";
        List<TimeCodeVo>  timeCodeVo= eventInfoMapper.getTimeList();
        for (TimeCodeVo codeVo : timeCodeVo) {
            Date minDate = codeVo.getMinDate();
            Date maxDate = codeVo.getMaxDate();
            //取出最大时间最小时间
            Calendar instance = Calendar.getInstance();
            instance.setTime(minDate);
            Integer year = instance.get(Calendar.YEAR);
            Integer month = instance.get(Calendar.MONTH)+1;//获取月份
            String month1="";
            if (month<10){
                month1="0"+month;
            }else {
                month1=month.toString();
            }
            Integer day = instance.get(Calendar.DATE);
            String day1="";
            if (day<10){
                day1="0"+day;
            }else {
                day1=day.toString();
            }
            Integer integer=0;
            String inters="";
           List<EventInfo> eventInfos= eventInfoMapper.getTimeLists(minDate,maxDate);
            for (EventInfo eventInfo : eventInfos) {
                date=""+year+month+day1;
                EventInfo eventInfo1 = new EventInfo();
                String eventCode = eventInfo.getEventCode();
                integer=integer+1;
                if (integer<10){
                    inters="000"+integer;
                }else if (integer<100){
                    inters="00"+integer;
                }else {
                    inters="0"+integer;
                }
                date=date+inters;
                eventInfo1.setEventCode(eventCode);
                eventInfo1.setTimeZoneCode(date);
                eventInfoMapper.updateById(eventInfo1);
            }

        }

        return null;
    }

    @Override
    public  List<OrgDeptTreeVo> areaTree(  List<OrgDept> orgDeptApiResult) {
       // List<OrgDept> menu = new TreeUtil1<OrgDept>().createMenu(orgDeptApiResult, "deptCode", "deptPcode");
        List<OrgDeptTreeVo> list = OrgDepartmentConvert.INSTANCE.listToTreeVoList(orgDeptApiResult);
        List<OrgDeptTreeVo> tree=new TreeUtil<OrgDeptTreeVo>().createMenu(list,"deptCode","deptPcode");
        return tree;
    }

    @Override
    public PageInfo<EventInfoQueryVoStatus> pageListWeb(EventInfoQueryParamPage eventInfoQueryParam) {
        PageHelper.startPage(eventInfoQueryParam.getFindArticleDto().getPage(), eventInfoQueryParam.getFindArticleDto().getPageSize(),"");
        if (eventInfoQueryParam.getSource()!=null) {
            if (!(eventInfoQueryParam.getSource().equals(""))) {
                String source = eventInfoQueryParam.getSource();
                String replace = "'" + source + "'";
                if (source.contains(",")) {
                    replace = source.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setSource(replace);
            }
        }
        if (eventInfoQueryParam.getType()!=null) {
            if (!(eventInfoQueryParam.getType().equals(""))) {
                String type = eventInfoQueryParam.getType();
                String replace = "'" + type + "'";
                if (type.contains(",")) {
                    replace = type.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setType(replace);
            }
        }
        if (eventInfoQueryParam.getEventState()!=null) {
            if (!(eventInfoQueryParam.getEventState().equals(""))) {
                String type = eventInfoQueryParam.getEventState();
                String replace = "'" + type + "'";
                if (type.contains(",")) {
                    replace = type.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setEventState(replace);
            }
        }
        if (eventInfoQueryParam.getTown()!=null) {
            if (!(eventInfoQueryParam.getTown().equals(""))) {
                String town = eventInfoQueryParam.getTown();
                String replace = "'" + town + "'";
                if (town.contains(",")) {
                    replace = town.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setTown(replace);
            }
        }
        if (eventInfoQueryParam.getProjectTeam()!=null) {
            if (!(eventInfoQueryParam.getProjectTeam().equals(""))) {
                String projectTeam = eventInfoQueryParam.getProjectTeam();
                String replace = "'" + projectTeam + "'";
                if (projectTeam.contains(",")) {
                    replace = projectTeam.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                eventInfoQueryParam.setTown(replace);
            }
        }
        List<EventInfoQueryVoStatus>  list = eventInfoMapper.getEventInfoPageListss(eventInfoQueryParam);

        PageInfo<EventInfoQueryVoStatus> eventInfoQueryVoStatusPageInfo = new PageInfo<>(list);


        return eventInfoQueryVoStatusPageInfo;
    }


    /**
     * 根据路段编号查询
     *
     * @param code
     * @return
     */
    @Override
    public List<EventInfoQueryVo> selectInfoByCode(String code) {
        List<EventInfoQueryVo> list = eventInfoMapper.selectInfoByCode(code);
        for (int i=0;i<list.size();i++) {
            if (list.get(i).getType()!=null) {
                if (list.get(i).getType().equals("桥梁") || list.get(i).getType().equals("护栏") || list.get(i).getType().equals("下水")) {
                    list.get(i).setEventType(list.get(i).getType());
                }
            }
        }
        return list;
    }

    /**
     * 根据路段编号查询返回dispatch_code
     */
    @Override
    public List<EventDisposalRel> selectDispatchCode(String code) {
        List<EventDisposalRel> list = eventInfoMapper.selectDispatchCode(code);
        return list;
    }

    /**
     * 查询出eventInfo 的所有路段信息
     */
    @Override
    public List<RoadVo> selectRoadList() {
        List<RoadVo> list = eventInfoMapper.selectRoadVoList();
        return list;
    }

    /*
     * 根据条件查出所有的eventInfo
     * */
    @Override
    public List<EventInfo> selectEventInfoListByRoadSectionCode(EventInfoQueryParamCopy eventInfoQueryParamCopy) {
        List<EventInfo> list = eventInfoMapper.selectEventListBySectionCodeAndTime(eventInfoQueryParamCopy);
        for (int i=0;i<list.size();i++) {
            if (list.get(i).getType()!=null) {
                if (list.get(i).getType().equals("桥梁") || list.get(i).getType().equals("护栏") || list.get(i).getType().equals("下水")) {
                    list.get(i).setEventType(list.get(i).getType());
                }
            }
        }
        return list;
    }

    @Override
    public boolean insertEventInfo(EventInfo eventInfo, String endTime) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String s = simpleDateFormat.format(date);
        Date dateTime = simpleDateFormat.parse(s);
        String eventCode = UUID.randomUUID().toString().replaceAll("-", "");
        eventInfo.setEventCode(eventCode);
        eventInfo.setEventState("已派遣");
        eventInfo.setEventStandard("1");
        eventInfo.setReportDate(dateTime);
        String timeCode = eventInfoMapper.getTimeCode();
        if (timeCode==null){
            //取出最大时间最小时间
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            Integer year = instance.get(Calendar.YEAR);
            Integer month = instance.get(Calendar.MONTH)+1;//获取月份
            String month1="";
            if (month<10){
                month1="0"+month;
            }else {
                month1=month.toString();
            }
            Integer day = instance.get(Calendar.DATE);
            String day1="";
            if (day<10){
                day1="0"+day;
            }else {
                day1=day.toString();
            }
            timeCode=""+year+month1+day1+"0001";
            eventInfo.setTimeZoneCode(timeCode);
        }else {
            Long s1 = Long.valueOf(timeCode) + 1l;
            eventInfo.setTimeZoneCode(String.valueOf(s1));
        }
        eventInfoMapper.insert(eventInfo);
        String dispatchCode = UUID.randomUUID().toString().replaceAll("-", "");
        EventDispatch dispatch = new EventDispatch();
        BeanUtils.copyProperties(eventInfo, dispatch);
        dispatch.setDispatchCode(dispatchCode);
        dispatch.setDispatchState("已派遣");
        dispatch.setDispatcherDate(new Date());
        dispatch.setEventType(eventInfo.getType());
        dispatch.setType(eventInfo.getSource());
        dispatch.setEndTime(endTime);
        mapper.insert(dispatch);
        EventDispatchRel rel = new EventDispatchRel();
        rel.setEventCode(eventCode);
        rel.setDispatchCode(dispatchCode);
        relMapper.insert(rel);
        pushService.pushCustomMessageS(eventInfo.getTown());

        return true;
    }

    @Override
    public EventDisposalQueryVo getEventDisposalById(Serializable code) throws Exception {
        EventDisposalQueryVo list = null;
        EventDispatchRelQueryVo eventDispatchRelById = relMapper.getEventDispatchRelByEventId(code);
        if (eventDispatchRelById != null) {
            String dispatchCode = eventDispatchRelById.getDispatchCode();
            list = disposalMapper.getEventDisposalByDispatchId(dispatchCode);
        }
        return list;
    }

    @Override
    public List<EventInfo> selectEventInfoByDispatchCode(String dispatchCode) {
        List<EventInfo> list = eventInfoMapper.selectEventInfoListByDispatchCode(dispatchCode);
        for (int i=0;i<list.size();i++) {
            if (list.get(i).getType()!=null) {
                if (list.get(i).getType().equals("桥梁") || list.get(i).getType().equals("护栏") || list.get(i).getType().equals("下水")) {
                    list.get(i).setEventType(list.get(i).getType());
                }
            }
        }
        return list;
    }

    @Override
    public String saveEventInfoNew(AddEventInfoAndEventDispatch addEventInfoAndEventDispatch) throws Exception {
        EventInfo eventInfo = new EventInfo();
        BeanUtils.copyProperties(addEventInfoAndEventDispatch, eventInfo);
        if (addEventInfoAndEventDispatch.getEventStandard().equals("1")) {
            eventInfo.setEventState("已派遣");
        }
        String eventCode="";
        if (eventInfo.getEventCode()==null ||eventInfo.getEventCode().equals("")){
             eventCode = UUID.randomUUID().toString();
        }else {
             eventCode = eventInfo.getEventCode();
        }

        eventInfo.setEventCode(eventCode);
        String timeCode = eventInfoMapper.getTimeCode();
        if (timeCode==null){
            //取出最大时间最小时间
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            Integer year = instance.get(Calendar.YEAR);
            Integer month = instance.get(Calendar.MONTH)+1;//获取月份
            String month1="";
            if (month<10){
                month1="0"+month;
            }else {
                month1=month.toString();
            }
            Integer day = instance.get(Calendar.DATE);
            String day1="";
            if (day<10){
                day1="0"+day;
            }else {
                day1=day.toString();
            }
            timeCode=""+year+month1+day1+"0001";
            eventInfo.setTimeZoneCode(timeCode);
        }else {
            Long s = Long.valueOf(timeCode) + 1l;
            eventInfo.setTimeZoneCode(String.valueOf(s));
        }

        eventInfoMapper.insert(eventInfo);
        Integer integer=0;
                if ("1".equals(eventInfo.getEventStandard())) {
                    integer= saveEventDispatch(eventInfo);
                }
        return eventCode;
    }
    // 插入EventDispatch 自派遣
    private  Integer saveEventDispatch(EventInfo eventInfo){
        String dispatchCode = UUID.randomUUID().toString();
        String eventCode1 = eventInfo.getEventCode();
        EventDispatch eventDispatch = new EventDispatch();
        eventDispatch.setDispatchCode(dispatchCode);
        eventDispatch.setDispatcherDate(new Date());
        eventDispatch.setTown(eventInfo.getTown());
        eventDispatch.setCompany(eventInfo.getCompany());
        eventDispatch.setType(eventInfo.getEventType());
        eventDispatch.setRoadCode(eventInfo.getRoadCode());
        eventDispatch.setRoadName(eventInfo.getRoadName());
        eventDispatch.setRoadSectionCode(eventInfo.getRoadSectionCode());
        eventDispatch.setRoadSectionStart(eventInfo.getRoadSectionStart());
        eventDispatch.setRoadSectionEnd(eventInfo.getRoadSectionEnd());
        eventDispatch.setDispatchState("已派遣");
        eventDispatch.setProjectTeam(eventInfo.getProjectTeam());
        eventDispatch.setEventType(eventInfo.getType());
        eventDispatch.setEventNum(1);
        mapper.insert(eventDispatch);
        //建立关联关系
        EventDispatchRel rel = new EventDispatchRel();
        rel.setEventCode(eventCode1);
        rel.setDispatchCode(dispatchCode);
        Integer  insert  = relMapper.insert(rel);
        return insert;

    }

    @Override
    public List<EventInfoQueryVo> selectByPersonCode(String personCode) {
        List<EventInfoQueryVo> list = eventInfoMapper.selectByPersonCode(personCode);
        for (int i=0;i<list.size();i++) {
            if (list.get(i).getType()!=null) {
                if (list.get(i).getType().equals("桥梁") || list.get(i).getType().equals("护栏") || list.get(i).getType().equals("下水")) {
                    list.get(i).setEventType(list.get(i).getType());
                }
            }
        }
        return list;
    }

    @Override
    public EventInfoCountVo selectEventCount() {

        EventInfoCountVo eventInfoCountVo = eventInfoMapper.selectEventInfoCount();
        return eventInfoCountVo;
    }

    @Override
    public List<EventInfoQueryVo> selectUnfinished(String type, String town) {
        List<EventInfoQueryVo> list = eventInfoMapper.selectUnfinished(type, town);
        return list;
    }

    @Override
    public boolean postpone(String eventCode) throws Exception{
        //修改eventInfo表中状态
        EventInfo eventInfo = new EventInfo();
        eventInfo.setEventCode(eventCode);
        eventInfo.setEventState("延期");
        boolean b = super.updateById(eventInfo);
        //修改evnetDispatch表中状态
        EventDispatchRelQueryVo eventDispatchRelByEventId = relMapper.getEventDispatchRelByEventId(eventCode);
        String dispatchCode = eventDispatchRelByEventId.getDispatchCode();
        EventDispatch eventDispatch = new EventDispatch();
        eventDispatch.setDispatchCode(dispatchCode);
        eventDispatch.setDispatchState("延期");
        mapper.updateById(eventDispatch);
        //修改eventDisposal表中状态
        EventDisposalQueryVo eventDisposalQueryVo = disposalMapper.selectDisposalByDispatchCode(dispatchCode);
        String disposalCode = eventDisposalQueryVo.getDisposalCode();
        EventDisposal eventDisposal = new EventDisposal();
        eventDisposal.setDisposalCode(disposalCode);
        eventDisposal.setDisposalState("延期");
        disposalMapper.updateById(eventDisposal);
        return b;
    }

    @Override
    public List<EventInfoCountVo> groupStatistics() {
        List<EventInfoCountVo> eventInfoCountVo = eventInfoMapper.groupStatistics();
        return eventInfoCountVo;
    }

    @Override
    public List<EventCountQueryVo> selectEventCount(String beginTime, String endTime, String town) {
        List<EventCountQueryVo> list = eventInfoMapper.selectEventCount(beginTime, endTime, town);
        return list;
    }

    @Override
    public List<EventInfoSourceQueryVo> selectSource(String beginTime, String endTime) {
        List<EventInfoSourceQueryVo> list = eventInfoMapper.getInfoSource(beginTime, endTime);
        return list;
    }

    @Override
    public List<EventInfoSourceByMonthQueryVo> getInfoSourceGroupByMonth(String beginTime, String endTime, String town) {
        List<EventInfoSourceByMonthQueryVo> list = eventInfoMapper.getInfoSourceGroupByMonth(beginTime, endTime, town);
        return list;
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
    public EvenInfoVo pAcquiredQuantityQuery(PatrolInfoObjectQuerParam queryParameter) throws ParseException {
        String date = queryParameter.getDate();
        List<Date> getdate = getdates(date);
        Date startDate = getdate.get(0);
        Date endDate = getdate.get(1);
        String project = queryParameter.getProject();
        EvenInfoVo eventInfo = new EvenInfoVo();
        List<String > list=new ArrayList<>();
        if (project != null) {
        project = "["+project+"]";
        //json转换为list
        Gson gson = new Gson();
            list  = gson.fromJson(project, new TypeToken<List<String>>(){}.getType());
            if (project.equals("[杨浦市政养护建设工程有限公司]")){
                list.clear();
            }
        }
       Integer problem= eventInfoMapper.getProblem(startDate,endDate,list);
        Integer dispose=eventInfoMapper.getDispose(startDate,endDate,list);
        Integer carry=eventInfoMapper.getCarry(startDate,endDate,list);
        Integer work=eventInfoMapper.getWork(startDate,endDate,list);
        Integer workTotal=eventInfoMapper.getWorkTotal(startDate,endDate,list);
        eventInfo.setProblem(problem);
        eventInfo.setCarry(carry);
        eventInfo.setDispose(dispose);
        eventInfo.setWork(work);
        eventInfo.setWorkTotal(workTotal);
        return eventInfo;
    }

    @Override
    public Map<String, GroupStatisticsVo> getEventRecord(String source, String beginTime, String endTime, String eventState, String town, String projectTeam, String type) {

        Map<String, GroupStatisticsVo> stringGroupStatisticsVoMap = new HashMap<>();
        // 事件记录  上报个数 完成个数 审核个数
        GroupStatisticsVo groupStatisticsVo = new GroupStatisticsVo();
        Gson gson = new Gson();
        List<String> sourceList = new ArrayList<>();
        if (source != null) {
            source = "[" + source + "]";
            //json转换为list
            sourceList = gson.fromJson(source, new TypeToken<List<String>>() {
            }.getType());
        }
        //json转换为list
        // sourceList = gson.fromJson(source, new TypeToken<List<String>>(){}.getType());

        List<String> typeList = new ArrayList<>();
        if (type != null) {
            type = "[" + type + "]";
            //json转换为list
            typeList = gson.fromJson(type, new TypeToken<List<String>>() {
            }.getType());

        }
        if (town!=null) {
            if (!(town.equals(""))) {
                town = town;
                String replace = "'" + town + "'";
                if (town.contains(",")) {
                    replace = town.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                town=replace;
            }
        }
        if (projectTeam!=null) {
            if (!(projectTeam.equals(""))) {
               projectTeam = projectTeam;
                String replace = "'" + projectTeam + "'";
                if (projectTeam.contains(",")) {
                    replace = projectTeam.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                projectTeam=replace;
            }
        }

        Integer reports = eventInfoMapper.eventReport(sourceList, beginTime, endTime,
                eventState, town, projectTeam, typeList);
        String s = "";
        Integer carry2 = 0;
        Integer review = 0;
        if (eventState != null) {
            if (eventState.equals("已完成")) {
                carry2 = eventInfoMapper.eventCarry(sourceList, beginTime, endTime,
                        eventState, town, projectTeam, typeList);
                review = 0;
            }
            if (eventState.equals("已审核")) {
                carry2 = 0;
                review = review = eventInfoMapper.eventReview(sourceList, beginTime, endTime,
                        eventState, town, projectTeam, typeList);
            }

            if ((!eventState.equals("已审核")) && (!eventState.equals("已完成"))) {
                    carry2 = 0;
                    review = 0;
                }

        }
            if (eventState == null) {
                String eventState1 = "已完成";
                carry2 = eventInfoMapper.eventCarry(sourceList, beginTime, endTime,
                        eventState1, town, projectTeam, typeList);
                String eventState2 = "已审核";
                review = review = eventInfoMapper.eventReview(sourceList, beginTime, endTime,
                        eventState2, town, projectTeam, typeList);
        }
        groupStatisticsVo.setReport(reports);
        groupStatisticsVo.setCarry(carry2);
        groupStatisticsVo.setReview(review);
        stringGroupStatisticsVoMap.put("Record", groupStatisticsVo);
        return stringGroupStatisticsVoMap;
    }

    @Override
    public HistoricalEmergencyVoS listHistoricalEmergency(String year) throws ParseException {

        // 道路病害
        Date startDate=null;
        Date endDate=null;
        if (year!=null&&!(year.equals(""))) {
            List<Date> getdate = getdates(year);
            startDate = getdate.get(0);
             endDate = getdate.get(1);
        }
        HistoricalEmergencyVoS historicalEmergencyVoS = new HistoricalEmergencyVoS();
        List<HistoricalEmergencyVo> emergencyVoList=  eventInfoMapper.listHistoricalEmergency(startDate,endDate);
        historicalEmergencyVoS.setHistoricalEmergencyVos(emergencyVoList);
       List<EventInfo>  xyVoList= eventInfoMapper.getXy(startDate,endDate);
        historicalEmergencyVoS.setXyVoList(xyVoList);

        return historicalEmergencyVoS;

    }



    // 更新图片
    @Override
    public Boolean updateImage(EventInfoImage eventInfo) {
        // 进行更新 eventinfo 图片
        EventInfo eventInfo1 = new EventInfo();
        eventInfo1.setEventCode(eventInfo.getEventCode());
        eventInfo1.setAttachment(eventInfo.getAttachment());
        eventInfoMapper.updateById(eventInfo1);
        ImagesInfo imagesInfo = new ImagesInfo();
        imagesInfo.setCodingCode(eventInfo.getEventCode());
        imagesInfo.setPersons(eventInfo.getPerson());
        imagesInfo.setStatus(eventInfo.getStatus());
        imagesInfo.setDataTime(new Date());
        imagesInfo.setTableName("eventInfo");
        imagesInfo.setImages(eventInfo.getUpdateImages());
        // 进行数据存储
        int insert = imagesInfoMapper.insert(imagesInfo);
        Boolean flag=false;
        if (insert>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public EventInfoQueryVoStatus getEventInfoByCode(String eventCode) {
        EventInfoQueryVoStatus eventInfoByCode=   eventInfoMapper.getEventInfoByCode(eventCode);

        return eventInfoByCode;
    }

    @Override
    public  QuestionsCountListVo statisticCount(String times,String type,String district) throws ParseException {
        QuestionsCountListVo questionsCountListVo = new QuestionsCountListVo();
        Map<String, Object> stringObjectHashMap1 = new HashMap<>();

        List<Date> getdates = getdates(times);
        Date startDate = getdates.get(0);
        Date  endDate= getdates.get(1);
        List<QuestionsVo>  pratolInfoVos=new ArrayList<>();
        QuestionsCountVo integers =new QuestionsCountVo();
        if (("1").equals(type)){
            // 当为1的时候是为排水处
            pratolInfoVos = eventInfoMapper.statisticCount(startDate,endDate);
            integers= eventInfoMapper.getCount(startDate,endDate);
        }else {
            // 为区级人员
            pratolInfoVos = eventInfoMapper.statisticCountDistrict(startDate,endDate,district);
            integers= eventInfoMapper.getCountdistrict(startDate,endDate,district);
        }
        questionsCountListVo.setQuestionsVos(pratolInfoVos);
        questionsCountListVo.setQuestionsCountVo(integers);
        return questionsCountListVo;



    }


    @Override
    public PageInfo<EventInfo> cquiredQuantityQueryList(QueryParameterEventPage queryParameter) throws ParseException {
        PageHelper.startPage(queryParameter.getFindArticleDto().getPage(), queryParameter.getFindArticleDto().getPageSize(),"");
        String date = queryParameter.getDate();
        List<Date> getdate = getdates(date);
        Date startDate = getdate.get(0);
        Date endDate = getdate.get(1);
        String project = queryParameter.getProject();
        String source = queryParameter.getSource();
        String twon = queryParameter.getTwon();
        String status = queryParameter.getStatus();
        List<String > list =new ArrayList<>();
        Gson gson = new Gson();
        if (source != null&&(source.equals("热线工单"))){
            source="自派遣,网格单";

        source = "["+source+"]";
        //json转换为list

            list = gson.fromJson(source, new TypeToken<List<String>>(){}.getType());
        }
        String disease = queryParameter.getDisease();
        List<String > projectList=new ArrayList<>();
        if (project != null&&!(project.equals(""))) {
        project = "["+project+"]";
        //json转换为list
            projectList = gson.fromJson(project, new TypeToken<List<String>>(){}.getType());
            if (project.equals("[杨浦市政养护建设工程有限公司]")){
                projectList=null;
            }
        }
        List<EventInfo> patrolInfos1= eventInfoMapper.cquiredQuantityQueryList(startDate,endDate,projectList,list,twon,status,disease);

        PageHelper.clearPage();
        PageInfo<EventInfo> pageInfo=new PageInfo<>(patrolInfos1);
        return pageInfo;


    }



    public  Date getdate(String date) throws ParseException {

        Date date1 = new Date();
        int day = date1.getDate();
        int year = date1.getYear()+1900;
        int month = date1.getMonth()+1;
        String month1="";
        String day2="";
        if (month<=9){
            month1="0"+month;

        }else {
            month1=Integer.toString(month);
        }
        if (day<=9){
            day2="0"+date1;

        }else {
            day2=Integer.toString(day);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2=null;
        if(date.equals("day")){
            //为天的时候 为 2020-09-23 00:00:00
            String time=year+"-"+month1+"-"+day2+" 00:00:00";
            date2=simpleDateFormat.parse(time);

        }
        if(date.equals("month")){
            //为月的时候 为 2020-09-01 00:00:00
            String time=year+"-"+month1+"-"+"01"+" 00:00:00";
            date2=simpleDateFormat.parse(time);
        }
        if(date.equals("year")){
            //为年的时候 为 2020-01-01 00:00:00
            String time=year+"-"+"01"+"-"+"01"+" 00:00:00";
            date2=simpleDateFormat.parse(time);
        }

        return date2;
    }

}
