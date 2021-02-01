package com.wavenet.maintenance.service;

import java.io.Serializable;
import java.util.List;

import com.wavenet.maintenance.dao.entity.EventTreeVo;
import com.wavenet.maintenance.dao.entity.EventType;
import com.wavenet.maintenance.web.query.EventTypeQueryParam;
import com.wavenet.maintenance.web.vo.EventInfoQueryVo;
import com.wavenet.maintenance.web.vo.EventTypeQueryVo;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenetframework.boot.common.vo.Paging;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-03-24
 */
public interface EventTypeService extends BaseService<EventType> {

    /**
     * 保存
     *
     * @param eventTypeDo
     * @return
     * @throws Exception
     */
    boolean saveEventType(EventType eventTypeDo) throws Exception;

    /**
     * 修改
     *
     * @param eventTypeDo
     * @return
     * @throws Exception
     */
    boolean updateEventType(EventType eventTypeDo) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteEventType(Serializable id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    EventTypeQueryVo getEventTypeById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param eventTypeDoQueryParam
     * @return
     * @throws Exception
     */
    Paging<EventTypeQueryVo> getEventTypePageList(EventTypeQueryParam eventTypeDoQueryParam) throws Exception;

    /**
     * 根据父Id获取查询子对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<EventTypeQueryVo> getEventTypeByParentCode(String id) throws Exception;

    List<EventTypeQueryVo> selectByName(String name) throws  Exception;

    /**
     * 查询树结构
     */
    List<EventTreeVo> selectByTree () throws  Exception;
    /**
     * 根据code查询
     */
    List<EventInfoQueryVo> selectByCode(String code) throws  Exception;

    /**
     * 查询树结构(根据市政,非市政查询)
     */
    List<EventTreeVo> selectTreeByCode (String eventStandard,String code) throws  Exception;

    List<EventTypeQueryVo> getEventTypeByParentCodeNew(String id,String eventStandard) throws Exception;

    /**
     * 检查井作业大类小类查询
     */
    List<EventTypeQueryVo> selectTypeByPcode(String pCode,String name);
}
