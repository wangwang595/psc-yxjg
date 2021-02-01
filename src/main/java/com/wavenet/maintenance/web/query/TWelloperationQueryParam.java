package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.common.FindArticleDto;
import io.swagger.annotations.ApiModel;
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
 * @date 2020-08-12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TWelloperationQueryParam对象", description = "查询参数")
public class TWelloperationQueryParam extends OrderQueryParam {
    private static final long serialVersionUID = 1L;

    private String sDate;
    private String eDate;
    private String state;
    private String manId;
    private String wellClass;
    private String recodeID;
    private String nId;
    private String yhwork;
    private String town;



}


