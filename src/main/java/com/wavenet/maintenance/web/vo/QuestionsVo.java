package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/12/10 10:15
 * @Version 1.0
 */

@Data
@ToString
public class QuestionsVo {


     @ApiModelProperty(value = "片区")
    private String town;

     @ApiModelProperty(value = "总数")
    private  Integer questionTotal;

     @ApiModelProperty(value = "执行中")
    private  Integer questionCarried;

     @ApiModelProperty(value = "完成")
    private  Integer questionComplete;


}
