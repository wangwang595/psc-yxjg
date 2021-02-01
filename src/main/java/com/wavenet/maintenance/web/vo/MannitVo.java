package com.wavenet.maintenance.web.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "MannitVo对象", description = "查询参数")
public class MannitVo {
    // 下水道
     @ApiModelProperty(value = "下水道")
    private Double xplan;
    //
     @ApiModelProperty(value = "下水道实际")
    private Double xreal;
    // 井
     @ApiModelProperty(value = "井")
    private Integer manplan;
    //
     @ApiModelProperty(value = "井实际")
    private Integer manreal;
    // 雨水口
     @ApiModelProperty(value = "雨水口计划")
    private Integer outplan;
    //
     @ApiModelProperty(value = "雨水口实际")
    private Integer outreal;
}
