package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wavenetframework.boot.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author zll
 * @since 2020-12-10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("MAINTENANCE_PLAN")
@ApiModel(value = "MaintenancePlan对象", description = "")
public class MaintenancePlan extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键")
@TableId(value = "plan_code", type = IdType.UUID)
    private String planCode;

@ApiModelProperty(value = "年")
private Double year;

@ApiModelProperty(value = "月")
private Double month;

@ApiModelProperty(value = "片区")
private String town;

@ApiModelProperty(value = "创建人")
private String creator;

@ApiModelProperty(value = "创建日期")
private Date creatorDate;

@ApiModelProperty(value = "雨水管总长度")
private Double rainTotal;

@ApiModelProperty(value = "雨水主管长度")
private Double rainMain;

@ApiModelProperty(value = "雨水支管长度")
private Double rainLateral;

@ApiModelProperty(value = "污水管总长度")
private Double sewerTotal;

@ApiModelProperty(value = "污水主管长度")
private Double sewerMain;

@ApiModelProperty(value = "污水支管长度")
private Double sewerLateral;

@ApiModelProperty(value = "检查井")
private Double manhole;

@ApiModelProperty(value = "雨水口")
private Double catchBasin;

@ApiModelProperty(value = "公司")
private String company;

@ApiModelProperty(value = "类型")
private String type;

@ApiModelProperty(value = "审核人")
private String auditor;

@ApiModelProperty(value = "审核意见")
private String auditorIdea;

@ApiModelProperty(value = "审核日期")
private Date auditorDate;

@ApiModelProperty(value = "备注")
private String note;

@ApiModelProperty(value = "状态")
private String state;

@ApiModelProperty(value = "计划内，计划外")
private String plans;

@ApiModelProperty(value = "项目组")
private String projectTeam;

}
