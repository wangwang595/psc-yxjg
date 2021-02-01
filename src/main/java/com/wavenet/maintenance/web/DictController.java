package com.wavenet.maintenance.web;

import com.wavenet.maintenance.dao.entity.Dict;
import com.wavenet.maintenance.service.DictService;
import com.wavenet.maintenance.web.query.DictQueryParam;
import com.wavenet.maintenance.web.vo.DictQueryVo;
import com.wavenet.maintenance.web.vo.EventTypeQueryVo;
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
 * @since 2020-03-28
 */
@Slf4j
@RestController
@RequestMapping("/dict")
@Api(tags = " 字典表")
@CrossOrigin
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    /**
     * 根据parentId获取子对象  web 调用
     */
    @GetMapping("/infoByParentCode/{parentCode}")
    @ApiOperation(value = "获取EventType对象详情", notes = "查看", response = EventTypeQueryVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentCode",value = "id值")
    })
    public ApiResult<DictQueryVo> getEventTypeByParentCode(@PathVariable("parentCode") String id) throws Exception {
        List<DictQueryVo> dictQueryVoList = dictService.getDictTypeByParentCode(id);
        return ApiResult.ok(dictQueryVoList);
    }

}

