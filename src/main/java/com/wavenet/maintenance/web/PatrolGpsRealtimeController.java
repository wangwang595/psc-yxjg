package com.wavenet.maintenance.web;

import javax.validation.Valid;

import com.wavenet.maintenance.web.vo.EveryReportErrorVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wavenet.maintenance.dao.entity.PatrolGpsRealtime;
import com.wavenet.maintenance.service.PatrolGpsRealtimeService;
import com.wavenet.maintenance.web.query.PatrolGpsRealtimeQueryParam;
import com.wavenet.maintenance.web.vo.PatrolGpsRealtimeQueryVo;
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
@RequestMapping("/patrolGpsRealtime")
@Api(tags = "道路巡查GPS实时表")
@CrossOrigin
public class PatrolGpsRealtimeController extends BaseController {

    @Autowired
    private PatrolGpsRealtimeService patrolGpsRealtimeService;



//    /**
//     * 添加
//     */
//    @PostMapping("/add")
//    @ApiOperation(value = "添加PatrolGpsRealtime对象", notes = "添加", response = ApiResult.class)
//    public ApiResult<Boolean> addPatrolGpsRealtime(@Valid @RequestBody PatrolGpsRealtime patrolGpsRealtime) throws Exception {
//        boolean flag = patrolGpsRealtimeService.savePatrolGpsRealtime(patrolGpsRealtime);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 修改
//     */
//    @PostMapping("/update")
//    @ApiOperation(value = "修改PatrolGpsRealtime对象", notes = "修改", response = ApiResult.class)
//    public ApiResult<Boolean> updatePatrolGpsRealtime(@Valid @RequestBody PatrolGpsRealtime patrolGpsRealtime) throws Exception {
//        boolean flag = patrolGpsRealtimeService.updatePatrolGpsRealtime(patrolGpsRealtime);
//        return ApiResult.result(flag);
//    }

//    /**
//     * 删除
//     */
//    @PostMapping("/delete/{id}")
//    @ApiOperation(value = "删除PatrolGpsRealtime对象", notes = "删除", response = ApiResult.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<Boolean> deletePatrolGpsRealtime(@PathVariable("id") Long id) throws Exception {
//        boolean flag = patrolGpsRealtimeService.deletePatrolGpsRealtime(id);
//        return ApiResult.result(flag);
//    }

//    /**
//     * 获取
//     */
//    @GetMapping("/info/{id}")
//    @ApiOperation(value = "获取PatrolGpsRealtime对象详情", notes = "查看", response = PatrolGpsRealtimeQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<PatrolGpsRealtimeQueryVo> getPatrolGpsRealtime(@PathVariable("id") Long id) throws Exception {
//        PatrolGpsRealtimeQueryVo patrolGpsRealtimeQueryVo = patrolGpsRealtimeService.getPatrolGpsRealtimeById(id);
//        return ApiResult.ok(patrolGpsRealtimeQueryVo);
//    }

//    /**
//     * 分页列表
//     */
//    @PostMapping("/getPageList")
//    @ApiOperation(value = "获取PatrolGpsRealtime分页列表", notes = "分页列表", response = PatrolGpsRealtimeQueryVo.class)
//    public ApiResult<Paging<PatrolGpsRealtimeQueryVo>> getPatrolGpsRealtimePageList(@Valid @RequestBody PatrolGpsRealtimeQueryParam patrolGpsRealtimeQueryParam) throws Exception {
//        Paging<PatrolGpsRealtimeQueryVo> paging = patrolGpsRealtimeService.getPatrolGpsRealtimePageList(patrolGpsRealtimeQueryParam);
//        return ApiResult.ok(paging);
//    }

    /**
     * 查询状态为工作中
     */
    @ApiOperation(value = "查询状态为工作中")
    @GetMapping(value = "/byState")
    public ApiResult<PatrolGpsRealtimeQueryVo> selectAtWork() throws Exception {
        List<PatrolGpsRealtimeQueryVo> list = patrolGpsRealtimeService.selectAtWork();
        return ApiResult.ok(list);
    }



}

