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
 * @date 2020-04-18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "TbRoadQueryVo对象", description = "查询参数")
public class TbRoadQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "路段编码")
        @TableField("S_ROAD_ID")
private String sRoadId;

    @ApiModelProperty(value = "路名")
        @TableField("S_NAME")
private String sName;

    @ApiModelProperty(value = "起点道路")
        @TableField("S_QD_ROAD")
private String sQdRoad;

    @ApiModelProperty(value = "终点道路")
        @TableField("S_ZD_ROAD")
private String sZdRoad;

    @ApiModelProperty(value = "路幅宽度")
        @TableField("N_ROAD_WIDTH")
private Double nRoadWidth;

    @ApiModelProperty(value = "道路长度")
        @TableField("N_ROAD_LENGTH")
private Double nRoadLength;

    @ApiModelProperty(value = "车道宽度")
        @TableField("N_ROAD_LANEWIDTH")
private Double nRoadLanewidth;

    @ApiModelProperty(value = "车道面积")
        @TableField("N_ROAD_LANEAREA")
private Double nRoadLanearea;

    @ApiModelProperty(value = "人行面积")
        @TableField("N_ROAD_SIDEWALKAREA")
private Double nRoadSidewalkarea;

    @ApiModelProperty(value = "路面等级")
        @TableField("S_ROADBED_GRADE")
private String sRoadbedGrade;

    @ApiModelProperty(value = "道路等级")
        @TableField("S_ROAD_GRADE")
private String sRoadGrade;

    @ApiModelProperty(value = "最后修建年份")
        @TableField("S_REBUILD_LASTYEAR")
private String sRebuildLastyear;

    @ApiModelProperty(value = "备注")
        @TableField("S_REMARK")
private String sRemark;

    @ApiModelProperty(value = "道路年份类别")
        @TableField("S_ROADYEAR_TYPE")
private String sRoadyearType;

    @ApiModelProperty(value = "板块")
        @TableField("S_PLATE")
private String sPlate;

}