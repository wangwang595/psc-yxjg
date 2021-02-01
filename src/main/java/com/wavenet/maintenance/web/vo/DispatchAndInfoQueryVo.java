/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: DispatchAndInfoQueryVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-07-26 17:27
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: DispatchAndInfoQueryVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-07-26 17:27     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class DispatchAndInfoQueryVo {
    
    @ApiModelProperty(value = "事件主键")

    private String dispatchCode;
    @ApiModelProperty(value = "问题主键")

    private String eventCode;
    @ApiModelProperty(value = "市政")

    private String eventStandard;
}
