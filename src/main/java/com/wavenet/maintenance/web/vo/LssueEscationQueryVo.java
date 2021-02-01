package com.wavenet.maintenance.web.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @date 2021-01-27
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "LssueEscationQueryVo对象", description = "查询参数")
public class LssueEscationQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "x")
    private String x;

    @ApiModelProperty(value = "y")
    private String y;

    @ApiModelProperty(value = "描述")
        @TableField("sBz")
private String sBz;

}