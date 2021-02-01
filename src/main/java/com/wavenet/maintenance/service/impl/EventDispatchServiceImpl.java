package com.wavenet.maintenance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.*;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.dao.mapper.*;
import com.wavenet.maintenance.dao.mapper.EventInfoMapper;
import com.wavenet.maintenance.service.EventDispatchService;
import com.wavenet.maintenance.service.PushService;
import com.wavenet.maintenance.web.query.DispatchParam;
import com.wavenet.maintenance.web.query.EventDispatchQueryParam;
import com.wavenet.maintenance.web.query.EventInfoQueryParamCopy;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import io.swagger.annotations.ApiOperation;
import com.wavenetframework.boot.util.DateUtil;
import com.wavenetframework.boot.util.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.types.Code;
import org.apache.velocity.runtime.directive.Foreach;
import org.bson.types.Code;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
public class EventDispatchServiceImpl extends BaseServiceImpl<EventDispatchMapper, EventDispatch> implements EventDispatchService {

    @Autowired
    private EventDispatchMapper eventDispatchMapper;

    @Autowired
    private EventInfoMapper eventInfoMapper;

    @Autowired
    private EventDispatchRelMapper eventDispatchRelMapper;

    @Autowired
    private EventDisposalMapper eventDisposalMapper;

    @Autowired
    private PushService pushService;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateEventDispatch(EventDispatch eventDispatch) throws Exception {
        //disposal取消时更新dispatch状态为已派遣并更新eventinfo状态为已派遣，删除disposal
        if(eventDispatch.getDispatchState().equals("已派遣")){
            List<EventInfo> eventInfos = eventInfoMapper.selectEventInfoListByDispatchCode(eventDispatch.getDispatchCode());
            for(EventInfo eventInfo:eventInfos){
                eventInfo.setEventState("已派遣");
                eventInfoMapper.updateById(eventInfo);
            }
            EventDisposal eventDisposal = new EventDisposal();
            eventDisposal.setDispatchCode(eventDispatch.getDispatchCode());
            QueryWrapper<EventDisposal> wrapper =new QueryWrapper<>(eventDisposal);
            List<EventDisposal> eventDisposals = eventDisposalMapper.selectList(wrapper);
            for(EventDisposal eventDisposal1:eventDisposals){
                eventDisposal1.setIsDel("0");
                eventDisposalMapper.updateById(eventDisposal1);
            }
            //dispatch表中接收人，接受人编号置null
            eventDispatchMapper.updateNull(eventDispatch.getDispatchCode());
        }else {
            if(eventDispatch.getDispatchState().equals("已完成")) {
                List<EventInfo> eventInfos = eventInfoMapper.selectEventInfoListByDispatchCode(eventDispatch.getDispatchCode());
                for (EventInfo eventInfo : eventInfos) {
                    eventInfo.setEventState("已完成");
                    eventInfoMapper.updateById(eventInfo);
                }
            }
            if(eventDispatch.getDispatchState().equals("执行中")) {
                List<EventInfo> eventInfos = eventInfoMapper.selectEventInfoListByDispatchCode(eventDispatch.getDispatchCode());
                for (EventInfo eventInfo : eventInfos) {
                    eventInfo.setEventState("执行中");
                    eventInfoMapper.updateById(eventInfo);
                }
            }
        }
        return super.updateById(eventDispatch);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteEventDispatch(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public EventDispatchQueryVo getEventDispatchById(Serializable id) throws Exception {
        return eventDispatchMapper.getEventDispatchById(id);
    }

    @Override
    public Paging<EventDispatchQueryVo> getEventDispatchPageList(EventDispatchQueryParam eventDispatchQueryParam) throws Exception {
        Page page = setPageParam(eventDispatchQueryParam,OrderItem.desc("solve_start"));
        IPage<EventDispatchQueryVo> iPage = eventDispatchMapper.getEventDispatchPageList(page, eventDispatchQueryParam);
        return new Paging(iPage);
    }

    @Override
    public EventDispatchYhInfoVo getYhInfo(String dispatchCode,String disposalCode) {
        //查询主表
        EventDispatchYhInfoVo list = eventDispatchMapper.selectDispatchInfo(disposalCode);
        //病害信息
        List<EventInfoQueryVo> eventInfoQueryVos = eventDispatchMapper.selectInfoByCode(dispatchCode);
        //工作量查询
        List<EventDisposalWorkloadQueryVo> eventDisposalWorkloadQueryVos = eventDispatchMapper.selectWorkLoad(disposalCode);
        String solveStart = list.getSolveStart();
        String solveEnd = list.getSolveEnd();
        String start = solveStart.substring(0,solveStart.length()-8);
        String end = solveEnd.substring(0,solveEnd.length()-8);
        list.setSolveStart(start);
        list.setSolveEnd(end);
        //判断如果照片为空，就用图片代替
        if (list.getAttAfter()==null||list.getAttAfter()=="") {
            list.setAttAfter("5e8eb0e924a09d0c1fbacc20");
        }
        if (list.getAttOngoing()==null||list.getAttOngoing()=="") {
            list.setAttOngoing("5e8eb0e924a09d0c1fbacc20");
        }
        if (list.getAttBefore()==null||list.getAttBefore()=="") {
            list.setAttBefore("5e8eb0e924a09d0c1fbacc20");
        }
        list.setBhType(eventInfoQueryVos);
        list.setWorkLoad(eventDisposalWorkloadQueryVos);
        return list;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addDiaptchAndUpdateEventInfo(EventDispatchParam param) {
        try {
            String id = UUIDUtil.getUUID();
            param.getEventDispatch().setDispatchCode(id);
            param.getEventDispatch().setDispatchState("未派遣");
            param.getEventDispatch().setDispatcherDate(new Date());
            eventDispatchMapper.insert(param.getEventDispatch());
            for (int i = 0; i < param.getCode().size(); i++) {
                EventInfo eventInfo = new EventInfo();
                eventInfo.setEventCode(param.getCode().get(i));
                eventInfo.setEventState("已受理");
                eventInfoMapper.updateById(eventInfo);
            }
            for (int i = 0; i < param.getCode().size(); i++) {
                EventDispatchRel eventDispatchRel = new EventDispatchRel();
                eventDispatchRel.setEventCode(param.getCode().get(i));
                eventDispatchRel.setDispatchCode(id);
                eventDispatchRelMapper.insert(eventDispatchRel);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public EventDispatchQueryVo findById(Serializable id) {
        return eventDispatchMapper.findById(id);
    }

    @Override
    public boolean UpdateDiaptchListAddRecipient(EventDispatchParam param) {
        try {
            for (int i = 0; i < param.getCode().size(); i++) {
                EventDispatch eventDispatch = new EventDispatch();
                BeanUtils.copyProperties(param.getEventDispatch(),eventDispatch);
                eventDispatch.setDispatchCode(param.getCode().get(i));
                eventDispatchMapper.updateById(eventDispatch);
                List<EventInfo> list = eventInfoMapper.selectEventInfoListByDispatchCode(param.getCode().get(i));
                for (int j = 0; j < list.size(); j++) {
                    EventInfo eventInfo = list.get(j);
                    eventInfo.setEventState("已派遣");
                    eventInfoMapper.updateById(eventInfo);
                    PushInfo info = new PushInfo();
                    info.setPersonCode(param.getEventDispatch().getRecipientCode());
                    info.setType("1");
                    pushService.push(info);
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<RoadVo> selectRoadList() {
        List<RoadVo> list = eventDispatchMapper.selectRoadVoList();
        return list;
    }

    @Override
    public List<EventDispatch> selectEventDispatchListByRoadSectionCode(EventInfoQueryParamCopy eventInfoQueryParamCopy) {
        List<EventDispatch> list=eventDispatchMapper.selectEventListBySectionCodeAndTime(eventInfoQueryParamCopy);
        return list;
    }

    @Override
    public Paging<EventDispatch> EventDispatchPageList(DispatchParam param) throws Exception {
        Page page = setPageParam(param, OrderItem.desc("dispatcher_date"));
        IPage<EventDispatch> iPage = eventDispatchMapper.EventDispatchPageList(page, param);
        return new Paging(iPage);
    }

    @Override
    public List<EventDispatch> selectEventDispatchListByRoadSectionCodeNew(EventInfoQueryParamCopy eventInfoQueryParamCopy) {
        List<EventDispatch> list1=eventDispatchMapper.selectEventListBySectionCodeAndTimeNews(eventInfoQueryParamCopy);
        List<EventDispatch> list=eventDispatchMapper.selectEventListBySectionCodeAndTimeNew(eventInfoQueryParamCopy);
        List<EventDispatch> eventDispatches=new ArrayList<>();
        eventDispatches.addAll(list1);
        eventDispatches.addAll(list);
        return eventDispatches;
    }
}
