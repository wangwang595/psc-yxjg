package com.wavenet.maintenance.web.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@ApiModel(value = "MaintenanceDisposalQueryVo对象", description = "查询参数")
public class MaintenanceDisposalQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "管道等级")
    private String pipeGrade;
    @ApiModelProperty(value = "项目团队")
    private String projectTeam;
    @ApiModelProperty(value = "派遣方式")
    private String source;
    @ApiModelProperty(value = "计划")
    private String plans;
    @ApiModelProperty(value = "外壳")
    private String enclosure;
    @ApiModelProperty(value = "井")
    private Double manhole;
    @ApiModelProperty(value = "雨水口")
    private Double catchBasin;
    @ApiModelProperty(value = "管道总长")
    private Double pipeTotal;


    @ApiModelProperty(value = "主键")
    private String disposalCode;

    @ApiModelProperty(value = "养护明细编号")
    private String detailCode;

    @ApiModelProperty(value = "道路编号")
    private String roadCode;

    @ApiModelProperty(value = "道路名称")
    private String roadName;

    @ApiModelProperty(value = "路段编号")
    private String roadSectionCode;

    @ApiModelProperty(value = "路段起始路名")
    private String roadSectionStart;

    @ApiModelProperty(value = "路段终止路名")
    private String roadSectionEnd;

    @ApiModelProperty(value = "养护类型")
    private String type;

    @ApiModelProperty(value = "开始时间")
    private Date dateStart;

    @ApiModelProperty(value = "结束时间")
    private Date dateEnd;

    @ApiModelProperty(value = "养护人编号")
    private String personCode;

    @ApiModelProperty(value = "养护人名称")
    private String personName;

    @ApiModelProperty(value = "公司")
    private String company;

    @ApiModelProperty(value = "片区")
    private String town;

    @ApiModelProperty(value = "方式")
    private String way;

    @ApiModelProperty(value = "逻辑删")
    private String deleteState;

    @ApiModelProperty(value = "照片（前）")
    private String attBefore;

    @ApiModelProperty(value = "照片（中）")
    private String attOngoing;

    @ApiModelProperty(value = "照片（后）")
    private String attAfter;

    @ApiModelProperty(value = "养护状态")
    private String curingState;

    @ApiModelProperty(value = "养护长度")
    private Double curingLength;

    @ApiModelProperty(value = "实际养护长度")
    private Double realLength;

    @ApiModelProperty(value = "雨水井数量")
    private Integer curingWellNum;

    @ApiModelProperty(value = "检查口数量")
    private Integer curingOutletNum;

    @ApiModelProperty(value = "实际雨水井数量")
    private Integer realWellNum;

    @ApiModelProperty(value = "实际检查口数量")
    private Integer realOutNum;

    @ApiModelProperty(value = "X坐标")
    private String xx;

    @ApiModelProperty(value = "Y坐标")
    private String yy;

    @ApiModelProperty(value = "淤泥厚度")
    private Double siltLength;

    @ApiModelProperty(value = "实际淤泥厚度")
    private Double siltRellength;

    @ApiModelProperty(value = "审核状态")
    private Integer checkStatus;

    @ApiModelProperty(value = "app 审核人")
    private String appReview;

    @ApiModelProperty(value = "web 审核人")
    private String webReview;

    @ApiModelProperty(value = "备注")
    private String sBz;



    @ApiModelProperty(value = "污泥站点")
    private String sludgeSite;

}