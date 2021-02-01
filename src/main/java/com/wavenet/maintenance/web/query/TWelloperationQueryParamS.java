package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.common.FindArticleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

@ApiModel(value = "TWelloperationQueryParam对象", description = "查询参数")
public class TWelloperationQueryParamS  {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "开始时间")
    private String sDate;
    @ApiModelProperty(value = "结束时间")
    private String eDate;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "id")
    private String manId;
    @ApiModelProperty(value = "巡查编号")
    private String wellClass;
    @ApiModelProperty(value = "巡查编号")
    private String recodeID;
    @ApiModelProperty(value = "巡查编号")
    private String nId;
    @ApiModelProperty(value = "巡查编号")
    private String yhwork;
    @ApiModelProperty(value = "片区")
    private String town;
    //分页对象
    private FindArticleDto findArticleDto;
    private String welltype;


}