package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QuestionsCountVo {



    @ApiModelProperty(value = "总数")
    private  Integer questionTotal;

    @ApiModelProperty(value = "执行中")
    private  Integer questionCarried;

    @ApiModelProperty(value = "完成")
    private  Integer questionComplete;


}
