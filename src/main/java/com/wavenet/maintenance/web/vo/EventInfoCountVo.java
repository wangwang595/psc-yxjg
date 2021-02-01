/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: EventInfoCountVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-07-30 19:41
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: EventInfoCountVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-07-30 19:41     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class EventInfoCountVo {

    @ApiModelProperty(value = "总数")
    private String eventCount;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "项目组")
    private String projectTeam;
    @ApiModelProperty(value = "公司")
    private String company;
}
