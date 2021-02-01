package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.PatrolInfo;
import com.wavenet.maintenance.dao.entity.PatrolInfoS;
import com.wavenet.maintenance.web.query.PatrolInfoQueryParam;
import com.wavenet.maintenance.web.vo.EveryReportErrorVo;
import com.wavenet.maintenance.web.vo.PatrolInfoQueryVo;
import com.wavenet.maintenance.web.vo.PratolInfoCountVo;
import com.wavenet.maintenance.web.vo.PratolInfoVo;
import com.wavenetframework.boot.common.api.ApiResult;
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
 * @since 2020-03-30
 */
@Repository
public interface PatrolInfoMapper extends BaseMapper<PatrolInfo> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    PatrolInfoQueryVo getPatrolInfoById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param patrolInfoQueryParam
     * @return
     */
    IPage<PatrolInfoQueryVo> getPatrolInfoPageList(@Param("page") Page page, @Param("param") PatrolInfoQueryParam patrolInfoQueryParam);

    /**
     * 根据patrol_code修改工作量
     */
    int updateMileageByPatrolCode(@Param("patrolCode")String patrolCode,@Param("realMileage")String realMileage);

    /**
     * 根据用户编号查询最新记录
     */

    EveryReportErrorVo selectNewByPersonCode(String personCode);

    Integer getNumberQuestions(String patrolCode);

    PatrolInfoS getInfoById(@Param("id") String id);

    List<PratolInfoVo> statisticCount(@Param("startDate") Date startDate,
                                      @Param("endDate") Date endDate);

    PratolInfoCountVo getCount(@Param("startDate") Date startDate,
                               @Param("endDate") Date endDate);

    List<PratolInfoVo> statisticCountDistrict(@Param("startDate")Date startDate,
                                              @Param("endDate") Date endDate,
                                              @Param("district")String district);

    PratolInfoCountVo getCountDistrict(@Param("startDate")Date startDate,
                                       @Param("endDate") Date endDate,
                                       @Param("district")String district);

}
