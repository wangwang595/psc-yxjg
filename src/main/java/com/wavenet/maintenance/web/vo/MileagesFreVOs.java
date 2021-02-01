package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MileagesFreVOs extends MileagesFreVO {


    

     @ApiModelProperty(value = "接收")
    private  Integer review;

}