package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.TWelloperation;
import com.wavenet.maintenance.web.query.TWelloperationCountQueryVo;
import com.wavenet.maintenance.web.query.TWelloperationQueryParam;
import com.wavenet.maintenance.web.query.TWelloperationQueryParamS;
import com.wavenet.maintenance.web.vo.TWelloperationQueryVo;
import com.wavenet.maintenance.web.vo.WellRecordVo;
import com.wavenet.maintenance.web.vo.WellRecordVoCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-08-12
 */
@Repository
public interface TWelloperationMapper extends BaseMapper<TWelloperation> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    TWelloperationQueryVo getTWelloperationById(Serializable id);

    /**
     * 获取分页对象
     *

     * @param tWelloperationQueryParam
     * @return
     */
    List<TWelloperationQueryVo> getTWelloperationPageList(@Param("param") TWelloperationQueryParamS tWelloperationQueryParam);

    /**
     * 查询养护总数
     * @param tWelloperationQueryParam
     * @return
     */
    TWelloperationCountQueryVo selectCountByYh(@Param("param") TWelloperationQueryParamS tWelloperationQueryParam);

    /**
     * 查询检查(正常)
     */
    TWelloperationCountQueryVo selectCountByZc(@Param("param") TWelloperationQueryParamS tWelloperationQueryParam);

    /**
     * 查询检查异常()
     */
    TWelloperationCountQueryVo selectCountByYc(@Param("param") TWelloperationQueryParamS tWelloperationQueryParam);

    List<String> stringWell();


    List<WellRecordVo> statisticCount(@Param("startDate")Date startDate,
                                      @Param("endDate")Date endDate);

    List<Integer>  getCount(@Param("startDate")Date startDate,
                               @Param("endDate")Date endDate);

    List<WellRecordVo> statisticCountDistrict(@Param("startDate")Date startDate,
                                              @Param("endDate")Date endDate,
                                              @Param("district")String district);

    List<Integer>  getCountDistrict(@Param("startDate")Date startDate,
                                       @Param("endDate")Date endDate,
                                       @Param("district")String district);
}
