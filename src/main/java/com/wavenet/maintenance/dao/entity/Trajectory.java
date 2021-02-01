package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.mongodb.client.model.geojson.Geometry;
import com.wavenetframework.boot.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author zll
 * @since 2020-05-21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("TRAJECTORY")
@ApiModel(value = "Trajectory对象", description = "")
public class Trajectory extends BaseEntity {

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
    @ApiModelProperty(value = "最小X坐标")
    private String minx;
    @ApiModelProperty(value = "最大Y坐标")
    private String maxx;
    @ApiModelProperty(value = "最小Y坐标")
    private String miny;
    @ApiModelProperty(value = "巡查编号")
    private String maxy;

}
