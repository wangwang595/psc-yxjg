package com.wavenet.maintenance.web.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.wavenetframework.boot.common.param.OrderQueryParam;

/**
 * <pre>
 *  查询参数对象
 * </pre>
 *
 * @author zll
 * @date 2020-03-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "EventDispatchQueryParam对象", description = "查询参数")
public class EventDispatchQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "处置时间")
    private String dispatcherDate;
    @ApiModelProperty(value = "处置状态")
    private String dispatcherState;
    @ApiModelProperty(value = "接收人编号")
    private String recipientCode;

}
