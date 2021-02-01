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
 * @date 2020-03-30
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PatrolInfoQueryVo对象", description = "查询参数")
public class PatrolInfoQueryVo implements Serializable {
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


    @ApiModelProperty(value = "轨迹图")
    private String trajectoryImg;


    private int eventInfoNum;

    @ApiModelProperty(value = "部件 设施 分类")
    private String partFacility;

    @ApiModelProperty(value = "大类")
    private String bigCategory;

    @ApiModelProperty(value = "中类")
    private String middleCategory;

    @ApiModelProperty(value = "小类")
    private String smallCategory;




}