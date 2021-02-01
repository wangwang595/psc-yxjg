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

import cn.jpush.api.push.PushResult;
import com.wavenet.maintenance.dao.entity.PushInfo;
import com.wavenet.maintenance.service.PushService;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: PushController
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-05-14 16:00     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@RestController
@RequestMapping(value = "/push")
@Api(tags = "信息推送")
@CrossOrigin
public class PushController extends BaseController {
    @Resource
    private PushService service;

    /**
     * 信息推送
     */
    @ApiOperation(value = "信息推送")
    @GetMapping(value = "/msg")
    public ApiResult<PushResult> pushMsg(PushInfo info) throws Exception {
        PushResult push = service.push(info);
        return ApiResult.ok(push);
    }


}
