package com.wavenet.maintenance.service;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.EventDisposal;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkloadList;
import com.wavenet.maintenance.dao.entity.MaintenanceDisposal;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenet.maintenance.web.query.EventDisposalWorkloadQueryParam;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
public interface EventDisposalWorkloadService extends BaseService<EventDisposalWorkload> {

    /**
     * 保存
     *
     * @param eventDisposalWorkload
     * @return
     * @throws Exception
     */
    boolean saveEventDisposalWorkload(EventDisposalWorkload eventDisposalWorkload) throws Exception;

    /**
     * 修改
     *
     * @param eventDisposalWorkload
     * @return
     * @throws Exception
     */
    boolean updateEventDisposalWorkload(EventDisposalWorkload eventDisposalWorkload) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteEventDisposalWorkload(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    EventDisposalWorkloadQueryVo getEventDisposalWorkloadById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param eventDisposalWorkloadQueryParam
     * @return
     * @throws Exception
     */
    Paging<EventDisposalWorkloadQueryVo> getEventDisposalWorkloadPageList(EventDisposalWorkloadQueryParam eventDisposalWorkloadQueryParam) throws Exception;

    /**
     * 批量新增
     */

    boolean saveWorkload(List<EventDisposalWorkload> eventDisposalWorkload) throws Exception;

   /*
    * @Author john
    * @Description //TODO 处置关联查询
    * @Date 2020/4/22  16:12 
    * @Param [id]
    * @return com.wavenet.maintenance.web.vo.EventDisposalWorkloadQueryVo
    **/
    List<EventDisposalWorkloadQueryVo> findById(Serializable id);


    /**
     * 根据disposalCode查询工程量列表
     */
    List<EventDisposalWorkloadQueryVo> selectByDisposalCode(String disposalCode);

    /**
     * 根据workCode修改实际工程量
     */
    boolean updateByWorkCode(EventDisposalWorkloadList eventDisposalWorkloadList);

    /**
     * 审核点击取消状态回退
     */
    boolean statusBack(String disposalCode,String web) throws Exception;


    PageInfo<EventDisposalSourceSpitVo> inquireReview(PgeVoParmterVo parmterVo);

    CheckWorkQueryVo getCheckWork(String disposalCode);




}
