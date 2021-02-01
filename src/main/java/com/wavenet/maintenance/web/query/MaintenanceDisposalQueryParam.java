package com.wavenet.maintenance.web.query;

import com.wavenetframework.boot.common.param.OrderQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
@ApiModel(value = "MaintenanceDisposalQueryParam对象", description = "查询参数")
public class MaintenanceDisposalQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("城镇")
    private String town;

    @ApiModelProperty("路名")
    private String roadName;

    @ApiModelProperty("养护状态")
    private String state;

    @ApiModelProperty("时间 2019-02-05")
    private String time;

    @ApiModelProperty("维修对策")
    private String way;

    @ApiModelProperty("用户ID")
    private String personCode;

    @ApiModelProperty("公司名称")
    private String company;

    @ApiModelProperty("疏通开始时间")
    private String beginTime;

    @ApiModelProperty("疏通结束时间")
    private String endTime;

    @ApiModelProperty("疏通类型")
    private String type;

    private String personName;

    private String plans;

    private String projectTeam;
}

