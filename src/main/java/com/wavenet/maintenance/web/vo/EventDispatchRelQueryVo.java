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
 * @date 2020-03-30
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "EventDispatchRelQueryVo对象", description = "查询参数")
public class EventDispatchRelQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "事件编码")
    private String eventDispatchCode;

@ApiModelProperty(value = "问题编码")
    private String eventCode;

@ApiModelProperty(value = "事件编号")
    private String dispatchCode;

}