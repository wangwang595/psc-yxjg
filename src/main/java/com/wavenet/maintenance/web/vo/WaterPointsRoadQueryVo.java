package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName WaterPoints
 * @Description TODO
 * @Author Alex
 * @Date 2019/8/20
 * @Version 1.0
 **/
@Data
@ToString(callSuper = true)
@ApiModel(description = "道路积水点基础信息查询")
public class WaterPointsRoadQueryVo {
    @ApiModelProperty(value = "x坐标")

    private String sName;
     @ApiModelProperty(value = "x坐标")
    private String time;
     @ApiModelProperty(value = "x坐标")
    private Double value;
     @ApiModelProperty(value = "x坐标")
    private String county;
     @ApiModelProperty(value = "x坐标")
    private String saddr;
     @ApiModelProperty(value = "x坐标")
    private Double ndeepth;
     @ApiModelProperty(value = "x坐标")
    private String smanae;
     @ApiModelProperty(value = "x坐标")
    private String classification;
     @ApiModelProperty(value = "x坐标")
    private String manageUnit;
     @ApiModelProperty(value = "x坐标")
    private String maintainUnit;
     @ApiModelProperty(value = "x坐标")
    private String managrMantel;
     @ApiModelProperty(value = "x坐标")
    private String maintainUnitfx;
}
