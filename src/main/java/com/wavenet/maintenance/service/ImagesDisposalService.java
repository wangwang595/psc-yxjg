package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.ImagesDisposal;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenet.maintenance.web.query.ImagesDisposalQueryParam;
import com.wavenet.maintenance.web.vo.ImagesDisposalQueryVo;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-11-25
 */
public interface ImagesDisposalService extends BaseService<ImagesDisposal> {

    /**
     * 保存
     *
     * @param imagesDisposal
     * @return
     * @throws Exception
     */
    boolean saveImagesDisposal(ImagesDisposal imagesDisposal) throws Exception;

    /**
     * 修改
     *
     * @param imagesDisposal
     * @return
     * @throws Exception
     */
    boolean updateImagesDisposal(ImagesDisposal imagesDisposal) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteImagesDisposal(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    ImagesDisposalQueryVo getImagesDisposalById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param imagesDisposalQueryParam
     * @return
     * @throws Exception
     */
    Paging<ImagesDisposalQueryVo> getImagesDisposalPageList(ImagesDisposalQueryParam imagesDisposalQueryParam) throws Exception;

}
