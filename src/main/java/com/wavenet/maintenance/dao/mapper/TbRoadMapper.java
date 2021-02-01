package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.TbRoad;
import com.wavenet.maintenance.web.query.TbRoadQueryParam;
import com.wavenet.maintenance.web.vo.TbRoadQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-04-18
 */
@Repository
public interface TbRoadMapper extends BaseMapper<TbRoad> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    TbRoadQueryVo getTbRoadById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param tbRoadQueryParam
     * @return
     */
    IPage<TbRoadQueryVo> getTbRoadPageList(@Param("page") Page page, @Param("param") TbRoadQueryParam tbRoadQueryParam);

    /**
     * 获取所有的roadName
     *
     * @param
     * @param
     * @return
     */
    List<String> getRoadName();

}
