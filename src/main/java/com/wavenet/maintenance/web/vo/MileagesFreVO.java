package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MileagesFreVO implements Serializable {


    private static final long serialVersionUID = 1L;


     @ApiModelProperty(value = "里程数")
    private  Double  mileages;

     @ApiModelProperty(value = "次数")
    private  Integer  frequency;

     @ApiModelProperty(value = "问题")
    private  Integer querys;

}
