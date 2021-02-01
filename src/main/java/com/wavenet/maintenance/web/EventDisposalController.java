package com.wavenet.maintenance.web;

import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wavenet.maintenance.dao.entity.EventDisposal;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.dao.entity.ImagesDisposal;
import com.wavenet.maintenance.service.EventDispatchService;
import com.wavenet.maintenance.service.EventDisposalService;
import com.wavenet.maintenance.service.EventDisposalWorkloadService;
import com.wavenet.maintenance.service.EventInfoService;
import com.wavenet.maintenance.web.query.DisposalAndWorkloadParam;
import com.wavenet.maintenance.web.query.EventDisposalQueryParam;
import com.wavenet.maintenance.web.query.EventDisposalWorkQueryParam;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import com.wavenetframework.boot.common.vo.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

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
@RequestMapping("/eventDisposal")
@Api(tags ="事件道路巡查处置")
@CrossOrigin
public class EventDisposalController extends BaseController {

    @Autowired
    private EventDisposalService eventDisposalService;

    @Autowired
    private EventDisposalWorkloadService eventDisposalWorkloadService;

    @Autowired
    private EventInfoService eventInfoService;

    @Autowired
    private EventDispatchService eventDispatchService;





    /**
     * 添加
     */
    @PostMapping("/add")
//    @RequiresPermissions("event:disposal:add")
    @ApiOperation(value = "添加EventDisposal对象", notes = "添加", response = ApiResult.class)
    public ApiResult<String> addEventDisposal(@Valid @RequestBody EventDisposal eventDisposal) throws Exception {
        String flag = eventDisposalService.saveEventDisposal(eventDisposal);
        return ApiResult.ok(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("event:disposal:update")
    @ApiOperation(value = "修改EventDisposal对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateEventDisposal(@RequestBody EventDisposalWorkQueryParam param) throws Exception {
        boolean flag = eventDisposalService.updateEventDisposal(param);
        return ApiResult.result(flag);
    }
    /**
     * 修改更新 张远方 12.1
     */
    @PostMapping("/modifyAndUpdate")
//    @RequiresPermissions("event:disposal:update")
    @ApiOperation(value = "修改EventDisposal对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> modifyAndUpdate(@RequestBody EventDisposalWorkQueryParam param) throws Exception {
        boolean flag = eventDisposalService.modifyAndUpdate(param);
        return ApiResult.result(flag);
    }



    /**
     * 获取
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("event:disposal:info")
    @ApiOperation(value = "获取处置信息详情", notes = "测试ID 0be8610b719634d455ff25ee6675330e", response = EventDisposalQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<EventDisposalQueryVo> getEventDisposal(@PathVariable("id") String id) throws Exception {
        EventDisposalQueryVo eventDisposalQueryVo = eventDisposalService.getEventDisposalById(id);
        List<EventDisposalWorkloadQueryVo> list = eventDisposalWorkloadService.selectByDisposalCode(id);
        Object[] obj = {eventDisposalQueryVo,list};
        return ApiResult.ok(obj);
    }

//    /**
//     * 分页列表
//     */
//    @PostMapping("/getPageList")
////    @RequiresPermissions("event:disposal:page")
//    @ApiOperation(value = "获取EventDisposal分页列表", notes = "分页列表", response = EventDisposalQueryVo.class)
//    public ApiResult<Paging<EventDisposalQueryVo>> getEventDisposalPageList(@Valid @RequestBody EventDisposalQueryParam eventDisposalQueryParam) throws Exception {
//        Paging<EventDisposalQueryVo> paging = eventDisposalService.getEventDisposalPageList(eventDisposalQueryParam);
//        return ApiResult.ok(paging);
//    }
//    /**
//     * 获取
//     */
//    @GetMapping("/getEventInfo/{id}")
////    @RequiresPermissions("event:disposal:info")
//    @ApiOperation(value = "获取报送信息象详情", notes = "测试ID 5e5e72390373b503eb895439240e66b9", response = EventDisposalQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<EventDisposalQueryVo> getEventInfo(@PathVariable("id") String id) throws Exception {
//        EventDispatchQueryVo eventDispatchQueryVo = eventDispatchService.findById(id);
//        List<EventInfoQueryVo> list = eventInfoService.findById(id);
//        //第一个放的是处置信息，第二个放的是事件信息
//        Object[] obj = {eventDispatchQueryVo,list};
//        return ApiResult.ok(obj);
//    }

//    /**
//     * 根据id查询disposal
//     */
//    @GetMapping("/byId/{id}")
////    @RequiresPermissions("event:disposal:info")
//    @ApiOperation(value = "获取处置信息详情", notes = "测试ID 0be8610b719634d455ff25ee6675330e", response = EventDisposalQueryVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<EventDisposalQueryVo> getDisposalById(@PathVariable("id") String id) throws Exception {
//        EventDisposalQueryVo eventDisposalQueryVo = eventDisposalService.getEventDisposalById(id);
//
//        return ApiResult.ok(eventDisposalQueryVo);
//    }

//    /**
//     * 修改
//     */
//    @PostMapping("/update1")
////    @RequiresPermissions("event:disposal:update")
//    @ApiOperation(value = "修改EventDisposal对象", notes = "修改", response = ApiResult.class)
//    public ApiResult<Boolean> updateDisposal(@RequestBody EventDisposal param) throws Exception {
//        boolean flag = eventDisposalService.updateDisposal(param);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 修改New
//     */
//    @PostMapping("/update1New")
////    @RequiresPermissions("event:disposal:update")
//    @ApiOperation(value = "修改EventDisposalNew对象", notes = "修改New", response = ApiResult.class)
//    public ApiResult<Boolean> updateDisposalNew(@RequestBody EventDisposal param) throws Exception {
//        boolean flag = eventDisposalService.updateDisposalNew(param);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 逻辑删除
//     */
//    @GetMapping("/isDel")
//    @ApiOperation(value = "逻辑删除", notes = "逻辑删")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "disposalCode",value = "处置主键")
//    })
//    public ApiResult<Boolean> updateIsDel(String disposalCode) throws Exception {
//        boolean flag = eventDisposalService.updateIsDel(disposalCode);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 修改disposal同时新增工作量
//     */
//    @PostMapping("/disposalAndWorkload")
//    @ApiOperation(value = "修改disposal和新增workload(合并)")
//    public ApiResult<Boolean> updateDisposalAndInsertWorkLoad(@RequestBody DisposalAndWorkloadParam disposalAndWorkloadParam) throws Exception {
//        eventDisposalWorkloadService.saveWorkload(disposalAndWorkloadParam.getEventDisposalWorkloads());
//        boolean flag = eventDisposalService.updateDisposalNew(disposalAndWorkloadParam.getEventDisposal());
//        return ApiResult.result(flag);
//    }

    /**
     * 根据dispatchCode查询disposal表
     */
    @GetMapping("/getDisposalById")
    @ApiOperation(value = "根据dispatchCode查询disposal表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dispatchCode",value = "派遣编号")
    })
    public ApiResult<EventDisposalQueryVo> selectDisposalByDispatchCode(String dispatchCode) throws Exception {
        EventDisposalQueryVo eventDisposalQueryVo = eventDisposalService.selectDisposalByDispatchCode(dispatchCode);
        return ApiResult.ok(eventDisposalQueryVo);
    }


}

