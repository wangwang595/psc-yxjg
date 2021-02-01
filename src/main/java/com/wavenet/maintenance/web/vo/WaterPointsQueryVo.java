package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName WaterPoints
 * @Description TODO
 * @Author Alex
 * @Date 2019/8/20
 * @Version 1.0
 **/
@Data
@ToString(callSuper = true)
@ApiModel(description = "积水点统计")
public class WaterPointsQueryVo {
     @ApiModelProperty(value = "名称")
    private String sName;
     @ApiModelProperty(value = "水位")
    private Double value;
     @ApiModelProperty(value = "时间")
    private String time;

    private Double jsjsw;
}
