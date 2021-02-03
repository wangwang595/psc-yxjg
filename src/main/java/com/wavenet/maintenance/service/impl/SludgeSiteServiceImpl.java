package com.wavenet.maintenance.service.impl;

import com.wavenet.maintenance.dao.entity.SludgeSite;
import com.wavenet.maintenance.dao.mapper.SludgeSiteMapper;
import com.wavenet.maintenance.service.SludgeSiteService;

import com.wavenet.maintenance.web.vo.SludgeSiteQueryVo;
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
 * @since 2021-02-02
 */
@Slf4j
@Service
public class SludgeSiteServiceImpl extends BaseServiceImpl<SludgeSiteMapper, SludgeSite> implements SludgeSiteService {

    @Autowired
    private SludgeSiteMapper sludgeSiteMapper;


    @Override
    public List<SludgeSiteQueryVo> getSludgeSitePageList(SludgeSiteQueryVo sludgeSiteQueryParam) throws Exception {

        List<SludgeSiteQueryVo> iPage = sludgeSiteMapper.getSludgeSitePageList( sludgeSiteQueryParam);
        return iPage;
    }

}
