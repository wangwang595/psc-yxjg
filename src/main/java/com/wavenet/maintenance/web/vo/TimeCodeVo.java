package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/12/28 11:50
 * @Version 1.0
 */
@Data
public class TimeCodeVo {
    @ApiModelProperty(value = "最小时间")
    private Date minDate;
    @ApiModelProperty(value = "最大时间")
    private  Date MaxDate;
}
