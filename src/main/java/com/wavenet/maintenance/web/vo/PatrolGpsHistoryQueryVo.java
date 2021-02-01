package com.wavenet.maintenance.web.vo;

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
@ApiModel(value = "PatrolGpsHistoryQueryVo对象", description = "查询参数")
public class PatrolGpsHistoryQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "GPS编号")
    private String gpsCode;

    @ApiModelProperty(value = "巡查编号")
    private String patrolCode;

    @ApiModelProperty(value = "人员编号")
    private String personCode;

    @ApiModelProperty(value = "上传时间")
    private String uplaodDate;

    @ApiModelProperty(value = "X坐标")
    private Double x;

    @ApiModelProperty(value = "Y坐标")
    private Double y;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "路段编号")
    private String roadSectionCode;

    @ApiModelProperty(value = "平均速度")
    private Double speed;

    @ApiModelProperty(value = "公里数")
    private Double mileage;

    @ApiModelProperty(value = "公司")
    private String company;



    public String toString (){
        return y+ " " +x+",";
    }


}