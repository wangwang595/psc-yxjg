package com.wavenet.maintenance.web;


import com.wavenet.maintenance.dao.entity.AppVersion;
import com.wavenet.maintenance.dao.entity.OrgDept;
import com.wavenet.maintenance.service.AppVersionService;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author zll
 * @since 2020-04-29
 */
@Slf4j
@RestController
@RequestMapping("/appVersion")
@Api(tags = "APP版本表（放入APP版本信息）")
@CrossOrigin
public class AppVersionController extends BaseController {

    @Autowired
    private AppVersionService appVersionService;


    /**
     * 根据模块查询最新数据  app 调用
     */
    @GetMapping("/newData")
    @ApiOperation(value = "获取AppVersion分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modular",value = "模块")
    })
    public ApiResult<?> selectNewData( String modular)  {
        AppVersion list = appVersionService.selectNewData(modular);
        return ApiResult.ok(list);
    }

    // 调用权限模块 获取 区域 街镇


    public List<OrgDept> areaList( ) throws Exception {
        List<OrgDept> list1 = appVersionService.areaList();
        return list1;
    }
}

