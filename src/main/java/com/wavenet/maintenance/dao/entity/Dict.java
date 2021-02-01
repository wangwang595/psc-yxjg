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
@TableName("DICT")
@ApiModel(value = "Dict对象", description = "")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键")
@TableId(value = "dict_code", type = IdType.UUID)
    private String dictCode;

@ApiModelProperty(value = "类型编号")
private String typeCode;

@ApiModelProperty(value = "类型")
private String type;

@ApiModelProperty(value = "名称")
private String name;

@ApiModelProperty(value = "备注")
private String note;

@ApiModelProperty(value = "排序")
private Double sort;

}
