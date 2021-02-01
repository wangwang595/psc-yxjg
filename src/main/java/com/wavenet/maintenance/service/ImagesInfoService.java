package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.ImagesInfo;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenet.maintenance.web.query.ImagesInfoQueryParam;
import com.wavenet.maintenance.web.vo.ImagesInfoQueryVo;
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
public interface ImagesInfoService extends BaseService<ImagesInfo> {

    /**
     * 保存
     *
     * @param imagesInfo
     * @return
     * @throws Exception
     */
    boolean saveImagesInfo(ImagesInfo imagesInfo) throws Exception;

    /**
     * 修改
     *
     * @param imagesInfo
     * @return
     * @throws Exception
     */
    boolean updateImagesInfo(ImagesInfo imagesInfo) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteImagesInfo(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    ImagesInfoQueryVo getImagesInfoById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param imagesInfoQueryParam
     * @return
     * @throws Exception
     */
    Paging<ImagesInfoQueryVo> getImagesInfoPageList(ImagesInfoQueryParam imagesInfoQueryParam) throws Exception;

}
