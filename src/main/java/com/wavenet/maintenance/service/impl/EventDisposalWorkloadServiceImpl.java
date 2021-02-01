package com.wavenet.maintenance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wavenet.maintenance.dao.entity.*;
import com.wavenet.maintenance.dao.mapper.*;
import com.wavenet.maintenance.service.EventDisposalWorkloadService;
import com.wavenet.maintenance.web.query.EventDisposalWorkloadQueryParam;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
public class EventDisposalWorkloadServiceImpl extends BaseServiceImpl<EventDisposalWorkloadMapper, EventDisposalWorkload> implements EventDisposalWorkloadService {

    @Autowired
    private EventDisposalWorkloadMapper eventDisposalWorkloadMapper;

    @Autowired
    private EventDisposalMapper eventDisposalMapper;

    @Autowired
    private EventDispatchMapper eventDispatchMapper;

    @Autowired
    private EventInfoMapper eventInfoMapper;

    @Autowired
    private EventDispatchRelMapper eventDispatchRelMapper;
    @Autowired
    private  PushServiceImpl pushService;
    @Autowired
    private EventTypeMapper eventTypeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveEventDisposalWorkload(EventDisposalWorkload eventDisposalWorkload) throws Exception {
        return super.save(eventDisposalWorkload);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateEventDisposalWorkload(EventDisposalWorkload eventDisposalWorkload) throws Exception {
        return super.updateById(eventDisposalWorkload);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteEventDisposalWorkload(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public EventDisposalWorkloadQueryVo getEventDisposalWorkloadById(Serializable id) throws Exception {
        return eventDisposalWorkloadMapper.getEventDisposalWorkloadById(id);
    }

    @Override
    public Paging<EventDisposalWorkloadQueryVo> getEventDisposalWorkloadPageList(EventDisposalWorkloadQueryParam eventDisposalWorkloadQueryParam) throws Exception {
        Page page = setPageParam(eventDisposalWorkloadQueryParam);
        IPage<EventDisposalWorkloadQueryVo> iPage = eventDisposalWorkloadMapper.getEventDisposalWorkloadPageList(page, eventDisposalWorkloadQueryParam);
        return new Paging(iPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveWorkload(List<EventDisposalWorkload> eventDisposalWorkload) throws Exception {
        return super.saveBatch(eventDisposalWorkload);
    }

    @Override
    public List<EventDisposalWorkloadQueryVo> findById(Serializable id) {
        List<EventDisposalWorkloadQueryVo> list = eventDisposalWorkloadMapper.findById(id);
        return list;
    }

    @Override
    public List<EventDisposalWorkloadQueryVo> selectByDisposalCode(String disposalCode) {
        List<EventDisposalWorkloadQueryVo> list = eventDisposalWorkloadMapper.selectDisposalIsThick(disposalCode);
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateByWorkCode(EventDisposalWorkloadList eventDisposalWorkloadList) {
        boolean b = super.updateBatchById(eventDisposalWorkloadList.getEventDisposalWorkload());

        for (int i=0;i<eventDisposalWorkloadList.getEventDisposalWorkload().size();i++) {
            //审核通过后修改disposal表中的状态
            String disposalCode = eventDisposalWorkloadList.getEventDisposalWorkload().get(i).getDisposalCode();
            EventDisposal eventDisposal = new EventDisposal();
            eventDisposal.setDisposalCode(disposalCode);
            eventDisposal.setDisposalState("已审核");
            eventDisposal.setCheckStatus(2);
            eventDisposal.setWebReview(eventDisposalWorkloadList.getWebReview());
            eventDisposalMapper.updateById(eventDisposal);
            //根据处置编号查询派遣信息
            EventDisposal eventDisposalList = eventDisposalMapper.selectById(disposalCode);
            //修改派遣表中的状态
            String dispatchCode = eventDisposalList.getDispatchCode();
            EventDispatch eventDispatch = new EventDispatch();
            eventDispatch.setDispatchCode(dispatchCode);
            eventDispatch.setDispatchState("已审核");
            eventDispatchMapper.updateById(eventDispatch);
            //根据派遣编号查询事件关系表
            List<EventDispatchRelQueryVo> eventDispatchRelList = eventDispatchRelMapper.selectByDispatchCode(dispatchCode);
            for (int j=0;j<eventDispatchRelList.size();j++) {
                String eventCode = eventDispatchRelList.get(j).getEventCode();
                EventInfo eventInfo = new EventInfo();
                eventInfo.setEventState("已审核");
                eventInfo.setEventCode(eventCode);
                eventInfoMapper.updateById(eventInfo);
            }

        }
        return b;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean statusBack(String disposalCode, String web) throws Exception {
        //点击取消第一步先逻辑删除处置信息
        EventDisposal eventDisposal = new EventDisposal();
        eventDisposal.setDisposalCode(disposalCode);
        eventDisposal.setIsDel("0");
        eventDisposal.setWebReview(web);
        eventDisposalMapper.updateById(eventDisposal);
        //第二步 根据disposalCode查询处置表
        EventDisposal disposalList = eventDisposalMapper.selectById(disposalCode);
        //第三步 根据处置表中的dispatchCode查询关系表
        String dispatchCode = disposalList.getDispatchCode();

        //第四步 判断市政还是非市政
        List<DispatchAndInfoQueryVo> dispatchAndInfoQueryVoList = eventDispatchMapper.selectDispatchAndInfo(dispatchCode);
        for (int i = 0; i < dispatchAndInfoQueryVoList.size(); i++) {
            if (dispatchAndInfoQueryVoList.get(i).getEventStandard().equals("0")) {
                //修改派遣表状态为未派遣
                String dispatchCode1 = dispatchAndInfoQueryVoList.get(i).getDispatchCode();
                EventDispatch eventDispatch = new EventDispatch();
                eventDispatch.setDispatchCode(dispatchCode1);
                eventDispatch.setDispatchState("未派遣");
                eventDispatchMapper.updateById(eventDispatch);
                //修改详情表
                String eventCode = dispatchAndInfoQueryVoList.get(i).getEventCode();
                EventInfo eventInfo = new EventInfo();
                eventInfo.setEventCode(eventCode);
                eventInfo.setEventState("未派遣");
                eventInfoMapper.updateById(eventInfo);
            }else {
                //修改派遣表状态为未派遣
                String dispatchCode1 = dispatchAndInfoQueryVoList.get(i).getDispatchCode();
                EventDispatch eventDispatch = new EventDispatch();
                eventDispatch.setDispatchCode(dispatchCode1);
                eventDispatch.setDispatchState("已派遣");
                eventDispatchMapper.updateById(eventDispatch);
                //同时接收人和编号置null
                eventDispatchMapper.updateNull(dispatchCode1);
                //修改详情表
                String eventCode = dispatchAndInfoQueryVoList.get(i).getEventCode();
                EventInfo eventInfo = new EventInfo();
                eventInfo.setEventCode(eventCode);
                eventInfo.setEventState("已派遣");
                eventInfoMapper.updateById(eventInfo);
            }
        }
        pushService.pushCustomMessageS(disposalList.getTown());

        return true;
    }

    @Override
    public PageInfo<EventDisposalSourceSpitVo> inquireReview(PgeVoParmterVo parmterVo) {
        PageHelper.startPage(parmterVo.getFindArticleDto().getPage(), parmterVo.getFindArticleDto().getPageSize(),"");
        List<EventDisposalSourceVo> patrolInfos1= eventDispatchMapper.inquireReview(parmterVo);
       List<EventDisposalSourceSpitVo> eventDisposalSourceSpitVoVos = new ArrayList<>();
        for (EventDisposalSourceVo eventDisposalSourceVo : patrolInfos1) {
            EventDisposalSourceSpitVo eventDisposalSourceSpitVo = new EventDisposalSourceSpitVo();
            String attBefore = eventDisposalSourceVo.getAttBefore();
            String[] split = attBefore.split(",");
            BeanUtils.copyProperties(eventDisposalSourceVo,eventDisposalSourceSpitVo);
            eventDisposalSourceSpitVo.setAttBefore(split);
            eventDisposalSourceSpitVoVos.add(eventDisposalSourceSpitVo);
        }
        PageInfo pageInfo = new PageInfo(patrolInfos1);

        pageInfo.setList(eventDisposalSourceSpitVoVos);
        return pageInfo;



    }

    @Override
    public CheckWorkQueryVo getCheckWork(String disposalCode) {
        CheckWorkQueryVo checkWorkQueryVo = new CheckWorkQueryVo();
        //根据DisposalCOde 查询disposal列表信息
        EventDisposal eventDisposal = eventDisposalMapper.selectById(disposalCode);
        checkWorkQueryVo.setDisposals(eventDisposal);
        //根据dispatch查询eventInfo
        List<EventInfo> eventInfos = eventInfoMapper.selectEventInfoListByDispatchCode(eventDisposal.getDispatchCode());
        checkWorkQueryVo.setEventInfo(eventInfos);
        //根据disposalCode 查询work列表信息
        List<EventDisposalWorkload> byDisposalCode = eventDisposalWorkloadMapper.getByDisposalCode(disposalCode);
        checkWorkQueryVo.setWorkload(byDisposalCode);
        return checkWorkQueryVo;
    }

}
