package com.wavenet.maintenance.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wavenet.maintenance.convert.EventTypeConvert;
import com.wavenet.maintenance.dao.entity.EventTreeVo;
import com.wavenet.maintenance.manager.common.util.TreeUtil1;
import com.wavenet.maintenance.web.vo.EventInfoQueryVo;
import com.wavenetframework.boot.util.TreeUtil;
import com.wavenetframework.boot.util.UUIDUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.EventType;
import com.wavenet.maintenance.dao.mapper.EventTypeMapper;
import com.wavenet.maintenance.service.EventTypeService;
import com.wavenet.maintenance.web.query.EventTypeQueryParam;
import com.wavenet.maintenance.web.vo.EventTypeQueryVo;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;

import lombok.extern.slf4j.Slf4j;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-03-24
 */
@Slf4j
@Service
public class EventTypeServiceImpl extends BaseServiceImpl<EventTypeMapper, EventType> implements EventTypeService {

    @Autowired
    private EventTypeMapper eventTypeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveEventType(EventType eventTypeDo) throws Exception {
        eventTypeDo.setEventTypeCode(UUIDUtil.getUUID());
        return super.save(eventTypeDo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateEventType(EventType eventTypeDo) throws Exception {
        return super.updateById(eventTypeDo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteEventType(Serializable id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public EventTypeQueryVo getEventTypeById(Serializable id) throws Exception {
        return eventTypeMapper.getEventTypeById(id);
    }

    @Override
    public Paging<EventTypeQueryVo> getEventTypePageList(EventTypeQueryParam eventTypeDoQueryParam) throws Exception {
        Page page = setPageParam(eventTypeDoQueryParam, OrderItem.desc("create_time"));
        IPage<EventTypeQueryVo> iPage = eventTypeMapper.getEventTypePageList(page, eventTypeDoQueryParam);
        return new Paging(iPage);
    }

    @Override
    public List<EventTypeQueryVo> getEventTypeByParentCode(String id) throws Exception {
        EventTypeQueryVo eventTypeQueryVo=new EventTypeQueryVo();
        eventTypeQueryVo.setEventTypePcode(id);
        List<EventTypeQueryVo> list = eventTypeMapper.selectListByParentCode(id);
        return list;
    }

    @Override
    public List<EventTypeQueryVo> selectByName(String name) throws Exception {
        List<EventTypeQueryVo> list = eventTypeMapper.selectByName(name);
        return list;
    }

    @Override
    public List<EventTreeVo> selectByTree() throws Exception {
       // List<EventType> list = eventTypeMapper.selectList(null);
        List<EventType> list  = eventTypeMapper.getSelectList();
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("列表不能为空");
        }
        List<EventTreeVo> treeList = EventTypeConvert.INSTANCE.listToTreeVoList(list);

        List<EventTreeVo> tree = new TreeUtil1<EventTreeVo>().createMenu(treeList, "eventTypeCode", "eventTypePcode","0000");
        return tree;
    }

    @Override
    public List<EventInfoQueryVo> selectByCode(String code) throws Exception {
        List<EventInfoQueryVo> list = new ArrayList<>();
        List<EventTypeQueryVo> codeList = eventTypeMapper.selectByCode(code);
        for (int i=0;i<codeList.size();i++) {
            Integer lv = codeList.get(i).getLv();
            //如果查询的是4级节点直接返回结果
            if (lv.equals(4)||lv.equals(2)) {
                String name = codeList.get(i).getName();
                List<EventInfoQueryVo> infoQueryVoList = eventTypeMapper.selectInfoByName(name);
                list.addAll(infoQueryVoList);
            }
            if (lv.equals(3)) {
                String pcode = codeList.get(i).getEventTypeCode();
                //父节点和子节点
                List<EventTypeQueryVo> parentList = eventTypeMapper.selectParent(pcode);
                for (int j=0;j<parentList.size();j++) {
                    String name = parentList.get(i).getName();
                    List<EventInfoQueryVo> infoList = eventTypeMapper.selectInfoByName(name);
                    list.addAll(infoList);
                }

            }

        }


        return list;
    }

    @Override
    public List<EventTreeVo> selectTreeByCode(String eventStandard,String code) throws Exception {
        List<EventType> list = eventTypeMapper.selectTreeByStandard(eventStandard,code);
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("列表不能为空");
        }
        List<EventTreeVo> treeList = EventTypeConvert.INSTANCE.listToTreeVoList(list);
        List<EventTreeVo> tree=new TreeUtil1<EventTreeVo>().createMenu(treeList,"eventTypeCode","eventTypePcode",code);
        return tree;
    }

    @Override
    public List<EventTypeQueryVo> getEventTypeByParentCodeNew(String id,String eventStandard) throws Exception {
        EventTypeQueryVo eventTypeQueryVo=new EventTypeQueryVo();
        eventTypeQueryVo.setEventTypePcode(id);
        eventTypeQueryVo.setEventStandard(eventStandard);
        List<EventTypeQueryVo> list = eventTypeMapper.selectListByParentCodeNew(id,eventStandard);
        return list;
    }

    @Override
    public List<EventTypeQueryVo> selectTypeByPcode(String pCode, String name) {
        List<EventTypeQueryVo> list = eventTypeMapper.selectTypeByPcode(pCode, name);
        return list;
    }


}
