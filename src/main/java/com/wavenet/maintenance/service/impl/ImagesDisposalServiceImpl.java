package com.wavenet.maintenance.service.impl;

import com.wavenet.maintenance.dao.entity.ImagesDisposal;
import com.wavenet.maintenance.dao.mapper.ImagesDisposalMapper;
import com.wavenet.maintenance.service.ImagesDisposalService;
import com.wavenet.maintenance.web.query.ImagesDisposalQueryParam;
import com.wavenet.maintenance.web.vo.ImagesDisposalQueryVo;
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


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-11-25
 */
@Slf4j
@Service
public class ImagesDisposalServiceImpl extends BaseServiceImpl<ImagesDisposalMapper, ImagesDisposal> implements ImagesDisposalService {

    @Autowired
    private ImagesDisposalMapper imagesDisposalMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveImagesDisposal(ImagesDisposal imagesDisposal) throws Exception {
        return super.save(imagesDisposal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateImagesDisposal(ImagesDisposal imagesDisposal) throws Exception {
        return super.updateById(imagesDisposal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteImagesDisposal(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public ImagesDisposalQueryVo getImagesDisposalById(Serializable id) throws Exception {
        return imagesDisposalMapper.getImagesDisposalById(id);
    }

    @Override
    public Paging<ImagesDisposalQueryVo> getImagesDisposalPageList(ImagesDisposalQueryParam imagesDisposalQueryParam) throws Exception {
        Page page = setPageParam(imagesDisposalQueryParam, OrderItem.desc("create_time"));
        IPage<ImagesDisposalQueryVo> iPage = imagesDisposalMapper.getImagesDisposalPageList(page, imagesDisposalQueryParam);
        return new Paging(iPage);
    }

}
