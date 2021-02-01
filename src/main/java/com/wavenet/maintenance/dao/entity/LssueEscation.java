package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-01-27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("LSSUE_ESCATION")
@ApiModel(value = "LssueEscation对象", description = "")
public class LssueEscation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "x")
    private String x;

    @ApiModelProperty(value = "y")
    private String y;

    @ApiModelProperty(value = "描述")
        @TableField("sBz")
private String sBz;

}
