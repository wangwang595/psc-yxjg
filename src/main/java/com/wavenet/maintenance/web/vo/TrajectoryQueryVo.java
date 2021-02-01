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
 * @date 2020-05-21
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "TrajectoryQueryVo对象", description = "查询参数")
public class TrajectoryQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(value = "OBJECTID", type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Integer objectid;
    @ApiModelProperty(value = " gps编码")
    private String gpsCode;
    @ApiModelProperty(value = "用户编号")
    private String patrolCode;
    @ApiModelProperty(value = " 地图X坐标")
    private String personCode;
    @ApiModelProperty(value = "地图Y坐标")
    private Double x;
    @ApiModelProperty(value = "类型")
    private Double y;
    @ApiModelProperty(value = "路段编号")
    private String type;
    @ApiModelProperty(value = "平均速度")
    private String roadSectionCode;
    @ApiModelProperty(value = "里程数")
    private Double speed;
    @ApiModelProperty(value = "轨迹")
    private String mileage;
    @ApiModelProperty(value = "最大X坐标")
    private String shape;



    private Date uplaodDate;

}