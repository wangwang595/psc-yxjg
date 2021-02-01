package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *  查询结果对象
 * </pre>
 *
 * @author zll
 * @date 2020-06-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "EmergencyUserStateQueryVo对象", description = "查询参数")
public class EmergencyUserStateQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "人员名称")
    private String userName;

    @ApiModelProperty(value = "人员ID")
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

    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "状态")
    private Integer status;

}

