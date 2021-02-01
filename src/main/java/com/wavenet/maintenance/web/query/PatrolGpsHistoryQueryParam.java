package com.wavenet.maintenance.web.query;

import com.wavenetframework.boot.common.param.OrderQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  查询参数对象
 * </pre>
 *
 * @author zll
 * @date 2020-03-13
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PatrolGpsHistoryQueryParam对象", description = "查询参数")
public class PatrolGpsHistoryQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "巡查编号")
    private String patrolCode;
}
