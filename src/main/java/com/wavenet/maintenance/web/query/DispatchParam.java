/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: EventDispatchParam.java
 * @Package com.wavenet.maintenance.web.query
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-05-19 16:40
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.query;

import com.wavenetframework.boot.common.param.OrderQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName: EventDispatchParam
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-05-19 16:40     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "对象", description = "查询参数")
public class DispatchParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型编号")
    private String eventType;
    @ApiModelProperty(value = "项目组")
    private String projectTeam;
    @ApiModelProperty(value = "片区")
    private String town;
    @ApiModelProperty(value = "事件人")
    private String dispatcherName;
    @ApiModelProperty(value = "事件状态")
    private String dispatchState;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;

}