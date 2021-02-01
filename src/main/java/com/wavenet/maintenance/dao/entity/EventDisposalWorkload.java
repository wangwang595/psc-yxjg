package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wavenetframework.boot.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("EVENT_DISPOSAL_WORKLOAD")
@ApiModel(value = "EventDisposalWorkload对象", description = "")
public class EventDisposalWorkload extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键")
@TableId(value = "workload_code", type = IdType.UUID)
    private String workloadCode;

@ApiModelProperty(value = "处置编号")
private String disposalCode;

@ApiModelProperty(value = "事件类型")
private String eventType;

@ApiModelProperty(value = "病害大类")
private String eventTypeLarge;

@ApiModelProperty(value = "病害小类")
private String eventTypeSmall;

@ApiModelProperty(value = "计划工作量")
private Double planValue;

@ApiModelProperty(value = "单位")
private String unit;

@ApiModelProperty(value = "描述")
private String describe;

@ApiModelProperty(value = "过程")
private Double process;

@ApiModelProperty(value = "审核人")
private String auditor;

@ApiModelProperty(value = "实际工作量")
    @TableField("real_workLoad")
private String realWorkload;

@ApiModelProperty(value = "厚度")
private Double thickness;

@ApiModelProperty(value = "type编码")
private String eventTypeCode;

private Double realThickness;

}
