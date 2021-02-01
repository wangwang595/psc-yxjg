package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.EventDispatch;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.dao.entity.inPlans;
import com.wavenet.maintenance.web.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
@Repository
public interface PlanMapper extends BaseMapper {


    /**
     * 查询计划长度
     */
    List<PlanTableVo> selectPlanLength(@Param("year")String year,@Param("month")String month,@Param("team")String team);
    /**
     * 根据项目组查询雨水污水河流的长度
     */
    List<inPlans> selectByTeam(@Param("team")String team,@Param("year")String year,@Param("month")String month,@Param("plan")String plan);

    /**
     * 查询路段名称
     */
    List<MaintenanceDetail>selectRoadName(@Param("year")String year,@Param("month")String month,@Param("team")String team);
}
