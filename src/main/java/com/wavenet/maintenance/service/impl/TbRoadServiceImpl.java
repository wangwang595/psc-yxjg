package com.wavenet.maintenance.service.impl;

import com.wavenet.maintenance.dao.entity.TbRoad;
import com.wavenet.maintenance.dao.mapper.TbRoadMapper;
import com.wavenet.maintenance.service.TbRoadService;
import com.wavenet.maintenance.web.query.TbRoadQueryParam;
import com.wavenet.maintenance.web.vo.TbRoadQueryVo;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-04-18
 */
@Slf4j
@Service
public class TbRoadServiceImpl extends BaseServiceImpl<TbRoadMapper, TbRoad> implements TbRoadService {

    @Autowired
    private TbRoadMapper tbRoadMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveTbRoad(TbRoad tbRoad) throws Exception {
        return super.save(tbRoad);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTbRoad(TbRoad tbRoad) throws Exception {
        return super.updateById(tbRoad);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTbRoad(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public TbRoadQueryVo getTbRoadById(Serializable id) throws Exception {
        return tbRoadMapper.getTbRoadById(id);
    }

    @Override
    public Paging<TbRoadQueryVo> getTbRoadPageList(TbRoadQueryParam tbRoadQueryParam) throws Exception {
        Page page = setPageParam(tbRoadQueryParam, OrderItem.desc("create_time"));
        IPage<TbRoadQueryVo> iPage = tbRoadMapper.getTbRoadPageList(page, tbRoadQueryParam);
        return new Paging(iPage);
    }

    @Override
    public List<String> getRoadName() throws Exception {
        List<String> list = tbRoadMapper.getRoadName();
        return list;
    }

}
