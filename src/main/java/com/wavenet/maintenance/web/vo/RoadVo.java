/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: RoadVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: hp
 * @date: 2020-4-21 20:23
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import com.wavenet.maintenance.dao.entity.inPlans;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: RoadVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: hp
 * @date: 2020-4-21 20:23     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class RoadVo {

    @ApiModelProperty(value = "路code")
    private String roadCode;
    @ApiModelProperty(value = "路名")
    private String roadName;
    @ApiModelProperty(value = "路段code")
    private String roadSectionCode;
    @ApiModelProperty(value = "起始路段")
    private String roadSectionStart;
    @ApiModelProperty(value = "结束路段")
    private String roadSectionEnd;
}
