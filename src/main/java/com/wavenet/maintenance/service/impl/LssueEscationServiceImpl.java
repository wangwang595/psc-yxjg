package com.wavenet.maintenance.service.impl;

import com.wavenet.maintenance.dao.entity.LssueEscation;
import com.wavenet.maintenance.dao.mapper.LssueEscationMapper;
import com.wavenet.maintenance.service.LssueEscationService;

import com.wavenet.maintenance.web.vo.LssueEscationQueryVo;
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
import java.util.UUID;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2021-01-27
 */
@Slf4j
@Service
public class LssueEscationServiceImpl extends BaseServiceImpl<LssueEscationMapper, LssueEscation> implements LssueEscationService {

    @Autowired
    private LssueEscationMapper lssueEscationMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveLssueEscation(LssueEscation lssueEscation) throws Exception {
        lssueEscation.setId(UUID.randomUUID().toString().replace("-",""));
        return super.save(lssueEscation);
    }

}
