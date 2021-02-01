package com.wavenet.maintenance.dao.mapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.web.query.MaintenanceDisposalQueryParams;
import com.wavenet.maintenance.web.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.MaintenanceDisposal;
import com.wavenet.maintenance.web.query.MaintenanceDisposalQueryParam;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-03-13
 */
@Repository
public interface MaintenanceDisposalMapper extends BaseMapper<MaintenanceDisposal> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    MaintenanceDisposalQueryVo getMaintenanceDisposalById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param maintenanceDisposalQueryParam
     * @return
     */
    IPage<MaintenanceDisposalQueryVo> getMaintenanceDisposalPageList(@Param("page") Page page,
                                                                     @Param("param") MaintenanceDisposalQueryParams maintenanceDisposalQueryParam,
                                                                     @Param("startDate")Date startDate,
                                                                     @Param("endDate") Date endDate);

    /**
     * 查询总长度
     */

    MaintenanceDisposalCountVo getLengthCount(@Param("param") MaintenanceDisposalQueryParams maintenanceDisposalQueryParam,
                                              @Param("startDate")Date startDate,
                                              @Param("endDate") Date endDate);

    /**
     * 根据disposal_code修改工程量
     */
    int updateLengthByDisposalCode(@Param("disposalCode")String disposalCode,@Param("realLength")String realLength);

    /**
     * 根据用户查询最近的一条执行中的记录
     */
    EveryReportErrorVo selectNewByPersonCode(String personCode);


    IPage<MaintenanceDisposalQueryVo> getMaintenanceDisposalPageLists(@Param("page")Page page,
                                                                      @Param("param")MaintenanceDisposalQueryParam maintenanceDisposalQueryParam);


    List<MaintenanceDisposalCheckVo> inquireReview(@Param("parmterVo")PgeVoParmterVo parmterVo);


    IPage<MaintenanceDisposalQueryVo> getMaintenanceDisposal(@Param("page")Page page,
                                                             @Param("param")MaintenanceDisposalQueryParam maintenanceDisposalQueryParam);

    MaintenanceDisposalQueryVo getMaintenanceDisposalByCode(@Param("id") String id);

    Integer getInfoByDetailCode(@Param("detailCode") String detailCode);


    String getTown(@Param("detailCode") String detailCode);


    MaintenanceDisposalCountVo getLengthCounts(@Param("param") MaintenanceDisposalQueryParams maintenanceDisposalQueryParam,
                                               @Param("startDate")Date startDate,
                                               @Param("endDate") Date endDate);

    IPage<MaintenanceDisposalQueryVo> getPageListNew(@Param("page")Page page,
                                                     @Param("param")MaintenanceDisposalQueryParam maintenanceDisposalQueryParam);


}
