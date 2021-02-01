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

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)

@TableName("PATROL_INFO")
@ApiModel(value = "PatrolInfo对象", description = "")
public class PatrolInfoS implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "patrol_code", type = IdType.UUID)
    private String patrolCode;

    private String roadCode;

    private String roadName;

    private String roadSectionCode;

    private String roadSectionStart;

    private String roadSectionEnd;

    private Date dateStart;

    private Date dateEnd;

    private Double mileage;

    private String type;

    private String personCode;

    private String personName;

    private String company;

    private String town;

    private String state;

    private String deleteState;

    private String projectTeam;

    private String speed;

    private String trajectoryImg;
    
    private  Integer Querys;

    @ApiModelProperty(value = "部件 设施 分类")
    private String partFacility;

    @ApiModelProperty(value = "大类")
    private String bigCategory;

    @ApiModelProperty(value = "中类")
    private String middleCategory;

    @ApiModelProperty(value = "小类")
    private String smallCategory;

}