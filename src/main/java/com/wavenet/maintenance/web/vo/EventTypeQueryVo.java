package com.wavenet.maintenance.web.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 *  查询结果对象
 * </pre>
 *
 * @author zll
 * @date 2020-03-24
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "EventTypeQueryVo对象", description = "查询参数")
public class EventTypeQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer lv;


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
    @ApiModelProperty(value = "厚度")
    private Integer isthick;

}