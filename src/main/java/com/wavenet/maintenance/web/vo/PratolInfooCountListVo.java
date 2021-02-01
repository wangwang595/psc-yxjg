package com.wavenet.maintenance.web.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PratolInfooCountListVo {

    private List<PratolInfoVo> pratolInfoVos;
    
    private  PratolInfoCountVo pratolInfoCountVo;


}

