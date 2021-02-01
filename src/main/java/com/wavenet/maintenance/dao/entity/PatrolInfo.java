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
@TableName("PATROL_INFO")
@ApiModel(value = "PatrolInfo对象", description = "")
public class PatrolInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "巡查编号")
@TableId(value = "patrol_code", type = IdType.UUID)
    private String patrolCode;

@ApiModelProperty(value = "道路编号")
private String roadCode;

@ApiModelProperty(value = "道路名称")
private String roadName;

@ApiModelProperty(value = "道路编号")
private String roadSectionCode;

@ApiModelProperty(value = "道路起始名称")
private String roadSectionStart;

@ApiModelProperty(value = "道路终止名称")
private String roadSectionEnd;

@ApiModelProperty(value = "开始时间")
private Date dateStart;

@ApiModelProperty(value = "结束时间")
private Date dateEnd;

@ApiModelProperty(value = "里程")
private Double mileage;

@ApiModelProperty(value = "类型")
private String type;

@ApiModelProperty(value = "人员编号")
private String personCode;

@ApiModelProperty(value = "人员名称")
private String personName;

@ApiModelProperty(value = "公司")
private String company;

@ApiModelProperty(value = "片区")
private String town;

@ApiModelProperty(value = "状态")
private String state;

@ApiModelProperty(value = "逻辑删除，1保留")
    @TableField("DELETE_STATE")
private Double deleteState;

@ApiModelProperty(value = "项目组")
private String projectTeam;

@ApiModelProperty(value = "平均速度")
private String speed;

@ApiModelProperty(value = "实际里程数")
private Double realMileage;

@ApiModelProperty(value = "轨迹图")
private String trajectoryImg;

    @ApiModelProperty(value = "部件 设施 分类")
    private String partFacility;

    @ApiModelProperty(value = "大类")
    private String bigCategory;

    @ApiModelProperty(value = "中类")
    private String middleCategory;

    @ApiModelProperty(value = "小类")
    private String smallCategory;

}
