package com.wavenet.maintenance.service;

import java.io.Serializable;
import java.text.ParseException;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.dao.entity.MaintenanceDisposal;
import com.wavenet.maintenance.web.query.MaintenanceDisposalQueryParam;
import com.wavenet.maintenance.web.query.MaintenanceDisposalQueryParams;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenetframework.boot.common.vo.Paging;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-03-13
 */
public interface MaintenanceDisposalService extends BaseService<MaintenanceDisposal> {

    /**
     * 保存
     *
     * @param maintenanceDisposal
     * @return
     * @throws Exception
     */
    String saveMaintenanceDisposal(MaintenanceDisposal maintenanceDisposal) throws Exception;

    /**
     * 修改
     *
     * @param maintenanceDisposal
     * @return
     * @throws Exception
     */
    boolean updateMaintenanceDisposal(MaintenanceDisposal maintenanceDisposal) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteMaintenanceDisposal(String id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    MaintenanceDisposalQueryVo getMaintenanceDisposalById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param maintenanceDisposalQueryParam
     * @return
     * @throws Exception
     */
    Paging<MaintenanceDisposalQueryVo> getMaintenanceDisposalPageList(MaintenanceDisposalQueryParams maintenanceDisposalQueryParam) throws Exception;

    /**
     * 查询长度总值
     */
    MaintenanceDisposalCountVo getLengthCount(MaintenanceDisposalQueryParams maintenanceDisposalQueryParam) throws Exception;

    /**
     * 根据disposal_code修改工程量
     */
    ApiResult<Integer> updateLengthByDisposalCode(MaintenanceDisposal maintenanceDisposal);

    /**
     * 点击取消状态回退
     */
    boolean statusBack(MaintenanceDisposal maintenanceDisposal) throws Exception;


    Paging<MaintenanceDisposalQueryVo> getMaintenanceDisposalPageLists(MaintenanceDisposalQueryParam maintenanceDisposalQueryParam);

    PageInfo<MaintenanceDisposalCheckSpitVo> inquireReview(PgeVoParmterVo parmterVo);


    MaintenanceDisposalQueryVo getMaintenanceDisposalByCode(String id);

    boolean updateDisposal(MaintenanceDisposal maintenanceDisposal);


    MaintenanceDisposalCountVo getLengthCounts(MaintenanceDisposalQueryParams maintenanceDisposalQueryParam) throws ParseException;

    Paging<MaintenanceDisposalQueryVo> getPageListNew(MaintenanceDisposalQueryParam maintenanceDisposalQueryParam);

}
