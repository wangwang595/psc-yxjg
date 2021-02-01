package com.wavenet.maintenance.web.vo;

import com.wavenet.maintenance.common.FindArticleDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class  PgeVoParmterVo {
    @ApiModelProperty(value = "分页参数")
    private FindArticleDto findArticleDto;

    @ApiModelProperty(value = "审核状态")
    private Integer checkStaus;
    @ApiModelProperty(value = "公司")
    private  String company;
    @ApiModelProperty(value = "项目组")
    private  String projectTeam;

}