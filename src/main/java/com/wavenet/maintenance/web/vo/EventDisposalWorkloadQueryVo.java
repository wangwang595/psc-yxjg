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
 * @date 2020-03-30
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "EventDisposalWorkloadQueryVo对象", description = "查询参数")
public class EventDisposalWorkloadQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String workloadCode;

    @ApiModelProperty(value = "type编码")
    private String eventTypeCode;

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


    @ApiModelProperty(value = "实际厚度")
    private Double realThickness;


    private Integer isthick;

}