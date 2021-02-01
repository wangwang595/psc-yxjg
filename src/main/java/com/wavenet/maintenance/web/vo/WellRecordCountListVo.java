package com.wavenet.maintenance.web.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class WellRecordCountListVo {

    private List<WellRecordVo> wellRecordVoList;

    private  WellRecordVoCount wellRecordVoCount;


}