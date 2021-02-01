package com.wavenet.maintenance.web.query;

import com.wavenetframework.boot.common.param.OrderQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  查询参数对象
 * </pre>
 *
 * @author zll
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "EventDisposalQueryParam对象", description = "查询参数")
public class EventDisposalQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "事件类型")
    private String eventType;
    @ApiModelProperty(value = "项目组")
    private String projectTeam;
    @ApiModelProperty(value = "片区")
    private String town;
    @ApiModelProperty(value = "类型编号")
    private String solverName;
    @ApiModelProperty(value = "处置人名称")
    private String disposalState;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;

}
