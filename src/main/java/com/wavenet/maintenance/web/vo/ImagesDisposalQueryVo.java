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
@ApiModel(value = "ImagesDisposalQueryVo对象", description = "查询参数")
public class ImagesDisposalQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一编码")
        @TableField("codingCode")
private String codingCode;

    @ApiModelProperty(value = "人")
    private String persons;

    @ApiModelProperty(value = "前")
    private String attBefore;

    @ApiModelProperty(value = "中")
    private String attOngoing;

    @ApiModelProperty(value = "后")
    private String attAfter;

    @ApiModelProperty(value = "时间")
        @TableField("dataTime")
private Date dataTime;

    @ApiModelProperty(value = "前更改")
    private String beforeImages;

    @ApiModelProperty(value = "更改")
    private String ongoingImages;

    @ApiModelProperty(value = "后更改")
    private String afterImages;

    @ApiModelProperty(value = "状态 1 新增 0删除")
    private Integer status;

    @ApiModelProperty(value = "表明")
        @TableField("tableName")
private String tableName;

}