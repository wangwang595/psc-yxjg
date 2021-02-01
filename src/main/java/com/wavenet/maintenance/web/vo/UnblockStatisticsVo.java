package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/12/10 10:21
 * @Version 1.0
 */
@Data
@ToString
public class UnblockStatisticsVo {
     @ApiModelProperty(value = "片区")
    private  String town;

     @ApiModelProperty(value = "总官网")
    private Double pipeTotal;
     @ApiModelProperty(value = "井")
    private Double manhole;

     @ApiModelProperty(value = "雨水口")
    private Double catchBasin;

     @ApiModelProperty(value = "养护长度")
    private Double curingLength;

     @ApiModelProperty(value = "养护长度")
    private Double realLength;

     @ApiModelProperty(value = "雨水井数量")
    private Integer curingWellNum;

     @ApiModelProperty(value = "检查口数量")
    private Integer curingOutletNum;

     @ApiModelProperty(value = "实际雨水井数量")
    private Integer realWellNum;

     @ApiModelProperty(value = "实际检查口数量")
    private Integer realOutNum;
}
