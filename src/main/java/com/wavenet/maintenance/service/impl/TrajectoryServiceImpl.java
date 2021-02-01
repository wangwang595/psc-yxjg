package com.wavenet.maintenance.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wavenet.maintenance.convert.jdbcUtil;
import com.wavenet.maintenance.dao.entity.Trajectory;
import com.wavenet.maintenance.dao.mapper.PatrolGpsHistoryMapper;
import com.wavenet.maintenance.dao.mapper.TrajectoryMapper;
import com.wavenet.maintenance.service.PatrolGpsHistoryService;
import com.wavenet.maintenance.service.TrajectoryService;
import com.wavenet.maintenance.web.query.TrajectoryQueryParam;
import com.wavenet.maintenance.web.vo.PatrolGpsHistoryQueryVo;
import com.wavenet.maintenance.web.vo.TrajectoryQueryVo;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-05-21
 */
@Slf4j
@Service
@DS("permission1")
public class TrajectoryServiceImpl extends BaseServiceImpl<TrajectoryMapper, Trajectory> implements TrajectoryService {

    @Autowired
    private TrajectoryMapper trajectoryMapper;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveTrajectory(Trajectory trajectory) throws Exception {
        String patrolCode = trajectory.getPatrolCode();
        //先查画线表
        List<TrajectoryQueryVo> list = trajectoryMapper.selectByPatrolCodeExist(patrolCode);
        //如果存在数据
        if (list.size()!=0) {
           //shanchu
            QueryWrapper<Trajectory> wrapper = new QueryWrapper<>();
            wrapper.setEntity(new Trajectory().setPatrolCode(trajectory.getPatrolCode()));
            trajectoryMapper.delete(wrapper);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTrajectory(Trajectory trajectory) throws Exception {
        return super.updateById(trajectory);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTrajectory(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public TrajectoryQueryVo getTrajectoryById(Serializable id) throws Exception {
        return trajectoryMapper.getTrajectoryById(id);
    }

    @Override
    public Paging<TrajectoryQueryVo> getTrajectoryPageList(TrajectoryQueryParam trajectoryQueryParam) throws Exception {
        Page page = setPageParam(trajectoryQueryParam, OrderItem.desc("create_time"));
        IPage<TrajectoryQueryVo> iPage = trajectoryMapper.getTrajectoryPageList(page, trajectoryQueryParam);
        return new Paging(iPage);
    }

    @Override
    public List<TrajectoryQueryVo> selectByPatrolCode(String patrolCode) {
        List<TrajectoryQueryVo> list = trajectoryMapper.selectByPatrolCodeExist(patrolCode);
        return list;
    }




}
