package com.wavenet.maintenance.service;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.TWelloperation;
import com.wavenet.maintenance.web.query.TWelloperationCountQueryVo;
import com.wavenet.maintenance.web.query.TWelloperationQueryParamS;
import com.wavenet.maintenance.web.vo.WellRecordCountListVo;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenet.maintenance.web.query.TWelloperationQueryParam;
import com.wavenet.maintenance.web.vo.TWelloperationQueryVo;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.text.ParseException;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-08-12
 */
public interface TWelloperationService extends BaseService<TWelloperation> {

    /**
     * 保存
     *
     * @param tWelloperation
     * @return
     * @throws Exception
     */
    String saveTWelloperation(TWelloperation tWelloperation) throws Exception;

    /**
     * 修改
     *
     * @param tWelloperation
     * @return
     * @throws Exception
     */
    boolean updateTWelloperation(TWelloperation tWelloperation) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteTWelloperation(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    TWelloperationQueryVo getTWelloperationById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param tWelloperationQueryParam
     * @return
     * @throws Exception
     */
    PageInfo<TWelloperationQueryVo> getTWelloperationPageList(TWelloperationQueryParamS tWelloperationQueryParam) throws Exception;

    /**查询养护总数
     *
     * @return
     */
    TWelloperationCountQueryVo selectCountByYh(TWelloperationQueryParamS tWelloperationQueryParam);

    WellRecordCountListVo statisticCount(String times, String type, String district) throws ParseException;
}
