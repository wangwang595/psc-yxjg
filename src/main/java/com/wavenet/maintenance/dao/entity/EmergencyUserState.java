package com.wavenet.maintenance.dao.entity;

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
@TableName("Emergency_user_state")
@ApiModel(value = "EmergencyUserState对象", description = "")
public class EmergencyUserState extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "人员名称")
private String userName;

@ApiModelProperty(value = "人员ID")
@TableId("user_id")
private String userId;

@ApiModelProperty(value = "人员状态")
private String userState;

@ApiModelProperty(value = "项目组")
private String projectTeam;

@ApiModelProperty(value = "片区")
private String town;

@ApiModelProperty(value = "接收状态")
private String receiveState;

@ApiModelProperty(value = "公司")
private String company;

@ApiModelProperty(value = "指令编号")
private String emergencyCode;

private Date startTime;

private Date endTime;

private Integer status;

private String phone;


}
