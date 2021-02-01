package com.wavenet.maintenance.web;

import com.alibaba.fastjson.JSONObject;
import com.wavenet.maintenance.dao.entity.PatrolInfo;
import com.wavenet.maintenance.dao.entity.Trajectory;
import com.wavenet.maintenance.dao.mapper.PatrolGpsHistoryMapper;
import com.wavenet.maintenance.service.*;
import com.wavenet.maintenance.web.query.TrajectoryQueryParam;
import com.wavenet.maintenance.web.vo.PatrolGpsHistoryQueryVo;
import com.wavenet.maintenance.web.vo.TrajectoryQueryVo;
import com.wavenetframework.boot.common.api.ApiResult;

import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import com.wavenetframework.boot.common.vo.Paging;

import java.util.List;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author zll
 * @since 2020-05-21
 */
@Slf4j
@RestController
@RequestMapping("/trajectory")
@Api(tags = "轨迹")
@CrossOrigin
public class TrajectoryController extends BaseController {

    @Autowired
    private TrajectoryService trajectoryService;
    @Autowired
    private MapToPicService mapToPicService;
    @Autowired
    private PatrolGpsHistoryService patrolGpsHistoryService;
    @Autowired
    private PatrolInfoService patrolInfoService;
    @Autowired
    private PatrolGpsRealtimeService patrolGpsRealtimeService;

    /**
     * 添加
     */
    @PostMapping("/add")
//    @RequiresPermissions("trajectory:add")
    @ApiOperation(value = "添加Trajectory对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Integer> addTrajectory(@Valid @RequestBody Trajectory trajectory) throws Exception {
        String substring = patrolGpsRealtimeService.selectShape(trajectory.getPatrolCode());
        //boolean flag = trajectoryService.saveTrajectory(trajectory);
        patrolGpsHistoryService.createLine(trajectory);
        //画轨迹图的同时调用截图方法，存储到patrolInfo表中
       // JSONObject mapToPic = mapToPicService.getMapToPic(trajectory.getPatrolCode(), substring);
      // // String patrolCode = mapToPic.get("patrolCode").toString();
       // String picUrl = mapToPic.get("picUrl").toString();
        PatrolInfo patrolInfo = new PatrolInfo();
       // patrolInfo.setPatrolCode(patrolCode);
       // patrolInfo.setTrajectoryImg(picUrl);
        boolean b = patrolInfoService.updateByPatrolCode(patrolInfo);
        return ApiResult.result(b);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("trajectory:update")
    @ApiOperation(value = "修改Trajectory对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateTrajectory(@Valid @RequestBody Trajectory trajectory) throws Exception {
        boolean flag = trajectoryService.updateTrajectory(trajectory);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
//    @RequiresPermissions("trajectory:delete")
    @ApiOperation(value = "删除Trajectory对象", notes = "删除", response = ApiResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<Boolean> deleteTrajectory(@PathVariable("id") Long id) throws Exception {
        boolean flag = trajectoryService.deleteTrajectory(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("trajectory:info")
    @ApiOperation(value = "获取Trajectory对象详情", notes = "查看", response = TrajectoryQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<TrajectoryQueryVo> getTrajectory(@PathVariable("id") Long id) throws Exception {
        TrajectoryQueryVo trajectoryQueryVo = trajectoryService.getTrajectoryById(id);
        return ApiResult.ok(trajectoryQueryVo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
//    @RequiresPermissions("trajectory:page")
    @ApiOperation(value = "获取Trajectory分页列表", notes = "分页列表", response = TrajectoryQueryVo.class)
    public ApiResult<Paging<TrajectoryQueryVo>> getTrajectoryPageList(@Valid @RequestBody TrajectoryQueryParam trajectoryQueryParam) throws Exception {
        Paging<TrajectoryQueryVo> paging = trajectoryService.getTrajectoryPageList(trajectoryQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 根据道路编号查询轨迹
     */
    @GetMapping("/selectByPatrolCode")
    @ApiOperation(value = "根据道路编号查询轨迹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patrolCode",value = "巡查编号")
    })
    public ApiResult<TrajectoryQueryVo> selectByPatrolCode(String patrolCode) throws Exception {
        List<TrajectoryQueryVo> list = trajectoryService.selectByPatrolCode(patrolCode);
        return ApiResult.ok(list);
    }

    

}

