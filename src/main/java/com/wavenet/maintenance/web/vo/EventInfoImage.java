package com.wavenet.maintenance.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/11/25 15:00
 * @Version 1.0
 */
@Data
public class EventInfoImage implements Serializable {

    private String eventCode;

    private String attachment;

    // 一个状态代表是删除还是更新 (新增是 1 删除是0)

    private  Integer status;

    //新增 或者 删除的图片
    private  String updateImages;

    // 做更新的人
    private  String person;
}
