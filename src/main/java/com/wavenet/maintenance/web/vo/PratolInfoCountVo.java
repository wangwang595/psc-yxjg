package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PratolInfoCountVo {

    @ApiModelProperty(value = "里程数")
    private  Double mileages;

    @ApiModelProperty(value = "频率")
    private  Integer frequency;

    @ApiModelProperty(value = "问题")
    private  Integer questions;
}