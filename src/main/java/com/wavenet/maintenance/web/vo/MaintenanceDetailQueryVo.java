package com.wavenet.maintenance.web.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @date 2020-03-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MaintenanceDetailQueryVo对象", description = "查询参数")
public class MaintenanceDetailQueryVo implements Serializable {
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



    @ApiModelProperty(value = "片区")
    private String town;

    @ApiModelProperty(value = "项目组")
    private String projectTeam;

    @ApiModelProperty(value = "来源，网格单和自派遣")
    private String source;

    @ApiModelProperty(value = "计划内，计划外")
    private String plans;



    @ApiModelProperty(value = "结束时间")
    private Date endTime;







    @ApiModelProperty(value = "附件")
    private String enclosure;
    @ApiModelProperty("养护长度")
    private Double curingLength;

    @ApiModelProperty("平均")
    private String avgs;

    @ApiModelProperty("道路")
    private String relavg;
//
//    @ApiModelProperty(value = "污泥站点")
//    private String sludgeSite;

}