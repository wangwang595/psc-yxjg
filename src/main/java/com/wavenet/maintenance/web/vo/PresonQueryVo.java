package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data

public class PresonQueryVo  {


     @ApiModelProperty(value = "片区")
    private  String town;

     @ApiModelProperty(value = "下水道在线")
    private  Integer sewerOnlie;

     @ApiModelProperty(value = "下水道")
    private  Integer sewerOffline;


     @ApiModelProperty(value = "路面在线")
    private  Integer pavementOnline;


     @ApiModelProperty(value = "路面")
    private  Integer pavementOffline;
}