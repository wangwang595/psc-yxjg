/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: RoadWatchController.java
 * @Package com.wavenet.maintenance.web
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-22 20:39
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.RoadWatch;
import com.wavenet.maintenance.dao.entity.RoadWatchParam;
import com.wavenet.maintenance.service.RoadWatchService;
import com.wavenet.maintenance.web.vo.CountByMileageQueryVo;
import com.wavenet.maintenance.web.vo.RoadWatchVo;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import com.wavenetframework.boot.common.vo.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * @ClassName: RoadWatchController
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-22 20:39     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@RestController
@RequestMapping(value = "/roadWatch")
@Api(tags = "道路巡查")
@CrossOrigin
public class RoadWatchController extends BaseController {
    @Resource
    private RoadWatchService service;

    /**
     * 道路巡查第一张设计图
     */
    @ApiOperation(value = "道路巡查")
    @PostMapping(value = "/road")
    public ApiResult<RoadWatch> selectPlan(@Valid @RequestBody RoadWatchParam param) throws Exception {
        PageInfo<RoadWatch> list = service.selectRoadWatchTable(param);
        return ApiResult.ok(list);
    }

    /**
     * 计算总路程
     */
    @ApiOperation(value = "总路程查询")
    @PostMapping(value = "/sum")
    public ApiResult<RoadWatchVo> selectSum(@Valid @RequestBody RoadWatchParam param) throws Exception {
        RoadWatchVo list = service.selectSum(param);
        return ApiResult.ok(list);
    }

    /**
     * 巡查里程数统计
     */
    @ApiOperation(value = "巡查里程数")
    @GetMapping(value = "/total")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime",value = "开始时间"),
            @ApiImplicitParam(name = "endTime",value = "结束时间"),
            @ApiImplicitParam(name = "town",value = "片区")
    })
    public ApiResult<CountByMileageQueryVo> patrolMileage(String beginTime,String endTime,String town) throws Exception {
        CountByMileageQueryVo countByMileageQueryVo = service.patrolMileage(beginTime,endTime,town);
        return ApiResult.ok(countByMileageQueryVo);
    }

    /**
     * 巡查里程数按城镇分组统计
     */
    @ApiOperation(value = "巡查次数分组统计")
    @GetMapping(value = "/groupByTown")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime",value = "开始时间"),
            @ApiImplicitParam(name = "endTime",value = "结束时间"),
            @ApiImplicitParam(name = "town",value = "片区")
    })
    public ApiResult<RoadWatch> selectTotalGroupByTow(String beginTime,String endTime,String town) throws Exception {
        List<RoadWatch> list = service.selectTotalGroupByTow(beginTime,endTime,town);
        return ApiResult.ok(list);
    }

    /**
     * 发现问题按城镇分组查询
     */
    @ApiOperation(value = "发现问题")
    @GetMapping(value = "/findEventCon")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime",value = "开始时间"),
            @ApiImplicitParam(name = "endTime",value = "结束时间"),
            @ApiImplicitParam(name = "town",value = "片区")
    })
    public ApiResult<RoadWatch> selectEventConGroupByTown(String beginTime,String endTime,String town) throws Exception {
        List<RoadWatch> list = service.selectEventConGroupByTown(beginTime,endTime,town);
        return ApiResult.ok(list);
    }

}
