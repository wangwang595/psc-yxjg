package com.wavenet.maintenance.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto extends BaseDto {

    @ApiModelProperty(value = "页码")
    private Integer page; //页码
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize; //每页记录数
}
