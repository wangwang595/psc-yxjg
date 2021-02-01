/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: EveryReportError.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-08-03 17:29
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: EveryReportError
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-08-03 17:29     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class EveryReportErrorVo {
     @ApiModelProperty(value = "处置编码")
    private String disposalCode;
     @ApiModelProperty(value = "事件编码")
    private String dispatchCode;
     @ApiModelProperty(value = "事件编码")
    private String detailCode;
     @ApiModelProperty(value = "状态")
    private String state;
     @ApiModelProperty(value = "巡查编码")
    private String patrolCode;
     @ApiModelProperty(value = "里程数")
    private Double mileage;
     @ApiModelProperty(value = "指令")
    private String emergencyId;
}
