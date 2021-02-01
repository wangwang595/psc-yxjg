package com.wavenet.maintenance.web;

import cn.jpush.api.push.PushResult;
import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.EventInfo;
import com.wavenet.maintenance.dao.entity.PatrolInfo;
import com.wavenet.maintenance.dao.entity.PatrolInfoS;
import com.wavenet.maintenance.dao.mapper.PatrolInfoMapper;
import com.wavenet.maintenance.service.PatrolInfoService;
import com.wavenet.maintenance.web.query.PatrolInfoObjectQuerParam;
import com.wavenet.maintenance.web.query.PatrolInfoQueryParam;
import com.wavenet.maintenance.web.query.QueryParameterObjectPage;
import com.wavenet.maintenance.web.query.QueryParameterQueryPage;
import com.wavenet.maintenance.web.vo.*;
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
import java.util.Map;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
@Slf4j
@RestController
@RequestMapping("/patrolInfo")
@Api(tags = "巡查表")
@CrossOrigin
public class PatrolInfoController extends BaseController {

    @Autowired
    private PatrolInfoService patrolInfoService;
    @Autowired
    private PatrolInfoMapper patrolInfoMapper;

    /**
     * 添加
     */
    @PostMapping("/add")
//    @RequiresPermissions("patrol:info:add")
    @ApiOperation(value = "添加PatrolInfo对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addPatrolInfo(@Valid @RequestBody PatrolInfo patrolInfo) throws Exception {
        String flag = patrolInfoService.savePatrolInfo(patrolInfo);
        if (flag.isEmpty()) {
            return ApiResult.fail();
        } else {
            return ApiResult.ok(flag);
        }

    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("patrol:info:update")
    @ApiOperation(value = "修改PatrolInfo对象", notes = "修改", response = ApiResult.class)
    public ApiResult<PushResult> updatePatrolInfo(@Valid @RequestBody PatrolInfo patrolInfo) throws Exception {
        PushResult pushResult = patrolInfoService.updatePatrolInfo(patrolInfo);
        return ApiResult.ok(pushResult);
    }

//    /**
//     * 删除
//     */
//    @PostMapping("/delete/{id}")
////    @RequiresPermissions("patrol:info:delete")
//    @ApiOperation(value = "删除PatrolInfo对象", notes = "删除", response = ApiResult.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<Boolean> deletePatrolInfo(@PathVariable("id") Long id) throws Exception {
//        boolean flag = patrolInfoService.deletePatrolInfo(id);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 获取
//     */
//    @GetMapping("/info/{id}")
////    @RequiresPermissions("patrol:info:info")
//    @ApiOperation(value = "获取PatrolInfo对象详情", notes = "查看", response = PatrolInfoQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<PatrolInfoQueryVo> getPatrolInfo(@PathVariable("id") Long id) throws Exception {
//        PatrolInfoQueryVo patrolInfoQueryVo = patrolInfoService.getPatrolInfoById(id);
//        return ApiResult.ok(patrolInfoQueryVo);
//    }

//    /**
//     * 分页列表
//     */
//    @PostMapping("/getPageList")
////    @RequiresPermissions("patrol:info:page")
//    @ApiOperation(value = "获取PatrolInfo分页列表", notes = "分页列表", response = PatrolInfoQueryVo.class)
//    public ApiResult<Paging<PatrolInfoQueryVo>> getPatrolInfoPageList(@Valid @RequestBody PatrolInfoQueryParam patrolInfoQueryParam) throws Exception {
//        Paging<PatrolInfoQueryVo> paging = patrolInfoService.getPatrolInfoPageList(patrolInfoQueryParam);
//        return ApiResult.ok(paging);
//    }


//    /**
//     * 根据patrol_code修改工程量
//     */
//    @PostMapping("/realMileage")
//    @ApiOperation(value = "根据patrol_code修改工程量")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "patrolCode",value = "巡查编号"),
//            @ApiImplicitParam(name = "realMileage",value = "实际巡查长度")
//    })
//    public ApiResult<Integer> updateMileageByPatrolCode(String patrolCode, String realMileage) throws Exception {
//        ApiResult<Integer> i = patrolInfoService.updateMileageByPatrolCode(patrolCode, realMileage);
//        return ApiResult.ok(i);
//    }

    @GetMapping("/patrolAndEventInfo")
    @ApiOperation(value = "根据patrolCode获取PatrolInfo和EventInfo对象详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patrolCode",value = "巡查编号")
    })
    public ApiResult<PatrolInfoAndEventInfoQueryVo> selectInfoByPatrolCode(String patrolCode) {
        PatrolInfoAndEventInfoQueryVo data = patrolInfoService.selectInfoByPatrolCode(patrolCode);
        return ApiResult.ok(data);
    }



    @PostMapping("/pAcquiredQuantity")
    @ApiOperation(value = "时间和项目组获取相应的数量")
    public ApiResult<?> pAcquiredQuantity(@RequestBody PatrolInfoObjectQuerParam patrolInfoObjectQuerParam) throws Exception {
        MileagesFreVOs mileagesFreVO = patrolInfoService.pAcquiredQuantity(patrolInfoObjectQuerParam);
        return ApiResult.ok(mileagesFreVO);
    }
    @PostMapping("/listInformationFrequency")
    @ApiOperation(value = "获取巡查管理:巡查次数")
    public ApiResult<?> listInformationFrequency(@Valid @RequestBody QueryParameterObjectPage queryParameter) throws Exception {

        PageInfo<PatrolInfoS> paging = patrolInfoService.listInformation(queryParameter);
        return ApiResult.ok(paging);
    }

    @PostMapping("/listInformationQuer")
    @ApiOperation(value = "获取巡查管理:发现问题")
    public ApiResult<?> listInformationQuer(@Valid @RequestBody QueryParameterQueryPage queryParameter) throws Exception {

        PageInfo<EventInfo> paging = patrolInfoService.listInformationQuer(queryParameter);
        return ApiResult.ok(paging);
    }

//    @GetMapping("/getNumberQuestions")
//    @ApiOperation(value = "根据patrolCode查询问题数")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "patrolCode",value = "巡查编号")
//    })
//    public ApiResult<PatrolInfoAndEventInfoQueryVo> getNumberQuestions(String patrolCode) {
//        Integer data = patrolInfoMapper.getNumberQuestions(patrolCode);
//
//        return ApiResult.ok(data);
//    }

    @GetMapping("getInfoById")
//    @RequiresPermissions("patrol:info:info")
    @ApiOperation(value = "获取PatrolInfo对象详情", notes = "查看", response = PatrolInfoQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<PatrolInfoS> getInfoById(String id) throws Exception {
        PatrolInfoS patrolInfoQueryVo = patrolInfoService.getInfoById(id);


        return ApiResult.ok(patrolInfoQueryVo);

    }

    @GetMapping("list/statisticCount")
    @ApiOperation(value = "PatrolInfo分类统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "times",value = "时间"),
            @ApiImplicitParam(name = "type",value = "人员类型"),
            @ApiImplicitParam(name = "district",value = "区域"),

    })
    public ApiResult<?> statisticCount(String times,String type,String district) throws Exception {
        PratolInfooCountListVo patrolInfoQueryVo = patrolInfoService.statisticCount(times,type,district);


        return ApiResult.ok(patrolInfoQueryVo);

    }

}

