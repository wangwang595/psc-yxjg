/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: EventInfoSourceByMonthQueryVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-09-22 9:56
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName: EventInfoSourceByMonthQueryVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-09-22 9:56     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class EventInfoSourceByMonthQueryVo {
    
     @ApiModelProperty(value = "月份")
    private String month;
     @ApiModelProperty(value = "网格单")
    private String wgd;
     @ApiModelProperty(value = "自派遣")
    private String zpq;
     @ApiModelProperty(value = "巡查发现")
    private String xcfx;
}
