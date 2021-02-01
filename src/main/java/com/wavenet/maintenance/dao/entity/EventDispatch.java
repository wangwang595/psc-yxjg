package com.wavenet.maintenance.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("EVENT_DISPATCH")
@ApiModel(value = "EventDispatch对象", description = "")
public class EventDispatch extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键")
@TableId(value = "dispatch_code", type = IdType.UUID)
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

@ApiModelProperty(value = "派遣状态")
    @TableField("DISPATCH_STATE")
private String dispatchState;

@ApiModelProperty(value = "项目组")
private String projectTeam;

@ApiModelProperty(value = "派遣时间")
private Date accpetDate;

@ApiModelProperty(value = "事件类型")
private String eventType;

@ApiModelProperty(value = "病害数量")
private Integer eventNum;

@ApiModelProperty(value = "结束时间")
private String endTime;

@ApiModelProperty(value = "来源(网格单，自派遣，巡查发现)")
private String source;

private Date dealline;

}
