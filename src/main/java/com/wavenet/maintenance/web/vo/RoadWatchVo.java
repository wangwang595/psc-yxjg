/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: RoadWatchVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-23 13:23
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import com.wavenet.maintenance.dao.entity.RoadWatch;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.input.PickResult;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: RoadWatchVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-23 13:23     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class RoadWatchVo {
    
     @ApiModelProperty(value = "总数")
    private Double count;
     @ApiModelProperty(value = "巡查总数")
    private Integer patrolcon;
     @ApiModelProperty(value = "问题总数")
    private Integer eventcon;
}
