/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: RoadWacth.java
 * @Package com.wavenet.maintenance.dao.entity
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-22 17:16
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.dao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @ClassName: RoadWacth
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-22 17:16     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class RoadWatch {

    @ApiModelProperty(value = "巡查编号")
    private String patrolCode;

    @ApiModelProperty(value = "道路编号")
    private String roadCode;

    @ApiModelProperty(value = "道路名称")
    private String roadName;

    @ApiModelProperty(value = "道路编号")
    private String roadSectionCode;

    @ApiModelProperty(value = "道路起始名称")
    private String roadSectionStart;

    @ApiModelProperty(value = "道路终止名称")
    private String roadSectionEnd;

    @ApiModelProperty(value = "开始时间")
    private Date dateStart;

    @ApiModelProperty(value = "结束时间")
    private Date dateEnd;

    @ApiModelProperty(value = "里程")
    private Double mileage;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "人员编号")
    private String personCode;

    @ApiModelProperty(value = "人员名称")
    private String personName;

    @ApiModelProperty(value = "公司")
    private String company;

    @ApiModelProperty(value = "片区")
    private String town;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "平均速度")
    private String speed;

    @ApiModelProperty(value = "附件")
    private String attachment;

    @ApiModelProperty(value = "项目组")
    private String projectTeam;

    @ApiModelProperty(value = "统计个数")
    private String con;

    @ApiModelProperty(value = "轨迹")
    private String trajectoryImg;

    private  String eventCode;
}
