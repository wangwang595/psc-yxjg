/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: CountByMileageQueryVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-09-18 9:56
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import com.wavenet.maintenance.dao.entity.RoadWatch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: CountByMileageQueryVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-09-18 9:56     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class CountByMileageQueryVo {
    private List<RoadWatch> mileage;

    @ApiModelProperty(value = "总数")
    private String total;
}
