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

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("MAINTENANCE_DETAIL")
@ApiModel(value = "MaintenanceDetail对象", description = "")
public class MaintenanceDetails extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("养护明细编码,add时不必填")
    @TableId(value = "detail_code", type = IdType.UUID)
    private String detailCode;

    @ApiModelProperty("养护计划编码")
    private String planCode;

    @ApiModelProperty("道路编号")
    private String roadCode;

    @ApiModelProperty("道路名称")
    private String roadName;

    @ApiModelProperty("路段编号")
    private String roadSectionCode;

    @ApiModelProperty("路段起始名")
    private String roadSectionStart;

    @ApiModelProperty("路段终止路名")
    private String roadSectionEnd;

    @ApiModelProperty("养护类型")
    private String type;

    @ApiModelProperty("养护年度")
    private Double year;

    @ApiModelProperty("养护月份")
    private Double month;

    @ApiModelProperty("养护状态")
    private String curingState;

    @ApiModelProperty("养护单位")
    private String company;

    @ApiModelProperty("管总长度")
    private Double pipeTotal;

    @ApiModelProperty("主管长度")
    private Double pipeMain;

    @ApiModelProperty("连管长度")
    private Double pipeBranch;

    @ApiModelProperty("检查井")
    private Double manhole;

    @ApiModelProperty("雨水口")
    private Double catchBasin;
    @ApiModelProperty("管道类型")
    private String pipeGrade;
    @ApiModelProperty("片区")
    private String town;
    @ApiModelProperty("项目组")
    private String projectTeam;
    @ApiModelProperty("网格单")
    private String source;
    @ApiModelProperty("计划")
    private String plans;
    @ApiModelProperty("附件")
    private String enclosure;
    @ApiModelProperty("截至事件")
    private String endTime;
    
    private List<String> picuter;

}
