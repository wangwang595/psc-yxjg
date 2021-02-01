package com.wavenet.maintenance.dao.entity;

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
 * @since 2021-01-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OrgDept对象", description = "")
public class OrgDept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门code")
    @TableId(value = "DEPT_CODE", type = IdType.UUID)
    private String deptCode;

    @ApiModelProperty(value = "部门父编码")
        @TableField("DEPT_PCODE")
private String deptPcode;

    @ApiModelProperty(value = "部门名称")
        @TableField("NAME")
private String name;

    @ApiModelProperty(value = "部门编号")
        @TableField("CODE")
private String code;

    @ApiModelProperty(value = "状态 0:禁用 1:启用")
        @TableField("STATE")
private Integer state;

    @ApiModelProperty(value = "排序")
        @TableField("SORT")
private Integer sort;

    @ApiModelProperty(value = "备注")
        @TableField("NOTE")
private String note;

    @ApiModelProperty(value = "创建时间")
        @TableField("CREATE_TIME")
private Date createTime;

    @ApiModelProperty(value = "修改时间")
        @TableField("UPDATE_TIME")
private Date updateTime;

}
