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
@TableName("T_WELLOPERATION")
@ApiModel(value = "TWelloperation对象", description = "")
public class TWelloperation extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

@ApiModelProperty(value = "病害类型")
    @TableField("TYPE")
private String type;

@ApiModelProperty(value = "X坐标")
    @TableField("X")
private String x;

@ApiModelProperty(value = "Y坐标")
    @TableField("Y")
private String y;

@ApiModelProperty(value = "地址")
    @TableField("ADDR")
private String addr;

@ApiModelProperty(value = "养护方式")
    @TableField("YHWORK")
private String yhwork;

@ApiModelProperty(value = "工作方式")
    @TableField("CURINGSTYLE")
private String curingstyle;

@ApiModelProperty(value = "附件")
private String enclosure;

@ApiModelProperty(value = "上报人名称")
    @TableField("MAN_NAME")
private String manName;

    @TableField("CATEGORY")
private String category;

@TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "开始路段")
    private String roadStart;

    @ApiModelProperty(value = "结束路段")
    @TableField("road_end")
    private String roadEnd;

    @ApiModelProperty(value = "区域")
    @TableField("project_team")
    private String projectTeam;
}
