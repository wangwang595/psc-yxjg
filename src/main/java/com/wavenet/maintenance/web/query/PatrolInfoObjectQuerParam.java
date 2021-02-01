package com.wavenet.maintenance.web.query;

import com.wavenetframework.boot.common.param.OrderQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
public class PatrolInfoObjectQuerParam {

    @ApiModelProperty(value = "时间")
    private String date;


    @ApiModelProperty(value = "项目组")
    private String project;



}


