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
@TableName("EVENT_DISPATCH_REL")
@ApiModel(value = "EventDispatchRel对象", description = "")
public class EventDispatchRel extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键")
@TableId(value = "event_dispatch_code", type = IdType.UUID)
    private String eventDispatchCode;

@ApiModelProperty(value = "关联事件编号")
private String eventCode;

@ApiModelProperty(value = "关联派遣编号")
private String dispatchCode;

}
