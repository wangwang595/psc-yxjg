package com.wavenet.maintenance.web.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupStatisticsVo {



    //完成
    
     @ApiModelProperty(value = "完成")
    private  Integer carry;

    //上报
     @ApiModelProperty(value = "上报")
    private  Integer report;

    //审核
     @ApiModelProperty(value = "审核")
    private  Integer review;
}
