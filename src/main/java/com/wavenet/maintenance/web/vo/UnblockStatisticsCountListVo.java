package com.wavenet.maintenance.web.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/12/10 14:03
 * @Version 1.0
 */
@Data
@ToString
public class UnblockStatisticsCountListVo {
    private List<UnblockStatisticsVo> unblockStatisticsVos;
    private  UnblockStatisticsCountVo unblockStatisticsCountVo;
}
