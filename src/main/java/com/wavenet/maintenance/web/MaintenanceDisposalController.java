package com.wavenet.maintenance.web;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.dao.entity.EventInfo;
import com.wavenet.maintenance.web.query.MaintenanceDisposalQueryParams;
import com.wavenet.maintenance.web.vo.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wavenet.maintenance.dao.entity.MaintenanceDisposal;
import com.wavenet.maintenance.service.MaintenanceDetailService;
import com.wavenet.maintenance.service.MaintenanceDisposalService;
import com.wavenet.maintenance.web.query.MaintenanceDisposalQueryParam;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import com.wavenetframework.boot.common.vo.Paging;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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
@RequestMapping("/maintenanceDisposal")
@Api(tags = "疏通养护处置表")
@CrossOrigin
public class MaintenanceDisposalController extends BaseController {

    @Autowired
    private MaintenanceDisposalService maintenanceDisposalService;

//    /**
//     * 添加
//     */
//    @PostMapping("/add")
//    @ApiOperation(value = "添加MaintenanceDisposal对象", notes = "添加", response = ApiResult.class)
//    public ApiResult<String> addMaintenanceDisposal(@Valid @RequestBody MaintenanceDisposal maintenanceDisposal) throws Exception {
//        String flag = maintenanceDisposalService.saveMaintenanceDisposal(maintenanceDisposal);
//        if (flag.isEmpty()) {
//            return ApiResult.fail();
//        }else {
//            return ApiResult.ok(flag);
//        }
//
//    }
//
//    /**
//     * 修改
//     */
//    @PostMapping("/update")
//    @ApiOperation(value = "修改MaintenanceDisposal对象", notes = "修改", response = ApiResult.class)
//    public ApiResult<Boolean> updateMaintenanceDisposal(@Valid @RequestBody MaintenanceDisposal maintenanceDisposal) throws Exception {
//        boolean flag = maintenanceDisposalService.updateMaintenanceDisposal(maintenanceDisposal);
//        return ApiResult.result(flag);
//    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除MaintenanceDisposal对象", notes = "删除", response = ApiResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<Boolean> deleteMaintenanceDisposal(@PathVariable("id") String id) throws Exception {
        boolean flag = maintenanceDisposalService.deleteMaintenanceDisposal(id);
        return ApiResult.result(flag);
    }

//    /**
//     * 获取
//     */
//    @GetMapping("/info/{id}")
//    @ApiOperation(value = "获取MaintenanceDisposal对象详情", notes = "查看", response = MaintenanceDisposalQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<MaintenanceDisposalQueryVo> getMaintenanceDisposal(@PathVariable("id") String id) throws Exception {
//        MaintenanceDisposalQueryVo maintenanceDisposalQueryVo = maintenanceDisposalService.getMaintenanceDisposalById(id);
//        return ApiResult.ok(maintenanceDisposalQueryVo);
//    }

    /**
     * 分页列表
     */
    @PostMapping ("/getPageList")
    @ApiOperation(value = "获取MaintenanceDisposal分页列表", notes = "分页列表", response = MaintenanceDisposalQueryVo.class)
    // 新的
    public ApiResult<Paging<MaintenanceDisposalQueryVo>> getMaintenanceDisposalPageList(@Valid @RequestBody MaintenanceDisposalQueryParams maintenanceDisposalQueryParams) throws Exception {
        Paging<MaintenanceDisposalQueryVo> paging = maintenanceDisposalService.getMaintenanceDisposalPageList(maintenanceDisposalQueryParams);
        return ApiResult.ok(paging);
    }
    /**
     * 分页列表
     */
    @PostMapping ("/getPageLists")
    @ApiOperation(value = "获取MaintenanceDisposal分页列表", notes = "分页列表", response = MaintenanceDisposalQueryVo.class)
    // 新的
    public ApiResult<Paging<MaintenanceDisposalQueryVo>> getMaintenanceDisposalPageLists(@Valid @RequestBody MaintenanceDisposalQueryParam maintenanceDisposalQueryParam) throws Exception {
        Paging<MaintenanceDisposalQueryVo> paging = maintenanceDisposalService.getMaintenanceDisposalPageLists(maintenanceDisposalQueryParam);
        return ApiResult.ok(paging);
    }
//    /**
//     * 分页列表12-22 做更改预留
//     */
//    @PostMapping ("list/getPageListNew")
//    @ApiOperation(value = "获取MaintenanceDisposal分页列表", notes = "分页列表", response = MaintenanceDisposalQueryVo.class)
//    // 新的
//    public ApiResult<Paging<MaintenanceDisposalQueryVo>> getPageListNew(@Valid @RequestBody MaintenanceDisposalQueryParam maintenanceDisposalQueryParam) throws Exception {
//        Paging<MaintenanceDisposalQueryVo> paging = maintenanceDisposalService.getPageListNew(maintenanceDisposalQueryParam);
//        return ApiResult.ok(paging);
//    }

    /**
     * 查询长度总和
     */
    @PostMapping ("/getLength")
    @ApiOperation(value = "获取长度总和", notes = "分页列表", response = MaintenanceDisposalQueryVo.class)
    public ApiResult<MaintenanceDisposalCountVo> getLengthCount(@Valid @RequestBody MaintenanceDisposalQueryParams maintenanceDisposalQueryParam) throws Exception {
        MaintenanceDisposalCountVo count = maintenanceDisposalService.getLengthCount(maintenanceDisposalQueryParam);
        return ApiResult.ok(count);
    }
    /**
     * 查询长度总和 12-22 做更改预留
     */
//    @PostMapping ("/getLengths")
//    @ApiOperation(value = "获取长度总和", notes = "分页列表", response = MaintenanceDisposalQueryVo.class)
//    public ApiResult<MaintenanceDisposalCountVo> getLengthCounts(@Valid @RequestBody MaintenanceDisposalQueryParams maintenanceDisposalQueryParam) throws Exception {
//        MaintenanceDisposalCountVo count = maintenanceDisposalService.getLengthCounts(maintenanceDisposalQueryParam);
//        return ApiResult.ok(count);
//    }
//


    /**
     * 根据disposal_code修改工程量
     */
    @PostMapping ("/realLength")
    @ApiOperation(value = "根据disposal_code修改工程量")
    public ApiResult<Integer> updateLengthByDisposalCode(@Valid @RequestBody MaintenanceDisposal maintenanceDisposal) throws Exception {
        ApiResult<Integer> i = maintenanceDisposalService.updateLengthByDisposalCode(maintenanceDisposal);
        return ApiResult.ok(i);
    }

    /**
     * 点击取消状态回退
     */
    @PostMapping("/statusBack")
    @ApiOperation(value = "点击取消状态回退")
    public ApiResult<Boolean> statusBack(@Valid @RequestBody MaintenanceDisposal maintenanceDisposal) throws Exception {
        boolean b = maintenanceDisposalService.statusBack(maintenanceDisposal);
        return ApiResult.ok(b);
    }

    @PostMapping("/inquireReview")
    @ApiOperation(value = "查询可审查得")
    public ApiResult<?> statusBack(@RequestBody PgeVoParmterVo parmterVo) throws Exception {

         PageInfo<MaintenanceDisposalCheckSpitVo> eventDisposalWorkloads = maintenanceDisposalService.inquireReview(parmterVo);
        return ApiResult.ok(eventDisposalWorkloads);
    }


    /**
     * 详情接口 MAINTENANCE_DETAIL 做主表关联 MAINTENANCE_DISPOSAL
     */
    @GetMapping("getMaintenanceDisposalByCode")
    @ApiOperation(value = "详情接口 MAINTENANCE_DETAIL 做主表关联 MAINTENANCE_DISPOSAL", notes = "查看", response = MaintenanceDisposalQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<MaintenanceDisposalQueryVo> getMaintenanceDisposalByCode(String id) throws Exception {
        MaintenanceDisposalQueryVo maintenanceDisposalQueryVo = maintenanceDisposalService.getMaintenanceDisposalByCode(id);
        return ApiResult.ok(maintenanceDisposalQueryVo);
    }

    /**
     * 修改 MAINTENANCE_DISPOSAL 12.2
     */
    @PostMapping("/updateDisposal")
    @ApiOperation(value = "修改 MAINTENANCE_DISPOSAL", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateDisposal(@Valid @RequestBody MaintenanceDisposal maintenanceDisposal) throws Exception {
        boolean flag = maintenanceDisposalService.updateDisposal(maintenanceDisposal);
        return ApiResult.result(flag);
    }



}


