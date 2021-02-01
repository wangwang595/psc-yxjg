package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.dao.entity.MaintenanceDetails;
import com.wavenet.maintenance.dao.entity.MaintenancePlan;
import com.wavenet.maintenance.web.query.MaintenanceDetailQueryParam;
import com.wavenet.maintenance.web.query.MaintenancePlanQueryParam;
import com.wavenet.maintenance.web.vo.DetalListUpdateVo;
import com.wavenet.maintenance.web.vo.MaintenancePlanQueryVo;
import com.wavenet.maintenance.web.vo.MaintenancePlanQueryVos;
import com.wavenet.maintenance.web.vo.MannitVo;
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
 * @since 2020-04-22
 */
@Repository
public interface MaintenancePlanMapper extends BaseMapper<MaintenancePlan> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    MaintenancePlanQueryVo getMaintenancePlanById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param maintenancePlanQueryParam
     * @return
     */
    IPage<MaintenancePlanQueryVo> getMaintenancePlanPageList(@Param("page") Page page, @Param("param") MaintenancePlanQueryParam maintenancePlanQueryParam);

    /**
     * 根据年月片区查询Plan表
     */
    List<MaintenancePlanQueryVo> selectExist(@Param("year")String year,@Param("month")String month,@Param("town")String town);



    List<MaintenancePlanQueryVos> getPlan(@Param("year") String year,
                                          @Param("month")String month,
                                          @Param("project") String project,
                                          @Param("task")String task,
                                          @Param("area") String  area);




    MannitVo sewerInformation(@Param("startDate")Date startDate,
                              @Param("endDate")Date endDate,
                              @Param("project") List<String> project);

    List<MaintenanceDetails> getListPlan(@Param("month")String month,
                                         @Param("year")String year,
                                         @Param("plan")String plan,
                                         @Param("type")String type,
                                         @Param("twon")String twon,
                                         @Param("project") List<String> project);

    List<MaintenanceDetail> monthPlan(@Param("year") String year,
                                      @Param("month")String month,
                                      @Param("plan")String plan);

    List<MaintenanceDetail> getDetalList(String id);





    Boolean deleteByPlanCode(String planCode);



    MaintenancePlan getMaintenanceById(String planCode);


    Integer deleteByplanCode(@Param("id") String id);

    List<String> getPicuer(String detailCode);

    Double getCarry(String planCode);

    Double gettotal(String planCode);
}
