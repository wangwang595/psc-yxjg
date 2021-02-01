package com.wavenet.maintenance.web.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MaintenanceDetailPercenvO  {

    private static final long serialVersionUID = 1L;




    @ApiModelProperty(value = "百分比")
    private String percentage;
    @ApiModelProperty(value = "主键")
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



    private List<MaintenanceDetail> maintenanceDetailList;



}