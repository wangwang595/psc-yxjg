package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.common.FindArticleDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryParameterQueryPage  {

    //分页参数
    @ApiModelProperty(value = "分页参数")
    private FindArticleDto findArticleDto;

    //时间
    @ApiModelProperty(value = "时间")
    private String date;
//项目组
@ApiModelProperty(value = "项目组")
    private String project;
//问题类型
@ApiModelProperty(value = "问题类型")
    private String type;
//片区
@ApiModelProperty(value = "片区")
    private  String twon;
//状态
@ApiModelProperty(value = "状态")
    private String  status;

}

