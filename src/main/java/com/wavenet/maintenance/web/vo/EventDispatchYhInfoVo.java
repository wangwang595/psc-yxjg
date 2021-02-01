/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: EventDispatchYhInfoVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-08 10:31
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: EventDispatchYhInfoVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-08 10:31     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class EventDispatchYhInfoVo {
    
    
    @ApiModelProperty(value = "事件编码")
    private String dispatchCode;

    @ApiModelProperty(value = "派遣人编号")
    private String dispatcherCode;

    @ApiModelProperty(value = "派遣人名称")
    private String dispatcherName;

    @ApiModelProperty(value = "派遣时间")
    private Date dispatcherDate;

    @ApiModelProperty(value = "派遣描述")
    private String dispatcherDesc;

    @ApiModelProperty(value = "接收人编号")
    private String recipientCode;

    @ApiModelProperty(value = "接收人姓名")
    private String recipientName;

    @ApiModelProperty(value = "片区")
    private String town;

    @ApiModelProperty(value = "公司")
    private String company;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "道路编号")
    private String roadCode;

    @ApiModelProperty(value = "道路名称")
    private String roadName;

    @ApiModelProperty(value = "路段编号")
    private String roadSectionCode;

    @ApiModelProperty(value = "路段开始名称")
    private String roadSectionStart;

    @ApiModelProperty(value = "路段结束名称")
    private String roadSectionEnd;

    @ApiModelProperty(value = "处置状态")
    private String disposalState;

    @ApiModelProperty(value = "处置前")
    private String attBefore;

    @ApiModelProperty(value = "处置中")
    private String attOngoing;

    @ApiModelProperty(value = "处置后图片")
    private String attAfter;

    @ApiModelProperty(value = "解决开始")
    private String solveStart;

    @ApiModelProperty(value = "解决结束")
    private String solveEnd;


    private List<EventInfoQueryVo> bhType;


    @ApiModelProperty(value = "工作量")
    private List<EventDisposalWorkloadQueryVo> workLoad;


}
