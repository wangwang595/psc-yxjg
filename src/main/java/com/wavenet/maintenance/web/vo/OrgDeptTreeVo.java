package com.wavenet.maintenance.web.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 *  查询结果对象
 * </pre>
 *
 * @author zll
 * @date 2021-01-08
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "OrgDeptQueryVo对象", description = "查询参数")
public class OrgDeptTreeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门code")
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

    private List<OrgDeptTreeVo> children ;


}