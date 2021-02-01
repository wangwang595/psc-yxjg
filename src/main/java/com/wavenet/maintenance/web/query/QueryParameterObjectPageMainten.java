package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.common.FindArticleDto;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryParameterObjectPageMainten  {

    private FindArticleDto findArticleDto;

    //月
    @ApiModelProperty(value = "月")
    private String month;
//年
 @ApiModelProperty(value = "年")
    private  String year;
    //计划
     @ApiModelProperty(value = "计划")
    private String plan;
//类型
@ApiModelProperty(value = "类型")
    private String type;
//片区
@ApiModelProperty(value = "片区")
    private  String twon;
    //项目组
    @ApiModelProperty(value = "项目组")
    private String project ;

}