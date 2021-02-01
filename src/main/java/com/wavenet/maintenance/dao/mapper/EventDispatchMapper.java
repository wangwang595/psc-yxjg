package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.EventDispatch;
import com.wavenet.maintenance.dao.entity.EventDispatchParam;
import com.wavenet.maintenance.dao.entity.EventDisposal;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.web.query.DispatchParam;
import com.wavenet.maintenance.web.query.EventDispatchQueryParam;
import com.wavenet.maintenance.web.query.EventDisposalQueryParam;
import com.wavenet.maintenance.web.query.EventInfoQueryParamCopy;
import com.wavenet.maintenance.web.vo.*;
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
 * @since 2020-03-30
 */
@Repository
public interface EventDispatchMapper extends BaseMapper<EventDispatch> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    EventDispatchQueryVo getEventDispatchById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param eventDispatchQueryParam
     * @return
     */
    IPage<EventDispatchQueryVo> getEventDispatchPageList(@Param("page") Page page, @Param("eventDispatchQueryParam") EventDispatchQueryParam eventDispatchQueryParam);

    /**
     * 病害信息查询
     * @param code
     * @return
     */
    List<EventInfoQueryVo> selectInfoByCode(String code);

    /**
     * 养护主表查询
     * @param code
     * @return
     */
    EventDispatchYhInfoVo selectDispatchInfo(String code);

    /**
     * 查询工作量
     */
    List<EventDisposalWorkloadQueryVo> selectWorkLoad(String disposalCode);

    /**
     * 条件查询dispatch表
     */
    List<EventDispatch> selectDispatList(@Param("town")String town);

    EventDispatchQueryVo findById(Serializable id);

    /**
     * 条件查询dispatch表
     */
    List<EventDispatch> selectEventListBySectionCodeAndTime(@Param("param")EventInfoQueryParamCopy eventInfoQueryParamCopy);

    /**
     * 查询所有的路段信息
     */
    List<RoadVo> selectRoadVoList();

    /**
     * 处置表分页查询
     */
    IPage<EventDispatch> EventDispatchPageList(@Param("page") Page page, @Param("param") DispatchParam param);

    /**
     * 条件查询dispatch表New
     */
    List<EventDispatch> selectEventListBySectionCodeAndTimeNew(@Param("param")EventInfoQueryParamCopy eventInfoQueryParamCopy);

    /**
     * 接收人，接收人编码置空
     */
    boolean updateNull(String dispatchCode);

    /**
     * 查询市政非市政
     */
    List<DispatchAndInfoQueryVo> selectDispatchAndInfo(String dispatchCode);

    List<EventDisposalSourceVo> inquireReview(@Param("parmterVo")PgeVoParmterVo parmterVo);


    List<EventDispatch> selectEventListBySectionCodeAndTimeNews(@Param("param")EventInfoQueryParamCopy eventInfoQueryParamCopy);
}
