package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.Trajectory;
import com.wavenet.maintenance.web.query.TrajectoryQueryParam;
import com.wavenet.maintenance.web.vo.TrajectoryQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-05-21
 */
@Repository
public interface TrajectoryMapper extends BaseMapper<Trajectory> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    TrajectoryQueryVo getTrajectoryById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param trajectoryQueryParam
     * @return
     */
    IPage<TrajectoryQueryVo> getTrajectoryPageList(@Param("page") Page page, @Param("param") TrajectoryQueryParam trajectoryQueryParam);

    int insertShape(@Param("patrolCode")String patrolCode,@Param("shape") String shape);

    List<Trajectory> getTrajectoryByPatrolCode(String patrolCode);

    List<TrajectoryQueryVo> selectByPatrolCodeExist(String patrolCode);

}
