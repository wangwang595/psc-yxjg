package com.wavenet.maintenance.web.vo;

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
 * @date 2020-03-28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "DictQueryVo对象", description = "查询参数")
public class DictQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "字典主键")
    private String dictCode;

    @ApiModelProperty(value = "类型编号")
    private String typeCode;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "排序")
    private Double sort;

}





