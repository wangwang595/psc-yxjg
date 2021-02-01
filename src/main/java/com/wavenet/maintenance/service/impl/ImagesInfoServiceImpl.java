package com.wavenet.maintenance.service.impl;

import com.wavenet.maintenance.dao.entity.ImagesInfo;
import com.wavenet.maintenance.dao.mapper.ImagesInfoMapper;
import com.wavenet.maintenance.service.ImagesInfoService;
import com.wavenet.maintenance.web.query.ImagesInfoQueryParam;
import com.wavenet.maintenance.web.vo.ImagesInfoQueryVo;
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
public class ImagesInfoServiceImpl extends BaseServiceImpl<ImagesInfoMapper, ImagesInfo> implements ImagesInfoService {

    @Autowired
    private ImagesInfoMapper imagesInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveImagesInfo(ImagesInfo imagesInfo) throws Exception {
        return super.save(imagesInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateImagesInfo(ImagesInfo imagesInfo) throws Exception {
        return super.updateById(imagesInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteImagesInfo(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public ImagesInfoQueryVo getImagesInfoById(Serializable id) throws Exception {
        return imagesInfoMapper.getImagesInfoById(id);
    }

    @Override
    public Paging<ImagesInfoQueryVo> getImagesInfoPageList(ImagesInfoQueryParam imagesInfoQueryParam) throws Exception {
        Page page = setPageParam(imagesInfoQueryParam, OrderItem.desc("create_time"));
        IPage<ImagesInfoQueryVo> iPage = imagesInfoMapper.getImagesInfoPageList(page, imagesInfoQueryParam);
        return new Paging(iPage);
    }

}
