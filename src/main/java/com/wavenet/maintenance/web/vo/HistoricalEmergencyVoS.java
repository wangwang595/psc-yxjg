package com.wavenet.maintenance.web.vo;


import com.wavenet.maintenance.dao.entity.EventInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "HistoricalEmergencyVoS对象", description = "查询参数")
public class HistoricalEmergencyVoS {


    private List<HistoricalEmergencyVo> historicalEmergencyVos;

    private  List<EventInfo> xyVoList;
}


