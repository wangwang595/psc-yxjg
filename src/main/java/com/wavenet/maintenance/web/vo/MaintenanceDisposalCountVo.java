/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: MaintenanceDisposalCountVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-08-18 10:39
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: MaintenanceDisposalCountVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-08-18 10:39     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class MaintenanceDisposalCountVo {
    @ApiModelProperty(value = "官网长度")
    private String pipeTotal;

    @ApiModelProperty(value = "雨水井数量")
    private String curingWellNum;
    @ApiModelProperty(value = "检查口数量")
    private String curingOutletNum;
    @ApiModelProperty(value = "实际检查口数量")
    private String relOutNum;
    @ApiModelProperty(value = "实际雨水井数量")
    private String relWellNum;
    @ApiModelProperty(value = "养护长度")
    private Double curingLength;

    @ApiModelProperty(value = "实际养护长度")
    private Double realLength;

    @ApiModelProperty(value = "检查井")
    private Double manhole;

    @ApiModelProperty(value = "雨水口")
    private Double catchBasin;

}
