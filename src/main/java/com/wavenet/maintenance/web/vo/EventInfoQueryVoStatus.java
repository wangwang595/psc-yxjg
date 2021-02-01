package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class EventInfoQueryVoStatus implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "处置人编号")
    private String solverCode;

    @ApiModelProperty(value = "处置人名称")
    private String solverName;

    @ApiModelProperty(value = "处置开始时间")
    private Date solveStart;

    @ApiModelProperty(value = "处置结束时间")
    private Date solveEnd;

    @ApiModelProperty(value = "处置描述")
    private String solveDesc;

    @ApiModelProperty(value = "事件主键")
    private String dispatchCode;



    @ApiModelProperty(value = "照片（前）")
    private String attBefore;

    @ApiModelProperty(value = "照片（中）")
    private String attOngoing;

    @ApiModelProperty(value = "照片（后）")
    private String attAfter;



    @ApiModelProperty(value = "主键")
    private String disposalCode;



    @ApiModelProperty(value = "审核状态")
    private  Integer checkStatus;
    @ApiModelProperty(value = "两小时工单 1 是 0否")
    private Integer twoHours;
    @ApiModelProperty(value = "事件主键")
    private String eventCode;

    @ApiModelProperty(value = "道路编号")
    private String patrolCode;

    @ApiModelProperty(value = "病害类型编号")
    private String eventTypeCode;

    @ApiModelProperty(value = "病害类型")
    private String eventType;

    @ApiModelProperty(value = "病害大类")
    private String eventTypeLarge;

    @ApiModelProperty(value = "病害小类")
    private String eventTypeSmall;

    @ApiModelProperty(value = "事件描述")
    private String eventDesc;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "X坐标")
    private Double x;

    @ApiModelProperty(value = "Y坐标")
    private Double y;

    @ApiModelProperty(value = "人员编号")
    private String personCode;

    @ApiModelProperty(value = "人员名称")
    private String personName;

    @ApiModelProperty(value = "公司")
    private String company;

    @ApiModelProperty(value = "片区")
    private String town;

    @ApiModelProperty(value = "上报时间")
    private Date reportDate;

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

    @ApiModelProperty(value = "附件")
    private String attachment;

    @ApiModelProperty(value = "事件状态")
    private String eventState;

    @ApiModelProperty(value = "逻辑删除，1显示")
    private Double deleteState;

    @ApiModelProperty(value = "道路养护，桥梁养护，护栏养护")
    private String type;

    @ApiModelProperty(value = "项目组")
    private String projectTeam;

    @ApiModelProperty(value = "网格单")
    private String source;

    @ApiModelProperty(value = "市政，非市政")
    private String eventStandard;
    @ApiModelProperty(value = "处置状态")
    private String disposalState;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "时间编号")
    private String timeZoneCode;

    @ApiModelProperty(value = "部件 设施 分类")
    private String partFacility;

    @ApiModelProperty(value = "大类")
    private String bigCategory;

    @ApiModelProperty(value = "中类")
    private String middleCategory;

    @ApiModelProperty(value = "小类")
    private String smallCategory;
}