package com.wavenet.maintenance.web.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EventDisposalSourceVo  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "disposal_code", type = IdType.UUID)

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

    @ApiModelProperty(value = "处置开始")
    private Date solveStart;

    @ApiModelProperty(value = "处置结束")
    private Date solveEnd;

    @ApiModelProperty(value = "处置描述")
    private String solveDesc;

    @ApiModelProperty(value = "处置前照片")
    private String attBefore;

    @ApiModelProperty(value = "处置中照片")
    private String attOngoing;

    @ApiModelProperty(value = "处置后照片")
    private String attAfter;

    @ApiModelProperty(value = "道路编号")
    private String roadCode;

    @ApiModelProperty(value = "道路名称")
    private String roadName;

    @ApiModelProperty(value = "路段编号")
    private String roadSectionCode;

    @ApiModelProperty(value = "路段开始")
    private String roadSectionStart;

    @ApiModelProperty(value = "路段结束")
    private String roadSectionEnd;

    @ApiModelProperty(value = "处置状态")
    private String disposalState;

    @ApiModelProperty(value = "项目组")
    private String projectTeam;

    @ApiModelProperty(value = "事件类型")
    private String eventType;

    @ApiModelProperty(value = "逻辑删除（")
    private String isdel;

    @ApiModelProperty(value = "审核状态")
    private  Integer checkStatus;

    @ApiModelProperty(value = "派遣方式")
    private String source;

    @ApiModelProperty(value = "app审核")
    private  String appReview;

    @ApiModelProperty(value = "web审核")
    private  String webReview;


    @ApiModelProperty(value = "事件编码")
    private String eventCode;

    @ApiModelProperty(value = "类型")
    private String type;
    //备注
    @ApiModelProperty(value = "备注")
    private  String sBz;
}
