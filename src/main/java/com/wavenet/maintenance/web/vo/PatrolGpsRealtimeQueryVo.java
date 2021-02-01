package com.wavenet.maintenance.web.vo;

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
@ApiModel(value = "PatrolGpsRealtimeQueryVo对象", description = "查询参数")
public class PatrolGpsRealtimeQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "轨迹编号")
    private String gpsCode;

    @ApiModelProperty(value = "巡查编号")
    private String patrolCode;

    @ApiModelProperty(value = "人员编号")
    @TableId("person_code")
    private String personCode;

    @ApiModelProperty(value = "上传时间")
    private Date uplaodDate;

    @ApiModelProperty(value = "X坐标")
    private String x;

    @ApiModelProperty(value = "Y坐标")
    private String y;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "道路编号")
    private String roadSectionCode;

    @ApiModelProperty(value = "平均速度")
    private Double speed;

    @ApiModelProperty(value = "里程数")
    private Double mileage;

    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "公司")
    private String company;
    @ApiModelProperty(value = "电话")
    private String phone;



}