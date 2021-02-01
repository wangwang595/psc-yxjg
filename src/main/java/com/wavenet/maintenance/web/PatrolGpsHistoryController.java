package com.wavenet.maintenance.web;

import javax.validation.Valid;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import com.wavenet.maintenance.dao.entity.PatrolGpsHistory;
import com.wavenet.maintenance.service.PatrolGpsHistoryService;
import com.wavenet.maintenance.service.PatrolGpsRealtimeService;
import com.wavenet.maintenance.web.query.PatrolGpsHistoryQueryParam;
import com.wavenet.maintenance.web.vo.PatrolGpsHistoryQueryVo;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import com.wavenetframework.boot.common.vo.Paging;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author zll
 * @since 2020-03-13
 */
@Slf4j
@RestController
@RequestMapping("/patrolGpsHistory")
@Api(tags = "道路巡查GPS历史表")
@CrossOrigin
public class PatrolGpsHistoryController extends BaseController {

    @Autowired
    private PatrolGpsHistoryService patrolGpsHistoryService;
    @Autowired
    private PatrolGpsRealtimeService patrolGpsRealtimeService;


    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加PatrolGpsHistory对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addPatrolGpsHistory(@Valid @RequestBody PatrolGpsHistory patrolGpsHistory) throws Exception {
        boolean flag = patrolGpsHistoryService.savePatrolGpsHistory(patrolGpsHistory);
        return ApiResult.result(flag);
    }

//    /**
//     * 修改
//     */
//    @PostMapping("/update")
//    @ApiOperation(value = "修改PatrolGpsHistory对象", notes = "修改", response = ApiResult.class)
//    public ApiResult<Boolean> updatePatrolGpsHistory(@Valid @RequestBody PatrolGpsHistory patrolGpsHistory) throws Exception {
//        boolean flag = patrolGpsHistoryService.updatePatrolGpsHistory(patrolGpsHistory);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 删除
//     */
//    @PostMapping("/delete/{id}")
//    @ApiOperation(value = "删除PatrolGpsHistory对象", notes = "删除", response = ApiResult.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<Boolean> deletePatrolGpsHistory(@PathVariable("id") Long id) throws Exception {
//        boolean flag = patrolGpsHistoryService.deletePatrolGpsHistory(id);
//        return ApiResult.result(flag);
//    }

//    /**
//     * 获取
//     */
//    @GetMapping("/info/{id}")
//    @ApiOperation(value = "获取PatrolGpsHistory对象详情", notes = "查看", response = PatrolGpsHistoryQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<PatrolGpsHistoryQueryVo> getPatrolGpsHistory(@PathVariable("id") Long id) throws Exception {
//        PatrolGpsHistoryQueryVo patrolGpsHistoryQueryVo = patrolGpsHistoryService.getPatrolGpsHistoryById(id);
//        return ApiResult.ok(patrolGpsHistoryQueryVo);
//    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取PatrolGpsHistory分页列表", notes = "分页列表", response = PatrolGpsHistoryQueryVo.class)
    public ApiResult<Paging<PatrolGpsHistoryQueryVo>> getPatrolGpsHistoryPageList(@Valid @RequestBody PatrolGpsHistoryQueryParam patrolGpsHistoryQueryParam) throws Exception {
        Paging<PatrolGpsHistoryQueryVo> paging = patrolGpsHistoryService.getPatrolGpsHistoryPageList(patrolGpsHistoryQueryParam);
        return ApiResult.ok(paging);
    }
    @PostMapping("/getPageList1")
    @ApiOperation(value = "获取PatrolGpsHistory分页列表", notes = "分页列表", response = PatrolGpsHistoryQueryVo.class)
    public ApiResult<?> getPatrolGpsHistoryPageList1(@Valid @RequestBody PatrolGpsHistory patrolGpsHistory) throws Exception {
        List<PatrolGpsHistory> paging = patrolGpsHistoryService.getPatrolGpsHistoryPageList1(patrolGpsHistory);
        return ApiResult.ok(paging);
    }

    @PostMapping("/addAndUpdate")
    @ApiOperation(value = "添加轨迹历史对象并且更新轨迹实时对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addPatrolGpsHistoryAndUpdatePatrolGpsRelam(@Valid @RequestBody PatrolGpsHistory patrolGpsHistory) throws Exception {
        boolean flag = patrolGpsHistoryService.savePatrolGpsHistoryAndUpdatePatrolRelam(patrolGpsHistory);
        return ApiResult.result(flag);
    }

}

