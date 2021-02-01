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
 * @date 2020-08-12
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "TWelloperationQueryVo对象", description = "查询参数")
public class TWelloperationQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "井编号")
    @TableField("NUM")
    private String num;

    @ApiModelProperty(value = "井类型")
    @TableField("WELLTYPE")
    private String welltype;

    @ApiModelProperty(value = "井分类")
    @TableField("WELLCLASS")
    private String wellclass;

    @ApiModelProperty(value = "井状态")
    @TableField("WELLSTATE")
    private String wellstate;

    @ApiModelProperty(value = "事件ID")
    @TableField("MANGE_ID")
    private String mangeId;

    @ApiModelProperty(value = "巡查记录ID")
    @TableField("RECODE_ID")
    private String recodeId;

    @ApiModelProperty(value = "上报人ID")
    @TableField("MAN_ID")
    private String manId;

    @ApiModelProperty(value = "街镇ID")
    @TableField("TOWNID")
    private String townid;

    @ApiModelProperty(value = "所属公司")
    @TableField("COM_NAME")
    private String comName;

    @ApiModelProperty(value = "上报时间")
    @TableField("UPDATETIME")
    private Date updatetime;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "逻辑删")
    @TableField("DEL")
    private Integer del;

    @TableField("CATEGORY")
    private String category;

    @TableField("TYPE")
    private String type;

    @TableField("X")
    private String x;

    @TableField("Y")
    private String y;

    @TableField("ADDR")
    private String addr;

    @TableField("YHWORK")
    private String yhwork;

    @TableField("CURINGSTYLE")
    private String curingstyle;

    @ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "上报人名称")
    private String manName;

    @ApiModelProperty(value = "开始路段")
    private String roadStart;

    @ApiModelProperty(value = "结束路段")
    @TableField("road_end")
    private String roadEnd;

    @ApiModelProperty(value = "区域")
    @TableField("project_team")
    private String projectTeam;

    @ApiModelProperty(value = "问题计数")

    private Integer numbers;


}