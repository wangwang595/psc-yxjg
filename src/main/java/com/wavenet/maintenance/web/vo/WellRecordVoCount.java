package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WellRecordVoCount {


    @ApiModelProperty(value = "异常")
    private  Integer abnormal;


    @ApiModelProperty(value = "正常")
    private  Integer normal;
}
