package com.wavenet.maintenance.web.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.wavenetframework.boot.common.param.OrderQueryParam;
import org.checkerframework.checker.units.qual.A;

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
@ApiModel(value = "PatrolInfoQueryParam对象", description = "查询参数")
public class PatrolInfoQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("项目组名")
    private String projectName;

    @ApiModelProperty("项目组code")
    private String projectCode;

    @ApiModelProperty("片区名")
    private String townName;

    @ApiModelProperty("片区code")
    private String townCode;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户code")
    private String userCode;

    @ApiModelProperty("巡查类型")
    private String typeName;

    @ApiModelProperty("巡查code")
    private String typeCode;

    @ApiModelProperty("巡查开始时间")
    private String dateStart;

    @ApiModelProperty("巡查结束时间")
    private String dateEnd;
}
