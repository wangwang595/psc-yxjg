package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wavenetframework.boot.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author zll
 * @since 2020-12-10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("EVENT_INFO")
@ApiModel(value = "EventInfo对象", description = "")
public class EventInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "事件主键")
@TableId(value = "event_code", type = IdType.UUID)
    private String eventCode;

@ApiModelProperty(value = "道路编号")
private String patrolCode;

@ApiModelProperty(value = "病害类型编号")
private String eventTypeCode;

@ApiModelProperty(value = "病害类型")
private String eventType;

@ApiModelProperty(value = "病害大类")
private String eventTypeLarge;

@ApiModelProperty(value = "病害小类")
private String eventTypeSmall;

@ApiModelProperty(value = "事件描述")
private String eventDesc;

@ApiModelProperty(value = "地址")
private String address;

@ApiModelProperty(value = "X坐标")
private Double x;

@ApiModelProperty(value = "Y坐标")
private Double y;

@ApiModelProperty(value = "人员编号")
private String personCode;

@ApiModelProperty(value = "人员名称")
private String personName;

@ApiModelProperty(value = "公司")
private String company;

@ApiModelProperty(value = "片区")
private String town;

@ApiModelProperty(value = "上报时间")
private Date reportDate;

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

@ApiModelProperty(value = "附件")
private String attachment;

@ApiModelProperty(value = "事件状态")
private String eventState;

@ApiModelProperty(value = "逻辑删除，1显示")
private Double deleteState;

@ApiModelProperty(value = "道路养护，桥梁养护，护栏养护")
private String type;

@ApiModelProperty(value = "项目组")
private String projectTeam;

@ApiModelProperty(value = "网格单")
private String source;

@ApiModelProperty(value = "市政，非市政")
private String eventStandard;

@ApiModelProperty(value = "两小时工单 1 是 0否")
private Integer twoHours;

    @ApiModelProperty(value = "时间编号")
    private String timeZoneCode;

    @ApiModelProperty(value = "部件 设施 分类")
    private String partFacility;

    @ApiModelProperty(value = "大类")
    private String bigCategory;

    @ApiModelProperty(value = "中类")
    private String middleCategory;

    @ApiModelProperty(value = "小类")
    private String smallCategory;

}
