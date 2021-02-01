package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EvenInfoVo {
  /// 问题  处置  完成  工单完成  工单
  @ApiModelProperty(value = "问题")
    private  Integer problem;

    @ApiModelProperty(value = "处置")

   private  Integer  dispose;

    @ApiModelProperty(value = "完成")

   private  Integer carry;
    @ApiModelProperty(value = "工单完成")

   private  Integer work;
    @ApiModelProperty(value = "工单")

    private Integer workTotal;






}
