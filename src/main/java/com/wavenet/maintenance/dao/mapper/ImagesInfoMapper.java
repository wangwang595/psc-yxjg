package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.ImagesInfo;
import com.wavenet.maintenance.web.query.ImagesInfoQueryParam;
import com.wavenet.maintenance.web.vo.ImagesInfoQueryVo;
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
public interface ImagesInfoMapper extends BaseMapper<ImagesInfo> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    ImagesInfoQueryVo getImagesInfoById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param imagesInfoQueryParam
     * @return
     */
    IPage<ImagesInfoQueryVo> getImagesInfoPageList(@Param("page") Page page, @Param("param") ImagesInfoQueryParam imagesInfoQueryParam);

}
