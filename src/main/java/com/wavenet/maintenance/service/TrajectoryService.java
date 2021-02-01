package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.Trajectory;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenet.maintenance.web.query.TrajectoryQueryParam;
import com.wavenet.maintenance.web.vo.TrajectoryQueryVo;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-05-21
 */
public interface TrajectoryService extends BaseService<Trajectory> {

    /**
     * 保存
     *
     * @param trajectory
     * @return
     * @throws Exception
     */
    boolean saveTrajectory(Trajectory trajectory) throws Exception;

    /**
     * 修改
     *
     * @param trajectory
     * @return
     * @throws Exception
     */
    boolean updateTrajectory(Trajectory trajectory) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteTrajectory(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    TrajectoryQueryVo getTrajectoryById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param trajectoryQueryParam
     * @return
     * @throws Exception
     */
    Paging<TrajectoryQueryVo> getTrajectoryPageList(TrajectoryQueryParam trajectoryQueryParam) throws Exception;

    /**
     * 根据PatrolCode查询轨迹
     */
    List<TrajectoryQueryVo> selectByPatrolCode(String patrolCode);

}
