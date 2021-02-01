package com.wavenet.maintenance.web;

import cn.jpush.api.push.PushResult;
import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.*;
import com.wavenet.maintenance.dao.mapper.EventInfoMapper;
import com.wavenet.maintenance.service.EventDisposalWorkloadService;
import com.wavenet.maintenance.service.EventInfoService;
import com.wavenet.maintenance.web.query.*;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.api.ApiResult;

import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import com.wavenetframework.boot.common.vo.Paging;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
@RequestMapping("/eventInfo")
@Api(tags ="事件详情表")
@CrossOrigin
public class EventInfoController extends BaseController {

    @Autowired
    private EventInfoService eventInfoService;
    @Autowired
    private EventDisposalWorkloadService eventDisposalWorkloadService;
    @Autowired
    private EventInfoMapper eventInfoMapper;

    @Autowired
    private AppVersionController appVersionController;

    private  static  final  String KEY="15c755b4845ffd3f6adfffec";
    private  static  final  String SECRET="f2701e28264cddd73dede39b";

//    /**
//     * 添加
//     */
//    @PostMapping("/add")
//    // @RequiresPermissions("event:info:add")
//    @ApiOperation(value = "添加EventInfo对象", notes = "添加", response = ApiResult.class)
//    public ApiResult<Boolean> addEventInfo(@Valid @RequestBody EventInfo eventInfo) throws Exception {
//        boolean flag = eventInfoService.saveEventInfo(eventInfo);
//        return ApiResult.result(flag);
//    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //  @RequiresPermissions("event:info:update")
    @ApiOperation(value = "修改EventInfo对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateEventInfo(@Valid @RequestBody EventInfo eventInfo) throws Exception {
        boolean flag = eventInfoService.updateEventInfo(eventInfo);
        return ApiResult.result(flag);
    }

//    /**
//     * 删除
//     */
//    @PostMapping("/delete/{id}")
//    //  @RequiresPermissions("event:info:delete")
//    @ApiOperation(value = "删除EventInfo对象", notes = "删除", response = ApiResult.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<Boolean> deleteEventInfo(@PathVariable("id") Long id) throws Exception {
//        boolean flag = eventInfoService.deleteEventInfo(id);
//        return ApiResult.result(flag);
//    }

    /**
     * 获取
     */
    @GetMapping("/info/{id}")
    //  @RequiresPermissions("event:info:info")
    @ApiOperation(value = "获取EventInfo对象详情", notes = "查看", response = EventInfoQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<EventInfoQueryVo> getEventInfo(@PathVariable("id") String id) throws Exception {
        EventInfoQueryVoPiager eventInfoQueryVo = eventInfoService.getEventInfoById(id);
        return ApiResult.ok(eventInfoQueryVo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    // @RequiresPermissions("event:info:page")
    @ApiOperation(value = "获取EventInfo分页列表", notes = "分页列表", response = EventInfoQueryVo.class)

    public ApiResult<Paging<EventInfoQueryVoStatus>> getEventInfoPageList(@Valid @RequestBody EventInfoQueryParam eventInfoQueryParam) throws Exception {
        Paging<EventInfoQueryVoStatus> paging = eventInfoService.getEventInfoPageList(eventInfoQueryParam);
        return ApiResult.ok(paging);
    }
    /**
     * 分页列表 web 多个街镇  多个类型
     */
    @PostMapping("/list-pageListWeb")
    // @RequiresPermissions("event:info:page")
    @ApiOperation(value = "获取EventInfo分页列表", notes = "分页列表", response = EventInfoQueryVo.class)

    public ApiResult<PageInfo<EventInfoQueryVoStatus>> pageListWeb(@Valid @RequestBody EventInfoQueryParamPage eventInfoQueryParam) throws Exception {
        PageInfo<EventInfoQueryVoStatus> paging = eventInfoService.pageListWeb(eventInfoQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 分页列表排序
     */
    @PostMapping("/list-getPageList")
    // @RequiresPermissions("event:info:page")
    @ApiOperation(value = "分页列表排序")
    public ApiResult<?> getPageLists(@Valid @RequestBody EventInfoQueryParamPage eventInfoQueryParam) throws Exception {
        PageInfo<EventInfoQueryVoStatus> paging = eventInfoService.getPageLists(eventInfoQueryParam);
        return ApiResult.ok(paging);
    }
    // 12.2 张 获取list单个详情
    @GetMapping("/getPageList/id")
    // @RequiresPermissions("event:info:page")
    @ApiOperation(value = "获取getpagelist单个详情", notes = "分页列表", response = EventInfoQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventCode",value = "事件主键")
    })
    public ApiResult<?> getEventInfoByCode(String eventCode) throws Exception {
        EventInfoQueryVoStatus paging = eventInfoService.getEventInfoByCode(eventCode);
        return ApiResult.ok(paging);
    }

//    @ApiOperation(value = "根据code关联查询")
//    @GetMapping("/byCode")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "eventCode",value = "事件主键")
//    })
//    public ApiResult<EventInfoQueryVo> selectInfoByCode(String code) throws Exception {
//        List<EventInfoQueryVo> list = eventInfoService.selectInfoByCode(code);
//        return ApiResult.ok(list);
//    }
//
//    @ApiOperation(value = "根据路段编号查询返回dispatch_code")
//    @GetMapping("/dispatch")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "eventCode",value = "事件主键")
//    })
//    public ApiResult<EventDisposalRel> selectDispatchCode(String code) throws Exception {
//        List<EventDisposalRel> list = eventInfoService.selectDispatchCode(code);
//        return ApiResult.ok(list);
//    }

//    @ApiOperation(value = "获取所有事件中包含的路段信息")
//    @GetMapping("/selectRoadList")
//    public ApiResult<List<RoadVo>> selectRoadList() throws Exception {
//        List<RoadVo> list = eventInfoService.selectRoadList();
//        return ApiResult.ok(list);
//    }

//
//    @ApiOperation(value = "根据路段code获取事件list")
//    @PostMapping("/selectEventInfoListByRoadSectionCode")
//    public ApiResult<List<EventInfo>> selectEventInfoListByRoadSectionCode(@Valid @RequestBody EventInfoQueryParamCopy eventInfoQueryParam) throws Exception {
//        List<EventInfo> list = eventInfoService.selectEventInfoListByRoadSectionCode(eventInfoQueryParam);
//        return ApiResult.ok(list);
//    }


    @PostMapping("/insertDispatch")
    @ApiOperation(value = "新建事件并建立关联关系", notes = "新增", response = ApiResult.class)
    public ApiResult<Boolean> insertInfoAndDispatch(@Valid @RequestBody EventInfo eventInfo, @RequestParam("endTime") String endTime) throws Exception {
        boolean flag = eventInfoService.insertEventInfo(eventInfo, endTime);
        return ApiResult.result(flag);
    }

    /**
     * 获取处置详情信息
     */
    @GetMapping("/disposalInfo/{code}")
    @ApiOperation(value = "获取处置信息详情", notes = "测试ID 0be8610b719634d455ff25ee6675330e", response = EventDisposalQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "处置主键")
    })
    public ApiResult<EventDisposalQueryVo> getEventDisposal(@PathVariable("code") String code) throws Exception {
        List<EventDisposalWorkloadQueryVo> list = null;
        EventDisposalQueryVo eventDisposalQueryVo = eventInfoService.getEventDisposalById(code);
        if (eventDisposalQueryVo != null) {
            String id = eventDisposalQueryVo.getDisposalCode();
            list = eventDisposalWorkloadService.findById(id);
        }
        Object[] obj = {eventDisposalQueryVo, list};


        return ApiResult.ok(obj);
    }

    /**
     * 根据dispatchCode查询eventInfo列表
     */
    @GetMapping("/eventList/{dispatchCode}")
    @ApiOperation(value = "根据dispatchCode查询eventInfo列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dispatchCode",value = "事件code")
    })
    public ApiResult<EventInfo> selectEventInfoByDispatchCode(@PathVariable("dispatchCode") String dispatchCode) throws Exception {
        List<EventInfo> list = eventInfoService.selectEventInfoByDispatchCode(dispatchCode);

        return ApiResult.ok(list);
    }

    /**
     * 新增病害信息同时新增派遣工单  app 调用
     */
    @PostMapping("/addEventInfoAndDispatch")
    @ApiOperation(value = "添加EventInfo和Dispatch")
    public ApiResult<?> saveEventInfoNew(@Valid @RequestBody AddEventInfoAndEventDispatch addEventInfoAndEventDispatch) throws Exception {
        String pushResult = eventInfoService.saveEventInfoNew(addEventInfoAndEventDispatch);
        return ApiResult.ok(pushResult);

    }

//    /**
//     * 根据personCode查询执行中的事件
//     */
//    @GetMapping("/selectByPersonCode")
//    @ApiOperation(value = "根据personCode查询执行中的事件")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "personCode",value = "用户编码")
//    })
//    public ApiResult<EventInfoQueryVo> selectByPersonCode(String personCode) throws Exception {
//        List<EventInfoQueryVo> list = eventInfoService.selectByPersonCode(personCode);
//
//        return ApiResult.ok(list);
//    }

//    /**
//     * 查询eventInfo表中未完成的记录
//     */
//    @GetMapping("/unfinished")
//    @ApiOperation(value = "查询eventInfo表中未完成的记录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "type",value = "类型"),
//            @ApiImplicitParam(name = "town",value = "片区")
//    })
//    public ApiResult<EventInfoQueryVo> selectUnfinished(String type, String town) {
//        List<EventInfoQueryVo> list = eventInfoService.selectUnfinished(type, town);
//        return ApiResult.ok(list);
//    }

//    /**
//     * Info,dispatch,disposal 延期
//     */
//    @GetMapping("/postpone")
//    @ApiOperation(value = "延期")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "eventCode",value = "事件编码")
//    })
//    public ApiResult<Boolean> postpone(String eventCode) throws Exception {
//        boolean b = eventInfoService.postpone(eventCode);
//        return ApiResult.ok(b);
//    }

//    /**
//     * 分组统计
//     */
//    @GetMapping("/groupStatistics")
//    @ApiOperation(value = "分组统计")
//    public ApiResult<EventInfoCountVo> groupStatistics() {
//        List<EventInfoCountVo> eventInfoCountVo = eventInfoService.groupStatistics();
//        return ApiResult.ok(eventInfoCountVo);
//    }

//    /**
//     * 路面养护统计
//     */
//    @GetMapping("/roadCount")
//    @ApiOperation(value = "路面养护统计")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "beginTime",value = "开始时间"),
//            @ApiImplicitParam(name = "endTime",value = "结束时间"),
//            @ApiImplicitParam(name = "town",value = "片区")
//    })
//    public ApiResult<EventCountQueryVo> selectEventCount(String beginTime, String endTime, String town) {
//        List<EventCountQueryVo> list = eventInfoService.selectEventCount(beginTime, endTime, town);
//        return ApiResult.ok(list);
//    }

//    /**
//     * 事件来源统计
//     */
//    @GetMapping("/infoSource")
//    @ApiOperation(value = "事件来源统计")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "beginTime",value = "开始时间"),
//            @ApiImplicitParam(name = "endTime",value = "结束时间")
//
//    })
//    public ApiResult<EventInfoSourceQueryVo> selectSource(String beginTime, String endTime) {
//        List<EventInfoSourceQueryVo> list = eventInfoService.selectSource(beginTime, endTime);
//
//        return ApiResult.ok(list);
//    }

//    /**
//     * 片区年养护统计
//     */
//    @GetMapping("/groupByMonth")
//    @ApiOperation(value = "片区年养护统计")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "beginTime",value = "开始时间"),
//            @ApiImplicitParam(name = "endTime",value = "结束时间"),
//            @ApiImplicitParam(name = "town",value = "片区")
//    })
//    public ApiResult<EventInfoSourceByMonthQueryVo> getInfoSourceGroupByMonth(String beginTime, String endTime, String town) {
//        List<EventInfoSourceByMonthQueryVo> list = eventInfoService.getInfoSourceGroupByMonth(beginTime, endTime, town);
//
//        return ApiResult.ok(list);
//    }

    @PostMapping("/pAcquiredQuantityQuery")
    @ApiOperation(value = "根据时间和项目组获取问题数量")
    public ApiResult<?> pAcquiredQuantityQuery(@Valid @RequestBody PatrolInfoObjectQuerParam queryParameter) throws Exception {
        EvenInfoVo pAcquiredQuantity = eventInfoService.pAcquiredQuantityQuery(queryParameter);
        return ApiResult.ok(pAcquiredQuantity);
    }

    //分页列表展示
    @PostMapping("/quiredQuantityQueryList")
    @ApiOperation(value = "获取养护管理详情")
    public ApiResult<?> quiredQuantityQueryList(@Valid @RequestBody QueryParameterEventPage queryParameter) throws Exception {

        PageInfo<EventInfo> eventInfoPageInfo = eventInfoService.cquiredQuantityQueryList(queryParameter);
        return ApiResult.ok(eventInfoPageInfo);
    }

    /**
     * 事件记录
     */

    @GetMapping("/getEventRecord")
    @ApiOperation(value = "事件记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime",value = "开始时间"),
            @ApiImplicitParam(name = "endTime",value = "结束时间"),
            @ApiImplicitParam(name = "town",value = "片区"),
            @ApiImplicitParam(name = "projectTeam",value = "项目组"),
            @ApiImplicitParam(name = "type",value = "类型"),
            @ApiImplicitParam(name = "source",value = "派遣方式"),
            @ApiImplicitParam(name = "eventState",value = "状态")
    })
    public ApiResult<?> getEventRecord(String source, String beginTime, String endTime,
                                       String eventState, String town, String projectTeam, String type) {
        Map<String, GroupStatisticsVo> list = eventInfoService.getEventRecord(source, beginTime, endTime,
                eventState, town, projectTeam, type);

        return ApiResult.ok(list);
    }


    /**
     * 根据protcode 查询
     */
    @GetMapping("/getEvnetInfoByCode")
    @ApiOperation(value = "根据protcode 查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "proCode",value = "道路编号")

    })
    public ApiResult<?> getSuspectedProblem(String proCode) {

        List<EventInfoQueryVo> eventInfoQueryVos = eventInfoMapper.selectByPatrolCode(proCode);

        return ApiResult.ok(eventInfoQueryVos);
    }
//    /**
//     * 历史道路病害
//     */
//
//    @GetMapping("/listHistoricalEmergency")
//    @ApiOperation(value = "历史道路病害")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "year",value = "年")
//
//    })
//    public ApiResult<?> listHistoricalEmergency(String year) throws ParseException {
//        HistoricalEmergencyVoS eventInfoQueryVos = eventInfoService.listHistoricalEmergency(year);
//
//        return ApiResult.ok(eventInfoQueryVos);
//    }




//    /**
//     * 根据disposalCode查询工程审核量
//     */
//    @GetMapping("/checkWork")
//    @ApiOperation(value = "根据disposalCode获取病害信息，工作量")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "disposalCode",value = "处置编码")
//
//    })
//    public ApiResult<CheckWorkQueryVo> checkWork(String disposalCode) throws Exception {
//        CheckWorkQueryVo list = eventDisposalWorkloadService.getCheckWork(disposalCode);
//        return ApiResult.ok(list);
//    }

//    // 根据 event_code 修改事件图片
//
//    @ApiOperation(value = "根据 event_code 修改事件图片")
//    public ApiResult<Boolean> updateImage(@RequestBody EventInfoImage eventInfo) throws Exception {
//        Boolean list = eventInfoService.updateImage(eventInfo);
//        return ApiResult.ok(list);
//    }

    @GetMapping("list/statisticCount")
    @ApiOperation(value = "EventInfo分类统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "times",value = "时间"),
            @ApiImplicitParam(name = "type",value = "人员类型"),
            @ApiImplicitParam(name = "district",value = "区域"),

    })
    public ApiResult<?> statisticCount(String times,String type,String district) throws Exception {
        QuestionsCountListVo patrolInfoQueryVo = eventInfoService.statisticCount(times,type,district);


        return ApiResult.ok(patrolInfoQueryVo);

    }

    @GetMapping("list-areaTree")
    @ApiOperation(value = "EventInfo分类统计")
    public ApiResult<?> areaTree() throws Exception {
        List<OrgDept> orgDeptApiResult = appVersionController.areaList();

        List<OrgDeptTreeVo> orgDeptTreeVos = eventInfoService.areaTree(orgDeptApiResult);


        return ApiResult.ok(orgDeptTreeVos);

    }


}

