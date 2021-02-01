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
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "EventDisposalQueryVo对象", description = "查询参数")
public class
EventDisposalQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;







    @ApiModelProperty(value = "app 审核人")
    private String appReview;

    @ApiModelProperty(value = "web 审核人")
    private String webReview;
    @ApiModelProperty(value = "x坐标")
    private Double eventX;
    @ApiModelProperty(value = "y坐标")
    private Double eventY;
    @ApiModelProperty(value = "x坐标")
    private Double x;
    @ApiModelProperty(value = "y坐标")
    private Double y;
    @ApiModelProperty(value = "审核状态")
    private Integer checkStatus;

    @ApiModelProperty(value = "事件主键")
    private String eventCode;
    @ApiModelProperty(value = "道路编号")
    private String patrolCode;
    @ApiModelProperty(value = "病害类型编号")
    private String eventTypeCode;
    @ApiModelProperty(value = "病害大类")
    private String eventTypeLarge;
    @ApiModelProperty(value = "病害小类")
    private String eventTypeSmall;
    @ApiModelProperty(value = "事件描述")
    private String eventDesc;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "人员编号")
    private String personCode;
    @ApiModelProperty(value = "人员名称")
    private String personName;
    @ApiModelProperty(value = "时间")
    private Date reportDate;
    @ApiModelProperty(value = "附件")
    private String attachment;
    @ApiModelProperty(value = "状态")
    private String eventState;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "派遣方式")
    private String source;
    @ApiModelProperty(value = "市政")
    private String eventStandard;




    @ApiModelProperty(value = "主键")
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




}