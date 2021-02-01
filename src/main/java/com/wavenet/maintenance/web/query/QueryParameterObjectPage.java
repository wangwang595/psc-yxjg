package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.common.FindArticleDto;
import com.wavenetframework.boot.common.param.OrderQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class QueryParameterObjectPage  {

    //分页对象
    @ApiModelProperty(value = "分页对象")
    private FindArticleDto findArticleDto;

    //时间 2020-08
    @ApiModelProperty(value = "时间")
    private String date;

    //项目组
    @ApiModelProperty(value = "项目组")
    private String project;

    // 巡查类型
    @ApiModelProperty(value = "巡查类型")
    private String type;

    //巡查片区
    @ApiModelProperty(value = "巡查片区")
    private  String twon;

}
