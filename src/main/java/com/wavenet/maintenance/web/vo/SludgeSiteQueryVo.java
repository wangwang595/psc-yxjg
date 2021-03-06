package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;

/**
 * <pre>
 *  查询结果对象
 * </pre>
 *
 * @author zll
 * @date 2021-02-02
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SludgeSiteQueryVo对象", description = "查询参数")
public class SludgeSiteQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "污泥站点")
    private String sludgeSite;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "x")
    private String x;

    @ApiModelProperty(value = "y")
    private String y;

}