package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "HistoricalEmergencyVo对象", description = "查询参数")
public class HistoricalEmergencyVo implements Serializable {

    private static final long serialVersionUID = 1L;
    
     @ApiModelProperty(value = "片区")
    private String town;

     @ApiModelProperty(value = "数量")
    private Integer  number;




}