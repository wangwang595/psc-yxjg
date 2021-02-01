package com.wavenet.maintenance.web;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.dao.entity.MaintenanceDetails;
import com.wavenet.maintenance.dao.entity.MaintenancePlan;
import com.wavenet.maintenance.dao.mapper.MaintenancePlanMapper;
import com.wavenet.maintenance.service.MaintenancePlanService;
import com.wavenet.maintenance.web.query.*;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.api.ApiResult;

import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
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
 * @since 2020-04-22
 */
@Slf4j
@RestController
@RequestMapping("/maintenancePlan")
@Api(tags = "疏通养护计划表")
@CrossOrigin
public class MaintenancePlanController extends BaseController {

    @Autowired
    private MaintenancePlanService maintenancePlanService;

    @Autowired
    private MaintenancePlanMapper maintenancePlanMapper;

//    /**
//     * 添加
//     */
//    @PostMapping("/add")
////    @RequiresPermissions("maintenance:plan:add")
//    @ApiOperation(value = "添加MaintenancePlan对象", notes = "添加", response = ApiResult.class)
//    public ApiResult<Boolean> addMaintenancePlan(@Valid @RequestBody MaintenancePlan maintenancePlan) throws Exception {
//        boolean flag = maintenancePlanService.saveMaintenancePlan(maintenancePlan);
//        return ApiResult.result(flag);
//    }

//    /**
//     * 修改
//     */
//    @PostMapping("/update")
////    @RequiresPermissions("maintenance:plan:update")
//    @ApiOperation(value = "修改MaintenancePlan对象", notes = "修改", response = ApiResult.class)
//    public ApiResult<Boolean> updateMaintenancePlan(@Valid @RequestBody MaintenancePlan maintenancePlan) throws Exception {
//        boolean flag = maintenancePlanService.updateMaintenancePlan(maintenancePlan);
//        return ApiResult.result(flag);
//    }

//    /**
//     * 删除
//     */
//    @PostMapping("/delete/{id}")
////    @RequiresPermissions("maintenance:plan:delete")
//    @ApiOperation(value = "删除MaintenancePlan对象", notes = "删除", response = ApiResult.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<Boolean> deleteMaintenancePlan(@PathVariable("id") Long id) throws Exception {
//        boolean flag = maintenancePlanService.deleteMaintenancePlan(id);
//        return ApiResult.result(flag);
//    }

//    /**
//     * 获取
//     */
//    @GetMapping("/info/{id}")
////    @RequiresPermissions("maintenance:plan:info")
//    @ApiOperation(value = "获取MaintenancePlan对象详情", notes = "查看", response = MaintenancePlanQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<MaintenancePlanQueryVo> getMaintenancePlan(@PathVariable("id") Long id) throws Exception {
//        MaintenancePlanQueryVo maintenancePlanQueryVo = maintenancePlanService.getMaintenancePlanById(id);
//        return ApiResult.ok(maintenancePlanQueryVo);
//    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
//    @RequiresPermissions("maintenance:plan:page")
    @ApiOperation(value = "获取MaintenancePlan分页列表", notes = "分页列表", response = MaintenancePlanQueryVo.class)
    public ApiResult<Paging<MaintenancePlanQueryVo>> getMaintenancePlanPageList(@Valid @RequestBody MaintenancePlanQueryParam maintenancePlanQueryParam) throws Exception {
        Paging<MaintenancePlanQueryVo> paging = maintenancePlanService.getMaintenancePlanPageList(maintenancePlanQueryParam);
        return ApiResult.ok(paging);
    }

    @PostMapping("/sewerInformation")
    @ApiOperation(value = "根据时间和项目组获取数量")
    public ApiResult<?> sewerInformation(@Valid @RequestBody PatrolInfoObjectQuerParam queryParameter) throws  Exception{

        MannitVo pAcquiredQuantity = maintenancePlanService.sewerInformation(queryParameter);
        return ApiResult.ok(pAcquiredQuantity);
    }

    @PostMapping("/dataInsertion")
    @ApiOperation(value = "数据插入")
    public ApiResult<?> dataInsertion(@Valid @RequestBody InsertManVo insertManVo) throws  Exception{

        Boolean aBoolean = maintenancePlanService.dataInsertion(insertManVo);
        return ApiResult.ok(aBoolean);
    }


//    @GetMapping("getPlan")
////    @RequiresPermissions("maintenance:plan:info")
//    @ApiOperation(value ="获取计划列表", notes = "查看", response = MaintenancePlanQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "year",value = "年"),
//            @ApiImplicitParam(name = "month",value = "月"),
//            @ApiImplicitParam(name = "project",value = "项目组"),
//            @ApiImplicitParam(name = "task",value = "计划内外"),
//            @ApiImplicitParam(name = "area",value = "片区")
//
//    })
//    public ApiResult<?> getPlan(String year,String month,String project,String task,String  area) throws Exception {
//        List<MaintenanceDetailPercenvO> maintenancePlanQueryVo = maintenancePlanService.getPlan(year,month,project,task,area);
//        return ApiResult.ok(maintenancePlanQueryVo);
//    }
    // 张远方 调
    @PostMapping("getPlans")
    @ApiOperation(value ="获取计划列表")
    public ApiResult<?> getPlans(@RequestBody PlansVo plansVo) throws Exception {
        PageInfo<MaintenanceDetailPercenvO> maintenancePlanQueryVo = maintenancePlanService.getPlans(plansVo);
        return ApiResult.ok(maintenancePlanQueryVo);
    }

    @PostMapping("list")
    @ApiOperation(value ="获取计划列表", notes = "查看", response = MaintenancePlanQueryVo.class)
    public ApiResult<?> getListPlan(@Valid @RequestBody QueryParameterObjectPageMainten maintenanceDetailQueryParam) throws Exception {
        PageInfo<MaintenanceDetails> maintenancePlanQueryVo = maintenancePlanService.getListPlan(maintenanceDetailQueryParam);
        return ApiResult.ok(maintenancePlanQueryVo);
    }

    @GetMapping("monthPlan")
    @ApiOperation(value ="根据年月计划查询列表", notes = "查看", response = MaintenancePlanQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",value = "年"),
            @ApiImplicitParam(name = "month",value = "月"),
            @ApiImplicitParam(name = "plan",value = "计划")
    })
    public ApiResult<?> monthPlan(String year,String month,String plan) throws Exception {
        List<MaintenanceDetail> maintenancePlanQueryVo =maintenancePlanMapper.monthPlan(year,month,plan);
        return ApiResult.ok(maintenancePlanQueryVo);
    }
    @GetMapping("getDetalList")
    @ApiOperation(value ="根据plancode查询detel表", notes = "查看", response = MaintenancePlanQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<?> getDetalList(String id) throws Exception {
        List<MaintenanceDetail> maintenancePlanQueryVo =maintenancePlanMapper.getDetalList(id);
        return ApiResult.ok(maintenancePlanQueryVo);
    }

    @PostMapping("DetalListUpdate")
    @ApiOperation(value ="根据plancode更新detel表", notes = "查看", response = MaintenancePlanQueryVo.class)
    public ApiResult<?> DetalListUpdate(@Valid @RequestBody DetalListUpdateVo maintenanceDetailQueryParam) throws Exception {
        Boolean flag =maintenancePlanService.DetalListUpdate(maintenanceDetailQueryParam);
        return ApiResult.ok(flag);
    }

    @GetMapping("deleteByplanCode")
    @ApiOperation(value ="根据plancode删除detel表", notes = "查看", response = MaintenancePlanQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<?> deleteByplanCode(String id) throws Exception {
        Boolean flag =maintenancePlanService.deleteByplanCode(id);
        return ApiResult.ok(flag);
    }
}

