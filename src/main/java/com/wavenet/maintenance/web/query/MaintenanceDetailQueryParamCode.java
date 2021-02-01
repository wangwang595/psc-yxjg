package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.common.FindArticleDto;
import com.wavenetframework.boot.common.param.OrderQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/12/3 14:36
 * @Version 1.0
 */

@Data
public class MaintenanceDetailQueryParamCode  {


    @ApiModelProperty("路名")
    private String roadName;

    @ApiModelProperty("年")
    private int year;

    @ApiModelProperty("月")
    private int month;

    @ApiModelProperty("养护状态")
    private String curingState;

    @ApiModelProperty("区域")
    private String town;

    @ApiModelProperty("养护类型 雨水/污水")
    private String type;

    @ApiModelProperty("道路编号")
    private String roadSectionCode;

    @ApiModelProperty("任务来源  计划内/计划外")
    private String plans;

    @ApiModelProperty("项目组")
    private String projectTeam;


    private String detailCode;

    //分页对象
    private FindArticleDto findArticleDto;
}
