/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: PushController.java
 * @Package com.wavenet.maintenance.web
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-05-14 16:00
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web;


import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/PumpInfo")
@Api(tags = "泵站积水点弹窗")
@CrossOrigin
public class PumpInfoController extends BaseController {
//    @Resource
//    private PumpInfoService service;
//
//    /**
//     * 输入id查询泵站详情
//     */
//    @ApiOperation(value = "输入id查询泵站详情")
//    @GetMapping(value = "/findPumpById")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<PumpInfoQueryVo> findPumpById(String id) throws Exception {
//        List<PumpInfoQueryVo> data = service.findPumpById(id);
//        return ApiResult.ok(data);
//    }
//
//    /**
//     * 输入id查询道路积水点详情
//     */
//    @ApiOperation(value = "输入id查询道路积水点详情")
//    @GetMapping(value = "/findPointsById")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<WaterPointsRoadQueryVo> findPointsById(String id) throws Exception {
//        List<WaterPointsRoadQueryVo> data = service.findPointsById(id);
//        return ApiResult.ok(data);
//    }
//
//    /**
//     * 输入id查询小区积水点详情
//     */
//    @ApiOperation(value = "输入id查询小区积水点详情")
//    @GetMapping(value = "/findUnderpassList")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id值")
//    })
//    public ApiResult<WaterPointsRoadQueryVo> findUnderpassList(String id) throws Exception {
//        List<WaterPointsRoadQueryVo> data = service.findUnderpassList(id);
//        return ApiResult.ok(data);
//    }
//
//    /**
//     * 输入id查询小区积水点详情
//     */
//    @ApiOperation(value = "输入id和时间查询积水点曲线")
//    @GetMapping(value = "/findPointsDay")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="param1",value="积水点编号",dataType="String", paramType = "query"),
//            @ApiImplicitParam(name="param2",value="日期（YYYY-MM-DD）",dataType="String", paramType = "query"),
//
//    })
//
//    public ApiResult<WaterPointsQueryVo> findPointsDay(String param1, String param2) throws Exception {
//        List<WaterPointsQueryVo> data = service.findPointsDay(param1, param2);
//        return ApiResult.ok(data);
//    }
//
//    /**
//     * 输入id查询小区积水点详情
//     */
//    @ApiOperation(value = "查询预警积水点")
//    @GetMapping(value = "/findOrdinary")
//    public ApiResult<WaterPointsQueryVo> findOrdinary() throws Exception {
//        List<WaterPointsExtendQueryVo> data = service.findOrdinary();
//        return ApiResult.ok(data);
//    }
//
//    /**
//     * 输入id查询小区积水点详情
//     */
//    @DS("permission2")
//    @ApiOperation(value = "查询泵站列表",notes = "0是掉线，1是正常，2是报警")
//    @GetMapping(value = "/findPumpList")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="value",value="泵站类型 1雨水 2污水 3合流",dataType="String", paramType = "query"),
//
//    })
//    public ApiResult<PumpWarningQueryVo> findPumpList(String value) throws Exception {
//        List<PumpWarningQueryVo> data = service.findPumpList(value);
//        return ApiResult.ok(data);
//    }
//
//    /**
//     * 输入id查询小区积水点详情(新)
//     */
//    @ApiOperation(value = "查询预警积水点(新)")
//    @GetMapping(value = "/findOrdinaryNew")
//    public ApiResult<PumpWarningNewQueryVo> findPumpListNew() throws Exception {
//        List<PumpWarningNewQueryVo> data = service.findPumpListNew();
//        return ApiResult.ok(data);
//    }
}
