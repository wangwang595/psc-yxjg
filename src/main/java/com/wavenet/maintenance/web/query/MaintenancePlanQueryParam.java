package com.wavenet.maintenance.web.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.wavenetframework.boot.common.param.OrderQueryParam;

/**
 * <pre>
 *  查询参数对象
 * </pre>
 *
 * @author zll
 * @date 2020-04-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MaintenancePlanQueryParam对象", description = "查询参数")
public class MaintenancePlanQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
}
