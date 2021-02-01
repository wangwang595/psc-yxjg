package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/11/4 14:19
 * @Version 1.0
 */

@Data
public class TotalNumberOfPeopleVo {
    
    

     @ApiModelProperty(value = "总人数")
    private  Integer totalUser;

     @ApiModelProperty(value = "在线人数")
    private  Integer onileuser;
}
