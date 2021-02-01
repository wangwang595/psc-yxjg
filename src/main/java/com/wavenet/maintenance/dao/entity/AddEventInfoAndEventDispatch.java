/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: AddEventInfoAndEventDispatch.java
 * @Package com.wavenet.maintenance.dao.entity
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-07-01 21:47
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.dao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: AddEventInfoAndEventDispatch
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-07-01 21:47     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class AddEventInfoAndEventDispatch {

    private String eventCode;

    @ApiModelProperty("巡查记录编号")
    private String patrolCode;

    @ApiModelProperty("事件类型编号")
    private String eventTypeCode;

    @ApiModelProperty("事件类型(路面类型，附属设施，人行道)")
    private String eventType;

    @ApiModelProperty("事件大类")
    private String eventTypeLarge;

    @ApiModelProperty("事件小类")
    private String eventTypeSmall;

    @ApiModelProperty("事件描述")
    private String eventDesc;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("经度")
    private Double x;

    @ApiModelProperty("纬度")
    private Double y;

    @ApiModelProperty("上报人编码")
    private String personCode;

    @ApiModelProperty("上报人名称")
    private String personName;

    @ApiModelProperty("上报公司名称")
    private String company;

    @ApiModelProperty("片区")
    private String town;

    @ApiModelProperty("上报时间")
    private Date reportDate;

    @ApiModelProperty("道路编号")
    private String roadCode;

    @ApiModelProperty("道路名称")
    private String roadName;

    @ApiModelProperty("路段编号")
    private String roadSectionCode;

    @ApiModelProperty("路段起始路名")
    private String roadSectionStart;

    @ApiModelProperty("路段终止路名")
    private String roadSectionEnd;

    @ApiModelProperty("附件")
    private String attachment;

    @ApiModelProperty("状态")
    private String eventState;

    @ApiModelProperty("逻辑删除编码")
    private Double deleteState;

    @ApiModelProperty("类型（桥梁，护栏，路面）")
    private String type;

    @ApiModelProperty("项目组")
    private String projectTeam;

    @ApiModelProperty("来源(网格单)")
    private String source;

    @ApiModelProperty("市政,非市政")
    private String eventStandard;

    private String dispatchCode;

    private String dispatcherCode;

    private String dispatcherName;

    private Date dispatcherDate;

    private String dispatcherDesc;

    private String recipientCode;

    private String recipientName;

    private String dispatchState;

    private String eventNum;

    @ApiModelProperty(value = "部件 设施 分类")
    private String partFacility;

    @ApiModelProperty(value = "大类")
    private String bigCategory;

    @ApiModelProperty(value = "中类")
    private String middleCategory;

    @ApiModelProperty(value = "小类")
    private String smallCategory;
}
