package com.wavenet.maintenance.dao.mapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wavenet.maintenance.web.query.MaintenanceDetailQueryParamCode;
import com.wavenet.maintenance.web.vo.UnblockStatisticsCountVo;
import com.wavenet.maintenance.web.vo.UnblockStatisticsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.web.query.MaintenanceDetailQueryParam;
import com.wavenet.maintenance.web.vo.MaintenanceDetailQueryVo;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-03-13
 */
@Repository
public interface MaintenanceDetailMapper extends BaseMapper<MaintenanceDetail> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    MaintenanceDetailQueryVo getMaintenanceDetailById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param maintenanceDetailQueryParam
     * @return
     */
    IPage<MaintenanceDetailQueryVo> getMaintenanceDetailPageList(@Param("page") Page page, @Param("param") MaintenanceDetailQueryParam maintenanceDetailQueryParam);


    List<MaintenanceDetailQueryVo> getMaintenanceDetail(@Param("param") MaintenanceDetailQueryParamCode maintenanceDetailQueryParam, @Param("objects") List<String> objects);

    Integer deleteByplanCode(String id);

    List<UnblockStatisticsVo> statisticCount(@Param("year") Integer startDate,
                                             @Param("month")Integer endDate);

    UnblockStatisticsCountVo getcount(@Param("year") Integer startDate,
                                      @Param("month")Integer endDate);

    List<UnblockStatisticsVo> statisticCountDistrict(@Param("year") Integer startDate,
                                                     @Param("month")Integer endDate,
                                                     @Param("district")String district);

    UnblockStatisticsCountVo getcountDistrict(@Param("year") Integer startDate,
                                              @Param("month")Integer endDate,
                                              @Param("district")String district);
}
