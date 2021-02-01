package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("PATROL_GPS_HISTORY")
@ApiModel(value = "PatrolGpsHistory对象", description = "")
public class PatrolGpsHistory extends BaseEntity {


    @TableId(value = "gps_code", type = IdType.UUID)
@ApiModelProperty(value = "GPS编号")
private String gpsCode;

@ApiModelProperty(value = "巡查编号")
private String patrolCode;

@ApiModelProperty(value = "人员编号")
private String personCode;

@ApiModelProperty(value = "上传时间")
private String uplaodDate;

@ApiModelProperty(value = "X坐标")
private String x;

@ApiModelProperty(value = "Y坐标")
private String y;

@ApiModelProperty(value = "类型")
private String type;

@ApiModelProperty(value = "路段编号")
private String roadSectionCode;

@ApiModelProperty(value = "平均速度")
private Double speed;

@ApiModelProperty(value = "公里数")
private Double mileage;

@ApiModelProperty(value = "状态")
private String state;

@ApiModelProperty(value = "接收状态")
private String receiveState;

@ApiModelProperty(value = "公司")
private String company;

}
