package com.wavenet.maintenance.web.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MaintenancePlanQueryVo对象", description = "查询参数")
public class MaintenancePlanQueryVo implements Serializable {
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


}