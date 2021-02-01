package com.wavenet.maintenance.web;

import com.wavenet.maintenance.dao.entity.EventDispatch;
import com.wavenet.maintenance.dao.entity.EventDispatchParam;
import com.wavenet.maintenance.dao.entity.PushInfo;
import com.wavenet.maintenance.service.EventDispatchService;
import com.wavenet.maintenance.service.PushService;
import com.wavenet.maintenance.web.query.DispatchParam;
import com.wavenet.maintenance.web.query.EventDispatchQueryParam;
import com.wavenet.maintenance.web.query.EventDisposalQueryParam;
import com.wavenet.maintenance.web.query.EventInfoQueryParamCopy;
import com.wavenet.maintenance.web.vo.EventDispatchQueryVo;
import com.wavenet.maintenance.web.vo.EventDispatchYhInfoVo;
import com.wavenet.maintenance.web.vo.EventDisposalQueryVo;
import com.wavenet.maintenance.web.vo.RoadVo;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import com.wavenetframework.boot.common.vo.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
@RequestMapping("/eventDispatch")
@Api(tags = "事件派遣表")
@CrossOrigin
public class


EventDispatchController extends BaseController {

    @Autowired
    private EventDispatchService eventDispatchService;




    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("event:dispatch:update")
    @ApiOperation(value = "修改EventDispatch对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateEventDispatch(@Valid @RequestBody EventDispatch eventDispatch) throws Exception {
        boolean flag = eventDispatchService.updateEventDispatch(eventDispatch);
        return ApiResult.result(flag);
    }


    /**
     * 分页列表 web
     */
    @PostMapping("/getPageList")
//    @RequiresPermissions("event:dispatch:page")
    @ApiOperation(value = "获取EventDispatch分页列表", notes = "分页列表", response = EventDispatchQueryVo.class)
    public ApiResult<Paging<EventDispatchQueryVo>> getEventDispatchPageList(@Valid @RequestBody EventDispatchQueryParam eventDispatchQueryParam) throws Exception {
        Paging<EventDispatchQueryVo> paging = eventDispatchService.getEventDispatchPageList(eventDispatchQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 养护详情页面 web
     */
    @ApiOperation(value = "养护详情")
    @PostMapping("/yhInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dispatchCode",value = "时间编码"),
            @ApiImplicitParam(name = "disposalCode",value = "处置编码")
    })
    public EventDispatchYhInfoVo getYhInfo(String dispatchCode, String disposalCode ) throws Exception {
        EventDispatchYhInfoVo list = eventDispatchService.getYhInfo(dispatchCode, disposalCode);
        return list;
    }


}

