package com.wavenet.maintenance.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.wavenet.maintenance.convert.jdbcUtil;
import com.wavenet.maintenance.dao.entity.PatrolGpsHistory;
import com.wavenet.maintenance.dao.entity.Trajectory;
import com.wavenet.maintenance.web.query.PatrolGpsHistoryQueryParam;
import com.wavenet.maintenance.web.vo.PatrolGpsHistoryQueryVo;
import com.wavenet.maintenance.web.vo.PatrolGpsRealtimeQueryVo;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenetframework.boot.common.vo.Paging;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-03-13
 */
public interface PatrolGpsHistoryService extends BaseService<PatrolGpsHistory> {

    /**
     * 保存
     *
     * @param patrolGpsHistory
     * @return
     * @throws Exception
     */
    boolean savePatrolGpsHistory(PatrolGpsHistory patrolGpsHistory) throws Exception;

    /**
     * 修改
     *
     * @param patrolGpsHistory
     * @return
     * @throws Exception
     */
    boolean updatePatrolGpsHistory(PatrolGpsHistory patrolGpsHistory) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deletePatrolGpsHistory(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    PatrolGpsHistoryQueryVo getPatrolGpsHistoryById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param patrolGpsHistoryQueryParam
     * @return
     * @throws Exception
     */
    Paging<PatrolGpsHistoryQueryVo> getPatrolGpsHistoryPageList(PatrolGpsHistoryQueryParam patrolGpsHistoryQueryParam) throws Exception;


    /**
     * 保存并更新实时数据
     *
     * @param patrolGpsHistory
     * @return
     * @throws Exception
     */
    boolean savePatrolGpsHistoryAndUpdatePatrolRelam(PatrolGpsHistory patrolGpsHistory) throws Exception;

    /**
     * 创建轨迹
     */

    boolean createLine(Trajectory trajectory);


    List<PatrolGpsHistory> getPatrolGpsHistoryPageList1(PatrolGpsHistory patrolGpsHistoryQueryParam);


}
