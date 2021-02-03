package com.wavenet.maintenance.web;

import com.wavenet.maintenance.dao.entity.SludgeSite;
import com.wavenet.maintenance.service.SludgeSiteService;

import com.wavenet.maintenance.web.vo.SludgeSiteQueryVo;
import com.wavenetframework.boot.common.api.ApiResult;

import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
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
 * @since 2021-02-02
 */
@Slf4j
@RestController
@RequestMapping("/sludgeSite")
@Api(tags="污泥站点")
public class SludgeSiteController extends BaseController {

    @Autowired
    private SludgeSiteService sludgeSiteService;



    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SludgeSite分页列表", notes = "分页列表", response = SludgeSiteQueryVo.class)
    public ApiResult<Paging<SludgeSiteQueryVo>> getSludgeSitePageList(@Valid @RequestBody SludgeSiteQueryVo sludgeSiteQueryParam) throws Exception {
        List<SludgeSiteQueryVo> paging = sludgeSiteService.getSludgeSitePageList(sludgeSiteQueryParam);
        return ApiResult.ok(paging);
    }

}

