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

/**
 * <pre>
 *
 * </pre>
 *
 * @author zll
 * @since 2020-03-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("EVENT_TYPE")
@ApiModel(value = "EventType对象", description = "")
public class EventType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "event_type_code", type = IdType.UUID)
    @ApiModelProperty(value = "code编码")
    private String eventTypeCode;
    @ApiModelProperty(value = "父类编码")
    private String eventTypePcode;
    @ApiModelProperty(value = "市政")
    private String eventStandard;
    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "单位")
    private String unit;
    @ApiModelProperty(value = "备注")
    private String note;
    @ApiModelProperty(value = "排序")
    private Double sort;
    @ApiModelProperty(value = "逻辑删")
    private String state;
    @ApiModelProperty(value = "厚度")
    private Integer isthick;
    @ApiModelProperty(value = "排序")
    private Integer lv;
}
