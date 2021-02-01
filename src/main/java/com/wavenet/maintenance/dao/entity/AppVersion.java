package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("APP_VERSION")
@ApiModel(value = "AppVersion对象", description = "")
public class AppVersion extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键ID")
    @TableField("VERSIONID")
private Integer versionid;

@ApiModelProperty(value = "版本号")
    @TableField("VERSIONNUM")
private String versionnum;

@ApiModelProperty(value = "标识（是否更新）")
    @TableField("ISUPDATE")
private Integer isupdate;

@ApiModelProperty(value = "地址")
    @TableField("URL")
private String url;

@ApiModelProperty(value = "更新时间")
    @TableField("UPDATETIME")
private Date updatetime;

@ApiModelProperty(value = "更新内容")
    @TableField("UPDATECONTENT")
private String updatecontent;

@ApiModelProperty(value = "模块")
    @TableField("Modular")
private String Modular;

}
