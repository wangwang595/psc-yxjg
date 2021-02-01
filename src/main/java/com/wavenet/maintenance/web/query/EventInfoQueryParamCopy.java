package com.wavenet.maintenance.web.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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

@ApiModel(value = "EventInfoQueryParamCopy对象", description = "查询参数")
public class EventInfoQueryParamCopy  {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("路code")
    private String roadCode;

    @ApiModelProperty("路段code")
    private String roadSectionCode;

    @ApiModelProperty("查询上报事件时间 （起始时间）")
    private String startDate;

    @ApiModelProperty("查询上报事件时间 （结束时间）")
    private String endDate;

    @ApiModelProperty("上报事件的状态 （未受理,已受理,已审核）")
    private String eventState;

    @ApiModelProperty("排序 (正序 false 倒序 true,默认倒序)")
    private boolean desc;

    @ApiModelProperty("用户编号")
    private String recipientCode;

    @ApiModelProperty("事件类型(路面，桥梁，护栏)")
    private String eventType;

    @ApiModelProperty("区域")
    private String town;
    @ApiModelProperty("公司")
    private String company;
}
