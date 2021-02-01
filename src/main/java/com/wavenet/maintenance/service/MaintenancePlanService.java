package com.wavenet.maintenance.service;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.dao.entity.MaintenanceDetails;
import com.wavenet.maintenance.dao.entity.MaintenancePlan;
import com.wavenet.maintenance.web.query.*;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-04-22
 */
public interface MaintenancePlanService extends BaseService<MaintenancePlan> {

    /**
     * 保存
     *
     * @param maintenancePlan
     * @return
     * @throws Exception
     */
    boolean saveMaintenancePlan(MaintenancePlan maintenancePlan) throws Exception;

    /**
     * 修改
     *
     * @param maintenancePlan
     * @return
     * @throws Exception
     */
    boolean updateMaintenancePlan(MaintenancePlan maintenancePlan) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteMaintenancePlan(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    MaintenancePlanQueryVo getMaintenancePlanById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param maintenancePlanQueryParam
     * @return
     * @throws Exception
     */
    Paging<MaintenancePlanQueryVo> getMaintenancePlanPageList(MaintenancePlanQueryParam maintenancePlanQueryParam) throws Exception;



    PageInfo<MaintenanceDetails> getListPlan(QueryParameterObjectPageMainten maintenanceDetailQueryParam) throws ParseException;

    MannitVo sewerInformation(PatrolInfoObjectQuerParam queryParameter) throws ParseException;


    Boolean dataInsertion(InsertManVo insertManVo);


    Boolean DetalListUpdate(DetalListUpdateVo maintenanceDetailQueryParam);


    Boolean deleteByplanCode(String id);

    List<MaintenanceDetailPercenvO> getPlan(String year, String month, String project, String task, String area);



    PageInfo<MaintenanceDetailPercenvO> getPlans(PlansVo plansVo);

}
