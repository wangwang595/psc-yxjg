/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: EventCountQueryVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-09-18 15:49
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: EventCountQueryVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-09-18 15:49     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class EventCountQueryVo {
    @ApiModelProperty(value = "派遣方式")
    private String source;

    private String num;
    @ApiModelProperty(value = "片区")
    private String town;
}
