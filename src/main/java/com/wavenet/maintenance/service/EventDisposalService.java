package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.EventDisposal;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.dao.entity.ImagesDisposal;
import com.wavenet.maintenance.web.query.EventDisposalQueryParam;
import com.wavenet.maintenance.web.query.EventDisposalWorkQueryParam;
import com.wavenet.maintenance.web.vo.EventDisposalQueryVo;
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
 * @since 2020-04-22
 */
public interface EventDisposalService extends BaseService<EventDisposal> {

    /**
     * 保存
     *
     * @param eventDisposal
     * @return
     * @throws Exception
     */
    String saveEventDisposal(EventDisposal eventDisposal) throws Exception;

    /**
     * 修改
     *
     * @param
     * @return
     * @throws Exception
     */
    boolean updateEventDisposal(EventDisposalWorkQueryParam param) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteEventDisposal(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    EventDisposalQueryVo getEventDisposalById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param eventDisposalQueryParam
     * @return
     * @throws Exception
     */
    Paging<EventDisposalQueryVo> getEventDisposalPageList(EventDisposalQueryParam eventDisposalQueryParam) throws Exception;

    boolean updateDisposal(EventDisposal param) throws Exception;

    boolean updateDisposalNew(EventDisposal param) throws Exception;

    /**
     * 逻辑删除
     */
    boolean updateIsDel(String disposalCode) throws Exception;

    /**
     * 根据dispatchCode查询disposal表
     */
    EventDisposalQueryVo selectDisposalByDispatchCode(String dispatchCode);

    Boolean updateImage(ImagesDisposal eventInfo);

    boolean modifyAndUpdate(EventDisposalWorkQueryParam param);


}
