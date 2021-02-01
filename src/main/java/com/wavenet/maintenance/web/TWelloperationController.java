package com.wavenet.maintenance.web;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.TWelloperation;
import com.wavenet.maintenance.dao.mapper.TWelloperationMapper;
import com.wavenet.maintenance.service.TWelloperationService;
import com.wavenet.maintenance.web.query.TWelloperationCountQueryVo;
import com.wavenet.maintenance.web.query.TWelloperationQueryParamS;
import com.wavenet.maintenance.web.vo.PratolInfooCountListVo;
import com.wavenet.maintenance.web.vo.TWelloperationQueryVo;
import com.wavenet.maintenance.web.vo.WellRecordCountListVo;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import com.wavenetframework.boot.common.vo.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author zll
 * @since 2020-08-12
 */
@Slf4j
@RestController
@RequestMapping("/tWelloperation")
@Api(tags = "开井作业表")
@CrossOrigin
public class TWelloperationController extends BaseController {

    @Autowired
    private TWelloperationService tWelloperationService;
    @Autowired
    private TWelloperationMapper welloperationMapper;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加TWelloperation对象", notes = "添加", response = ApiResult.class)

    public ApiResult<?> addTWelloperation(@Valid @RequestBody TWelloperation tWelloperation) throws Exception {
        String s = tWelloperationService.saveTWelloperation(tWelloperation);
        return ApiResult.ok(s);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改TWelloperation对象", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateTWelloperation(@Valid @RequestBody TWelloperation tWelloperation) throws Exception {
        boolean flag = tWelloperationService.updateTWelloperation(tWelloperation);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除TWelloperation对象", notes = "删除", response = ApiResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<Boolean> deleteTWelloperation(@PathVariable("id") Long id) throws Exception {
        boolean flag = tWelloperationService.deleteTWelloperation(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取TWelloperation对象详情", notes = "查看", response = TWelloperationQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id值")
    })
    public ApiResult<TWelloperationQueryVo> getTWelloperation(@PathVariable("id") String id) throws Exception {
        TWelloperationQueryVo tWelloperationQueryVo = tWelloperationService.getTWelloperationById(id);
        return ApiResult.ok(tWelloperationQueryVo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取TWelloperation分页列表", notes = "分页列表", response = TWelloperationQueryVo.class)
    public ApiResult<Paging<TWelloperationQueryVo>> getTWelloperationPageList(@Valid @RequestBody TWelloperationQueryParamS tWelloperationQueryParam) throws Exception {
        PageInfo<TWelloperationQueryVo> paging = tWelloperationService.getTWelloperationPageList(tWelloperationQueryParam);


        return ApiResult.ok(paging);
    }

    /**
     * 分页列表
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取TWelloperation分页列表和统计总数")
    public ApiResult<TWelloperationQueryVo> selectCountByYh(@Valid @RequestBody TWelloperationQueryParamS tWelloperationQueryParam) throws Exception {


        TWelloperationCountQueryVo tWelloperationCountQueryVo = tWelloperationService.selectCountByYh(tWelloperationQueryParam);
        PageInfo<TWelloperationQueryVo> tWelloperationPageList = tWelloperationService.getTWelloperationPageList(tWelloperationQueryParam);
        Object [] obj = {tWelloperationCountQueryVo,tWelloperationPageList};
        return ApiResult.ok(obj);
    }
    @PostMapping("/list-stringWell")
    @ApiOperation(value = "查询井")

    public ApiResult<?> stringWell() throws Exception {
        List<String> s = welloperationMapper.stringWell();
        return ApiResult.ok(s);
    }

    @GetMapping("list/statisticCount")
    @ApiOperation(value = "PatrolInfo分类统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "times",value = "时间"),
            @ApiImplicitParam(name = "type",value = "人员类型"),
            @ApiImplicitParam(name = "district",value = "区域"),

    })
    public ApiResult<?> statisticCount(String times,String type,String district) throws Exception {
        WellRecordCountListVo patrolInfoQueryVo = tWelloperationService.statisticCount(times,type,district);


        return ApiResult.ok(patrolInfoQueryVo);

    }

}

