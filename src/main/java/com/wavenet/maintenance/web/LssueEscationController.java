package com.wavenet.maintenance.web;

import com.wavenet.maintenance.dao.entity.LssueEscation;
import com.wavenet.maintenance.service.LssueEscationService;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author zll
 * @since 2021-01-27
 */
@Slf4j
@RestController
@RequestMapping("/lssueEscation")
@Api(tags = "问题上报")
public class LssueEscationController extends BaseController {

    @Autowired
    private LssueEscationService lssueEscationService;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加LssueEscation对象", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addLssueEscation(@Valid @RequestBody LssueEscation lssueEscation) throws Exception {
        boolean flag = lssueEscationService.saveLssueEscation(lssueEscation);
        return ApiResult.result(flag);
    }


}

