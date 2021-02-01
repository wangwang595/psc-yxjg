package com.wavenet.maintenance.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.*;
import com.wavenet.maintenance.dao.mapper.*;
import com.wavenet.maintenance.service.EventDisposalService;
import com.wavenet.maintenance.web.query.EventDisposalQueryParam;
import com.wavenet.maintenance.web.query.EventDisposalWorkQueryParam;
import com.wavenet.maintenance.web.vo.EventDispatchRelQueryVo;
import com.wavenet.maintenance.web.vo.EventDisposalQueryVo;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-04-22
 */
@Slf4j
@Service
public class EventDisposalServiceImpl extends BaseServiceImpl<EventDisposalMapper, EventDisposal> implements EventDisposalService {

    @Autowired
    private EventDisposalMapper eventDisposalMapper;

    @Autowired
    private EventDisposalWorkloadMapper workloadMapper;

    @Autowired
    private EventDispatchMapper dispatchMapper;

    @Autowired
    private EventDispatchRelMapper eventDispatchRelMapper;

    @Autowired
    private EventInfoMapper eventInfoMapper;
    @Autowired
    private MaintenanceDisposalMapper maintenanceDisposalMapper;
    @Autowired
    private  ImagesDisposalMapper imagesDisposalMapper;
    private int insert;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String  saveEventDisposal(EventDisposal eventDisposal) throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        eventDisposal.setDisposalCode(uuid);
        super.save(eventDisposal);
        EventDispatch dispatch = new EventDispatch();
        String state = eventDisposal.getDisposalState();
        String dispatchCode = eventDisposal.getDispatchCode();
        dispatch.setDispatchState(state);
        dispatch.setDispatchCode(dispatchCode);
        dispatchMapper.updateById(dispatch);
        //根据dispatch同步eventInfo状态
        List<EventDispatchRelQueryVo> eventDispatchRelQueryVo = eventDispatchRelMapper.selectByDispatchCode(dispatch.getDispatchCode());
        //根据关系表查到eventCode
        for (int i=0;i<eventDispatchRelQueryVo.size();i++){
            String eventCode = eventDispatchRelQueryVo.get(i).getEventCode();
            EventInfo eventInfo = new EventInfo();
            eventInfo.setEventCode(eventCode);
            eventInfo.setEventState(dispatch.getDispatchState());
            //根据eventCode同步状态
            eventInfoMapper.updateById(eventInfo);
        }
        return uuid;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateEventDisposal(EventDisposalWorkQueryParam param) throws Exception {
        // disposal同步dispathc
        EventDispatch dispatch = new EventDispatch();
        String state = param.getEventDisposal().getDisposalState();
        String dispatchCode = param.getEventDisposal().getDispatchCode();
        dispatch.setDispatchState(state);
        dispatch.setDispatchCode(dispatchCode);
        dispatchMapper.updateById(dispatch);
        //根据dispatch同步eventInfo状态
        List<EventDispatchRelQueryVo> eventDispatchRelQueryVo = eventDispatchRelMapper.selectByDispatchCode(dispatch.getDispatchCode());
        //根据关系表查到eventCode
        for (int i=0;i<eventDispatchRelQueryVo.size();i++){
        String eventCode = eventDispatchRelQueryVo.get(i).getEventCode();
        EventInfo eventInfo = new EventInfo();
        eventInfo.setEventCode(eventCode);
        eventInfo.setEventState(dispatch.getDispatchState());
        //根据eventCode同步状态
        eventInfoMapper.updateById(eventInfo);
        }
        if(param.getEventDisposal().getDisposalState().equals("已完成")){
            param.getEventDisposal().setIsDel("1");
        }
        if(param.getWorkload()==null || param.getWorkload().size()==0){
            return super.updateById(param.getEventDisposal());
        }else{
            boolean b = super.updateById(param.getEventDisposal());
            for (EventDisposalWorkload item:param.getWorkload()) {
                workloadMapper.updateById(item);
            }

            return b;
        }
    }
    @Override
    public boolean modifyAndUpdate(EventDisposalWorkQueryParam param) {
        log.info(param.toString());
        EventDispatch dispatch = new EventDispatch();
        String state = param.getEventDisposal().getDisposalState();
        String dispatchCode = param.getEventDisposal().getDispatchCode();
        dispatch.setDispatchState(state);
        dispatch.setDispatchCode(dispatchCode);
        dispatchMapper.updateById(dispatch);
        //根据dispatch同步eventInfo状态
        List<EventDispatchRelQueryVo> eventDispatchRelQueryVo = eventDispatchRelMapper.selectByDispatchCode(dispatch.getDispatchCode());
        //根据关系表查到eventCode
        for (int i=0;i<eventDispatchRelQueryVo.size();i++){
            String eventCode = eventDispatchRelQueryVo.get(i).getEventCode();
            EventInfo eventInfo = new EventInfo();
            eventInfo.setEventCode(eventCode);
            eventInfo.setEventState(dispatch.getDispatchState());
            //根据eventCode同步状态
            eventInfoMapper.updateById(eventInfo);
        }
        boolean b=false;
            param.getEventDisposal().setIsDel("1");
        if (param.getEventDisposal().getDisposalCode()==null||param.getEventDisposal().getDisposalCode().equals("")){
            // 新增
            param.getEventDisposal().setDisposalCode(UUID.randomUUID().toString().replace("-",""));
            if(param.getWorkload()==null || param.getWorkload().size()==0){
               // workloadMapper.deleteByDispoalCode(param.getEventDisposal().getDisposalCode());
                return super.save(param.getEventDisposal());
            } else {
                 b = super.saveOrUpdate(param.getEventDisposal());
                for (EventDisposalWorkload item:param.getWorkload()) {
                    item.setDisposalCode(param.getEventDisposal().getDisposalCode());
                    item.setWorkloadCode(UUID.randomUUID().toString().replace("-",""));
                    workloadMapper.insert(item);
                }
            }
        }else {
            if(param.getWorkload()==null || param.getWorkload().size()==0){
                //修改
                workloadMapper.deleteByDispoalCode(param.getEventDisposal().getDisposalCode());
                return super.updateById(param.getEventDisposal());

            } else {
              b  = super.saveOrUpdate(param.getEventDisposal());
                workloadMapper.deleteByDispoalCode(param.getEventDisposal().getDisposalCode());
                for (EventDisposalWorkload item:param.getWorkload()) {

                    item.setDisposalCode(param.getEventDisposal().getDisposalCode());
                    item.setWorkloadCode(UUID.randomUUID().toString().replace("-",""));
                    workloadMapper.insert(item);
                }
            }


        }
        return  b;



    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteEventDisposal(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public EventDisposalQueryVo getEventDisposalById(Serializable id) throws Exception {
        return eventDisposalMapper.getEventDisposalById(id);
    }

    @Override
    public Paging<EventDisposalQueryVo> getEventDisposalPageList(EventDisposalQueryParam eventDisposalQueryParam) throws Exception {
        Page page = setPageParam(eventDisposalQueryParam, OrderItem.desc("solve_start"));
        IPage<EventDisposalQueryVo> iPage = eventDisposalMapper.getEventDisposalPageList(page, eventDisposalQueryParam);
        return new Paging(iPage);
    }

    @Override
    public boolean updateDisposal(EventDisposal param) throws Exception {
        EventDispatch dispatch = new EventDispatch();
        if (param.getDispatchCode()==null||("").equals(param.getDispatchCode())) {
            return super.updateById(param);
        }else {
            dispatch.setDispatchCode(param.getDispatchCode());
            dispatch.setDispatchState(param.getDisposalState());
            dispatchMapper.updateById(dispatch);
            return super.updateById(param);
        }

    }

    @Override
    public boolean updateDisposalNew(EventDisposal param) throws Exception {
        EventDispatch dispatch = new EventDispatch();
        //如果状态为已派遣那么逻辑删除disposal
        if (param.getDisposalState().equals("已派遣")) {
            eventDisposalMapper.updateIsDel(param.getDisposalCode());
            //dispatch表中接收人，接受人编号置null
            dispatchMapper.updateNull(param.getDispatchCode());
        }
        //如果延期把接收人编号置null
        if (param.getDisposalState().equals("延期")) {
            dispatchMapper.updateNull(param.getDispatchCode());
        }
        dispatch.setDispatchCode(param.getDispatchCode());
        dispatch.setDispatchState(param.getDisposalState());
        //同步dispatch表中的状态
        dispatchMapper.updateById(dispatch);
        List<EventInfo> eventInfos = eventInfoMapper.selectEventInfoListByDispatchCode(param.getDispatchCode());
        for(EventInfo eventInfo:eventInfos){
            eventInfo.setEventState(param.getDisposalState());
            //同步eventInfo表
            eventInfoMapper.updateById(eventInfo);
        }
        return super.updateById(param);
    }

    @Override
    public boolean updateIsDel(String disposalCode) throws Exception {
        boolean b = eventDisposalMapper.updateIsDel(disposalCode);
        return b;
    }

    @Override
    public EventDisposalQueryVo selectDisposalByDispatchCode(String dispatchCode) {
        EventDisposalQueryVo eventDisposalQueryVo = eventDisposalMapper.selectDisposalByDispatchCode(dispatchCode);
        return eventDisposalQueryVo;
    }

    @Override
    public Boolean updateImage(ImagesDisposal eventInfo) {
        if ("EVENT_DISPOSAL".equals(eventInfo.getTableName())){
            EventDisposal eventDisposal = new EventDisposal();
            if (!("".equals(eventInfo.getBeforeImages())) && !(eventInfo.getBeforeImages()==null)) {
                eventDisposal.setAttBefore(eventInfo.getAttBefore());
            }
            if (!("".equals(eventInfo.getOngoingImages())) && !(eventInfo.getOngoingImages()==null)) {
                eventDisposal.setAttOngoing(eventInfo.getAttOngoing());
            }
            if (!("".equals(eventInfo.getAfterImages())) && !(eventInfo.getAfterImages()==null)) {
                eventDisposal.setAttAfter(eventInfo.getAttAfter());
            }
            eventDisposalMapper.updateById(eventDisposal);


        }
        if ("MAINTENANCE_DISPOSAL".equals(eventInfo.getTableName())){
            MaintenanceDisposal maintenanceDisposal = new MaintenanceDisposal();
            maintenanceDisposal.setDisposalCode(eventInfo.getCodingCode());
            if (!("".equals(eventInfo.getBeforeImages())) && !(eventInfo.getBeforeImages()==null)) {
                maintenanceDisposal.setAttBefore(eventInfo.getAttBefore());
                }
            if (!("".equals(eventInfo.getOngoingImages())) && !(eventInfo.getOngoingImages()==null)) {
                maintenanceDisposal.setAttOngoing(eventInfo.getAttOngoing());
            }
            if (!("".equals(eventInfo.getAfterImages())) && !(eventInfo.getAfterImages()==null)) {
                maintenanceDisposal.setAttAfter(eventInfo.getAttAfter());
            }
            maintenanceDisposalMapper.updateById(maintenanceDisposal);

            }
        ImagesDisposal imagesDisposal = new ImagesDisposal();
        imagesDisposal.setCodingCode(eventInfo.getCodingCode());
        imagesDisposal.setPersons(eventInfo.getPersons());
        imagesDisposal.setAttBefore(eventInfo.getAttBefore());
        imagesDisposal.setAttOngoing(eventInfo.getAttOngoing());
        imagesDisposal.setAttAfter(eventInfo.getAttAfter());
        imagesDisposal.setDataTime(new Date());
        imagesDisposal.setBeforeImages(eventInfo.getBeforeImages());
        imagesDisposal.setOngoingImages(eventInfo.getOngoingImages());
        imagesDisposal.setAfterImages(eventInfo.getAfterImages());
        imagesDisposal.setStatus(eventInfo.getStatus());
        imagesDisposal.setTableName(eventInfo.getTableName());
        Boolean flag=false;
        int insert = imagesDisposalMapper.insert(imagesDisposal);

                if(insert>0){
                    flag=true;
                }


        return flag;
    }




}
