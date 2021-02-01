package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WellRecordVo {


    @ApiModelProperty(value = "片区")
    private  String town;


    @ApiModelProperty(value = "问题")
    private  Integer questions;
}