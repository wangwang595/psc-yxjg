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
 * @date 2020-11-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ImagesInfoQueryVo对象", description = "查询参数")
public class ImagesInfoQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一编码")
        @TableField("codingCode")
private String codingCode;

    @ApiModelProperty(value = "人")
    private String persons;

    @ApiModelProperty(value = "时间")
        @TableField("dataTime")
private Date dataTime;

    @ApiModelProperty(value = "更新的图片")
    private String images;

    @ApiModelProperty(value = "状态 更新或者删除 1是加 0是删除")
    private Integer status;

    @ApiModelProperty(value = "表名")
        @TableField("tableName")
private String tableName;

}