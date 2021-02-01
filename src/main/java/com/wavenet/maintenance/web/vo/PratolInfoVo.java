package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/12/10 10:19
 * @Version 1.0
 */

@Data
@ToString
public class PratolInfoVo {


     @ApiModelProperty(value = "片区")
    private  String town;

     @ApiModelProperty(value = "里程数")
    private  Double mileages;

     @ApiModelProperty(value = "频率")
    private  Integer frequency;

     @ApiModelProperty(value = "问题")
    private  Integer questions;
}



