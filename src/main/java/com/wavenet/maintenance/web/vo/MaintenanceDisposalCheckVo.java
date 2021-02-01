package com.wavenet.maintenance.web.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MaintenanceDisposalCheckVo  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "disposal_code", type = IdType.UUID)
    private String disposalCode;

    @ApiModelProperty("养护计划编号")
    private String detailCode;

    @ApiModelProperty("道路编码")
    private String roadCode;

    @ApiModelProperty("道路名称")
    private String roadName;

    @ApiModelProperty("路段编号")
    private String roadSectionCode;

    @ApiModelProperty("路段起始路名")
    private String roadSectionStart;

    @ApiModelProperty("路段终止路名")
    private String roadSectionEnd;

    @ApiModelProperty("养护类型")
    private String type;

    @ApiModelProperty("开始时间")

    private String dateStart;

    @ApiModelProperty("结束时间")
    private String dateEnd;

    @ApiModelProperty("养护人编码")
    private String personCode;

    @ApiModelProperty("养护人中文名")
    private String personName;

    @ApiModelProperty("养护公司名称")
    private String company;

    @ApiModelProperty("街镇/区域")
    private String town;

    @ApiModelProperty("养护方式")
    private String way;

    @ApiModelProperty("0 删除 1保留")
    private String deleteState;

    @ApiModelProperty("附件(开工前)")
    private String attBefore;

    @ApiModelProperty("附件(开工中)")
    private String attOngoing;

    @ApiModelProperty("附件(开工后)")
    private String attAfter;

    @ApiModelProperty("养护状态")
    private String curingState;

    @ApiModelProperty("养护长度")
    private Double curingLength;

    @ApiModelProperty("实际养护长度")
    private Double realLength;

    @ApiModelProperty("检查井数量")
    private Double curingWellNum;

    @ApiModelProperty("实际检查井数量")
    private Double realWellNum;

    @ApiModelProperty("雨水口数量")
    private Double curingOutletNum;

    @ApiModelProperty("实际雨水口数量")
    private Double realOutNum;

    @ApiModelProperty("X坐标")
    private String xx;

    @ApiModelProperty("Y坐标")
    private String yy;

    private String siltLength;

    private String siltRellength;

    private String checkStatus;
    
    private Double pipeTotal;

    private String plans;
    private  String appReview;

    private  String webReview;

    //备注
    private  String sBz;

}
