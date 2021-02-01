package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.common.FindArticleDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryParameterEventPage  {

    //分页参数
    @ApiModelProperty(value = "分页参数")
    private FindArticleDto findArticleDto;

    //时间
    @ApiModelProperty(value = "时间")
    private String date;
    //片区
    @ApiModelProperty(value = "片区")
    private  String twon;
    //处理状态
    @ApiModelProperty(value = "处理状态")
    private String  status;
    //项目组
    @ApiModelProperty(value = "项目组")
    private String project;
    //来源
    @ApiModelProperty(value = "来源")
    private String source;
    //病害类型
    @ApiModelProperty(value = "病害类型")
    private String disease;


}