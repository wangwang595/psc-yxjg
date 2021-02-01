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
@TableName("EVENT_DISPOSAL")
@ApiModel(value = "EventDisposal对象", description = "")
public class EventDisposal extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键")
@TableId(value = "disposal_code", type = IdType.UUID)
    private String disposalCode;

@ApiModelProperty(value = "派遣编号")
private String dispatchCode;

@ApiModelProperty(value = "处置人编号")
private String solverCode;

@ApiModelProperty(value = "处置人名称")
private String solverName;

@ApiModelProperty(value = "片区")
private String town;

@ApiModelProperty(value = "公司")
private String company;

@ApiModelProperty(value = "处置开始时间")
private Date solveStart;

@ApiModelProperty(value = "处置结束时间")
private Date solveEnd;

@ApiModelProperty(value = "处置描述")
private String solveDesc;

@ApiModelProperty(value = "照片（前）")
private String attBefore;

@ApiModelProperty(value = "照片（中）")
private String attOngoing;

@ApiModelProperty(value = "照片（后）")
private String attAfter;

@ApiModelProperty(value = "道路编号")
private String roadCode;

@ApiModelProperty(value = "道路名称")
private String roadName;

@ApiModelProperty(value = "路段编号")
private String roadSectionCode;

@ApiModelProperty(value = "路段开始名称")
private String roadSectionStart;

@ApiModelProperty(value = "路段结束名称")
private String roadSectionEnd;

@ApiModelProperty(value = "处置状态")
private String disposalState;

@ApiModelProperty(value = "项目组")
private String projectTeam;

@ApiModelProperty(value = "事件类型")
private String eventType;

@ApiModelProperty(value = "逻辑删除（0代表删除）")
    @TableField("isDel")
private String isDel;

@ApiModelProperty(value = "app 审核人")
private String appReview;

@ApiModelProperty(value = "web 审核人")
private String webReview;

private Double eventX;

private Double eventY;

private Double x;

private Double y;

private Integer checkStatus;

}
