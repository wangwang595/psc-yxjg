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
 * @date 2020-03-13
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MaintenanceDetailQueryParam对象", description = "查询参数")

public class MaintenanceDetailQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("路名")
    private String roadName;

    @ApiModelProperty("年")
    private int year;

    @ApiModelProperty("月")
    private int month;

    @ApiModelProperty("养护状态")
    private String curingState;

    @ApiModelProperty("区域")
    private String town;

    @ApiModelProperty("养护类型 雨水/污水")
    private String type;

    @ApiModelProperty("道路编号")
    private String roadSectionCode;

    @ApiModelProperty("任务来源  计划内/计划外")
    private String plans;

    @ApiModelProperty("项目组")
    private String projectTeam;

}
