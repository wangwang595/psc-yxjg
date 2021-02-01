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
@ApiModel(value = "EventDispatchQueryVo对象", description = "查询参数")
public class EventDispatchQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "处置前图片")
private String attBefore;
    @ApiModelProperty(value = "处置中图片")
private String attOngoing;
    @ApiModelProperty(value = "处置后图片")
private String attAfter;
    @ApiModelProperty(value = "处置主键")
private String disposalCode;
    @ApiModelProperty(value = "主键")
    private String dispatchCode;

    @ApiModelProperty(value = "派遣人编号")
    private String dispatcherCode;

    @ApiModelProperty(value = "派遣人名称")
    private String dispatcherName;

    @ApiModelProperty(value = "派遣时间")
    private Date dispatcherDate;

    @ApiModelProperty(value = "派遣描述")
    private String dispatcherDesc;

    @ApiModelProperty(value = "接收人编号")
    private String recipientCode;

    @ApiModelProperty(value = "接收人姓名")
    private String recipientName;

    @ApiModelProperty(value = "片区")
    private String town;

    @ApiModelProperty(value = "公司")
    private String company;

    @ApiModelProperty(value = "类型")
    private String type;

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

    @ApiModelProperty(value = "派遣状态")
    @TableField("DISPATCH_STATE")
    private String dispatchState;



}