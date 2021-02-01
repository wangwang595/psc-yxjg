package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/11/4 14:08
 * @Version 1.0
 */

@Data
public class OnlineUsersVO {

    
     @ApiModelProperty(value = "用户编码")
    private String userCode;

}
