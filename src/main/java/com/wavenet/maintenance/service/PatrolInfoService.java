package com.wavenet.maintenance.service;

import cn.jpush.api.push.PushResult;
import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.EventInfo;
import com.wavenet.maintenance.dao.entity.PatrolInfo;
import com.wavenet.maintenance.dao.entity.PatrolInfoS;
import com.wavenet.maintenance.web.query.PatrolInfoObjectQuerParam;
import com.wavenet.maintenance.web.query.QueryParameterObjectPage;
import com.wavenet.maintenance.web.query.QueryParameterQueryPage;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenet.maintenance.web.query.PatrolInfoQueryParam;
import com.wavenetframework.boot.common.vo.Paging;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
public interface PatrolInfoService extends BaseService<PatrolInfo> {

    /**
     * 保存
     *
     * @param patrolInfo
     * @return
     * @throws Exception
     */
    String savePatrolInfo(PatrolInfo patrolInfo) throws Exception;

    /**
     * 修改
     *
     * @param patrolInfo
     * @return
     * @throws Exception
     */
    PushResult updatePatrolInfo(PatrolInfo patrolInfo) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deletePatrolInfo(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    PatrolInfoQueryVo getPatrolInfoById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param patrolInfoQueryParam
     * @return
     * @throws Exception
     */
    Paging<PatrolInfoQueryVo> getPatrolInfoPageList(PatrolInfoQueryParam patrolInfoQueryParam) throws Exception;

    /**
     * 根据patrol_code修改工作量
     */
    ApiResult<Integer> updateMileageByPatrolCode(String patrolCode, String realMileage);

    /**
     * 根据主键修改
     */
    boolean updateByPatrolCode(PatrolInfo patrolInfo);

    /**
     * 根据PatrolCode查询PatrolInfo和eventInfo
     */
    PatrolInfoAndEventInfoQueryVo selectInfoByPatrolCode(String patrolCode);

    MileagesFreVOs pAcquiredQuantity(PatrolInfoObjectQuerParam patrolInfoObjectQuerParam) throws ParseException;

    PageInfo<PatrolInfoS> listInformation(QueryParameterObjectPage queryParameter) throws ParseException;

    PageInfo<EventInfo> listInformationQuer(QueryParameterQueryPage queryParameter) throws ParseException;

    PatrolInfoS getInfoById(String id);

    PratolInfooCountListVo statisticCount(String times,String type,String district) throws ParseException;

}
