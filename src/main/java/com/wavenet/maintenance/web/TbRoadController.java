package com.wavenet.maintenance.web;

import com.wavenet.maintenance.dao.entity.TbRoad;
import com.wavenet.maintenance.service.TbRoadService;
import com.wavenet.maintenance.web.query.TbRoadQueryParam;
import com.wavenet.maintenance.web.vo.TbRoadQueryVo;
import com.wavenetframework.boot.common.api.ApiResult;

import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import com.wavenetframework.boot.common.vo.Paging;
import com.wavenetframework.boot.common.param.IdParam;

import java.util.List;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author zll
 * @since 2020-04-18
 */
@Slf4j
@RestController
@RequestMapping("/tbRoad")
@Api(tags = "城市道路表")
@CrossOrigin
public class TbRoadController extends BaseController {

    @Autowired
    private TbRoadService tbRoadService;

//    /**
//     * 添加
//     */
//    @PostMapping("/add")
//    @RequiresPermissions("tb:road:add")
//    @ApiOperation(value = "添加TbRoad对象", notes = "添加", response = ApiResult.class)
//    public ApiResult<Boolean> addTbRoad(@Valid @RequestBody TbRoad tbRoad) throws Exception {
//        boolean flag = tbRoadService.saveTbRoad(tbRoad);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 修改
//     */
//    @PostMapping("/update")
//    @RequiresPermissions("tb:road:update")
//    @ApiOperation(value = "修改TbRoad对象", notes = "修改", response = ApiResult.class)
//    public ApiResult<Boolean> updateTbRoad(@Valid @RequestBody TbRoad tbRoad) throws Exception {
//        boolean flag = tbRoadService.updateTbRoad(tbRoad);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 删除
//     */
//    @PostMapping("/delete/{id}")
//    @RequiresPermissions("tb:road:delete")
//    @ApiOperation(value = "删除TbRoad对象", notes = "删除", response = ApiResult.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<Boolean> deleteTbRoad(@PathVariable("id") Long id) throws Exception {
//        boolean flag = tbRoadService.deleteTbRoad(id);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 获取
//     */
//    @GetMapping("/info/{id}")
//    @RequiresPermissions("tb:road:info")
//    @ApiOperation(value = "获取TbRoad对象详情", notes = "查看", response = TbRoadQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<TbRoadQueryVo> getTbRoad(@PathVariable("id") Long id) throws Exception {
//        TbRoadQueryVo tbRoadQueryVo = tbRoadService.getTbRoadById(id);
//        return ApiResult.ok(tbRoadQueryVo);
//    }
//
//    /**
//     * 分页列表
//     */
//    @PostMapping("/getPageList")
//    @RequiresPermissions("tb:road:page")
//    @ApiOperation(value = "获取TbRoad分页列表", notes = "分页列表", response = TbRoadQueryVo.class)
//    public ApiResult<Paging<TbRoadQueryVo>> getTbRoadPageList(@Valid @RequestBody TbRoadQueryParam tbRoadQueryParam) throws Exception {
//        Paging<TbRoadQueryVo> paging = tbRoadService.getTbRoadPageList(tbRoadQueryParam);
//        return ApiResult.ok(paging);
//    }
//
//
//    @GetMapping("/getRoadList")
//    @ApiOperation(value = "获取所有的路段名", notes = "获取所有的路段名", response = TbRoadQueryVo.class)
//    public ApiResult<List<String>> getTbRoadPageList() throws Exception {
//        List<String> list = tbRoadService.getRoadName();
//        return ApiResult.ok(list);
//    }
//}
}
