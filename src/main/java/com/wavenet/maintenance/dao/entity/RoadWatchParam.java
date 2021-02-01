/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: RoadWatchParam.java
 * @Package com.wavenet.maintenance.dao.entity
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-23 14:50
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.dao.entity;

import com.wavenet.maintenance.common.FindArticleDto;
import com.wavenet.maintenance.web.vo.NameLvVO;
import com.wavenetframework.boot.common.param.OrderQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: RoadWatchParam
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-23 14:50     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "对象", description = "查询参数")
public class RoadWatchParam  {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目组")
    private String projectTeam;
    @ApiModelProperty(value = "片区")
    private String town;
    @ApiModelProperty(value = "用户名字")
    private String personName;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "用户编码")
    private String personCode;
    @ApiModelProperty(value = "状态")
    private String state;

    private FindArticleDto findArticleDto;
}
