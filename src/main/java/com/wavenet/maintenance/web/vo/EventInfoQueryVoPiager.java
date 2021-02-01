package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/11/21 11:04
 * @Version 1.0
 */
public class EventInfoQueryVoPiager  extends  EventInfoQueryVo{

    @ApiModelProperty(value = "处置前图片")
    private String attBefore;

    @ApiModelProperty(value = "处置中图片")
    private String attOngoing;

    @ApiModelProperty(value = "处置都图片")
    private String attAfter;
    @ApiModelProperty(value = "道路编号")
    private String dispatchCode;


}
