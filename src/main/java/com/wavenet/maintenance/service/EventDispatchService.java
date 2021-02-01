package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.EventDispatch;
import com.wavenet.maintenance.dao.entity.EventDispatchParam;
import com.wavenet.maintenance.web.query.DispatchParam;
import com.wavenet.maintenance.web.query.EventDispatchQueryParam;
import com.wavenet.maintenance.web.query.EventDisposalQueryParam;
import com.wavenet.maintenance.web.query.EventInfoQueryParamCopy;
import com.wavenet.maintenance.web.vo.EventDispatchQueryVo;
import com.wavenet.maintenance.web.vo.EventDispatchYhInfoVo;
import com.wavenet.maintenance.web.vo.EventDisposalQueryVo;
import com.wavenet.maintenance.web.vo.RoadVo;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
public interface EventDispatchService extends BaseService<EventDispatch> {



    /**
     * 修改
     *
     * @param eventDispatch
     * @return
     * @throws Exception
     */
    boolean updateEventDispatch(EventDispatch eventDispatch) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteEventDispatch(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    EventDispatchQueryVo getEventDispatchById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param eventDispatchQueryParam
     * @return
     * @throws Exception
     */
    Paging<EventDispatchQueryVo> getEventDispatchPageList(EventDispatchQueryParam eventDispatchQueryParam) throws Exception;
    /**
     * 养护详情列表
     */
    EventDispatchYhInfoVo getYhInfo(String dispatchCode,String disposalCode);

    /*
     * @Author john
     * @Description //TODO 关联查询派遣
     * @Date 2020/4/22  18:26
     * @Param [id]
     * @return com.wavenet.maintenance.web.vo.EventDispatchQueryVo
     **/
    EventDispatchQueryVo findById(Serializable id);

    /*
    * 添加事件派遣表并修改事件表
    * */
    boolean addDiaptchAndUpdateEventInfo(EventDispatchParam param);

    /*
     * 修改事件派遣表添加受理人
     * */
    boolean UpdateDiaptchListAddRecipient(EventDispatchParam param);


    /*
     * 获取所有的路段信息
     * */
    List<RoadVo> selectRoadList();


    /*
     * 获取路段code获取所有的事件信息
     * */
    List<EventDispatch> selectEventDispatchListByRoadSectionCode(EventInfoQueryParamCopy eventInfoQueryParamCopy);

    /**
     * 处置表分页查询
     */
    Paging<EventDispatch> EventDispatchPageList(DispatchParam param) throws Exception;

    /*
     * 获取路段code获取所有的事件信息New
     * */
    List<EventDispatch> selectEventDispatchListByRoadSectionCodeNew(EventInfoQueryParamCopy eventInfoQueryParamCopy);
}
