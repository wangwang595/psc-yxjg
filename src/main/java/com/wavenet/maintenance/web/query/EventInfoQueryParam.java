package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.common.FindArticleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.wavenetframework.boot.common.param.OrderQueryParam;

/**
 * <pre>
 *  查询参数对象
 * </pre>
 *
 * @author zll
 * @date 2020-03-30
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "EventInfoQueryParam对象", description = "查询参数")
public class EventInfoQueryParam  extends OrderQueryParam {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("道路编号")
    private String roadCode;
    @ApiModelProperty("事件发生事件")
    private String time;
    @ApiModelProperty("类型 (桥梁 路面 护栏)")
    private String eventType;
    @ApiModelProperty("路面类型 (人行道)")
    private String type;
    @ApiModelProperty("类型中类")
    private String typeLarge;
    @ApiModelProperty("类型小类")
    private String typeSmall;
    @ApiModelProperty("用户编码")
    private String personCode;
    @ApiModelProperty("项目组")
    private String projectTeam;
    @ApiModelProperty("片区")
    private String town;
    @ApiModelProperty("处理状态")
    private String eventState;
    @ApiModelProperty("开始事件")
    private String beginTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    @ApiModelProperty("用户名")
    private String personName;
    @ApiModelProperty("来源")
    private String source;
    @ApiModelProperty("市政")
    private String eventStandard;
    @ApiModelProperty("公司")
    private String company;

    private String solverCode;

    private  String agent;


}
