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

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
@Data
public class EventDisposalWorkloadList  {

    private List<EventDisposalWorkload> eventDisposalWorkload;

    @ApiModelProperty(value = "名字")
    private  String name;
    @ApiModelProperty(value = "app审核人")
    private  String appReview;
    @ApiModelProperty(value = "web审核人")
    private  String webReview;

}
