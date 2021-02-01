package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.ImagesDisposal;
import com.wavenet.maintenance.web.query.ImagesDisposalQueryParam;
import com.wavenet.maintenance.web.vo.ImagesDisposalQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-11-25
 */
@Repository
public interface ImagesDisposalMapper extends BaseMapper<ImagesDisposal> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    ImagesDisposalQueryVo getImagesDisposalById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param imagesDisposalQueryParam
     * @return
     */
    IPage<ImagesDisposalQueryVo> getImagesDisposalPageList(@Param("page") Page page, @Param("param") ImagesDisposalQueryParam imagesDisposalQueryParam);

}
