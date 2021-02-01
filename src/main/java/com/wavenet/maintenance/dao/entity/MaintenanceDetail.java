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
@TableName("MAINTENANCE_DETAIL")
@ApiModel(value = "MaintenanceDetail对象", description = "")
public class MaintenanceDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键")
@TableId(value = "detail_code", type = IdType.UUID)
    private String detailCode;

@ApiModelProperty(value = "养护计划关联编号")
private String planCode;

@ApiModelProperty(value = "道路编号")
private String roadCode;

@ApiModelProperty(value = "道路名称")
private String roadName;

@ApiModelProperty(value = "路段起始编号")
private String roadSectionCode;

@ApiModelProperty(value = "路段开始名称")
private String roadSectionStart;

@ApiModelProperty(value = "路段结束名称")
private String roadSectionEnd;

@ApiModelProperty(value = "养护类型")
private String type;

@ApiModelProperty(value = "年")
private Double year;

@ApiModelProperty(value = "月")
private Double month;

@ApiModelProperty(value = "养护状态")
private String curingState;

@ApiModelProperty(value = "公司")
private String company;

@ApiModelProperty(value = "管道总长")
private Double pipeTotal;

@ApiModelProperty(value = "主管长度")
private Double pipeMain;

@ApiModelProperty(value = "连管长度")
private Double pipeBranch;

@ApiModelProperty(value = "检查井")
private Double manhole;

@ApiModelProperty(value = "雨水口")
private Double catchBasin;

@ApiModelProperty(value = "管道级别")
private String pipeGrade;

@ApiModelProperty(value = "逻辑删除0删，1保留")
private String deleteState;

@ApiModelProperty(value = "片区")
private String town;

@ApiModelProperty(value = "项目组")
private String projectTeam;

@ApiModelProperty(value = "来源，网格单和自派遣")
private String source;

@ApiModelProperty(value = "计划内，计划外")
private String plans;

@ApiModelProperty(value = "附件")
    @TableField("Enclosure")
private String Enclosure;

@ApiModelProperty(value = "结束时间")
private Date endTime;

}
