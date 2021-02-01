package com.wavenet.maintenance.web.vo;

import com.wavenet.maintenance.common.FindArticleDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/12/25 14:42
 * @Version 1.0
 */
@Data
@ToString
public class PlansVo {

    @ApiModelProperty(value = "分页参数")
    private FindArticleDto findArticleDto;
    @ApiModelProperty(value = "年")
    private String year;
    @ApiModelProperty(value = "月")
    private String month;
    @ApiModelProperty(value = "项目组")
    private String project;
    @ApiModelProperty(value = "分页参数")
    private String task;

    @ApiModelProperty(value = "片区")
    private String  area;


}
