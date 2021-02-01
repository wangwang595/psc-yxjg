package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wavenetframework.boot.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author zll
 * @since 2020-11-25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("IMAGES_INFO")
@ApiModel(value = "ImagesInfo对象", description = "")
public class ImagesInfo extends BaseEntity {

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
