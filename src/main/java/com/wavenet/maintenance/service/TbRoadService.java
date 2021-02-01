package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.TbRoad;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenet.maintenance.web.query.TbRoadQueryParam;
import com.wavenet.maintenance.web.vo.TbRoadQueryVo;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-04-18
 */
public interface TbRoadService extends BaseService<TbRoad> {

    /**
     * 保存
     *
     * @param tbRoad
     * @return
     * @throws Exception
     */
    boolean saveTbRoad(TbRoad tbRoad) throws Exception;

    /**
     * 修改
     *
     * @param tbRoad
     * @return
     * @throws Exception
     */
    boolean updateTbRoad(TbRoad tbRoad) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteTbRoad(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    TbRoadQueryVo getTbRoadById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param tbRoadQueryParam
     * @return
     * @throws Exception
     */
    Paging<TbRoadQueryVo> getTbRoadPageList(TbRoadQueryParam tbRoadQueryParam) throws Exception;


    /**
     * 获取所有的路名
     *
     * @param
     * @return
     * @throws Exception
     */
    List<String> getRoadName() throws Exception;

}
