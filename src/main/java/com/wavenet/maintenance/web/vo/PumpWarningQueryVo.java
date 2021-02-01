package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName PumpWarningQueryVo
 * @Description TODO
 * @Author Alex
 * @Date 2020/7/18
 * @Version 1.0
 **/
@Data
@ToString(callSuper = true)
@ApiModel(description = "泵站列表")
public class PumpWarningQueryVo {

    private String pumpName;

    private String xtbm;

    private Double sw;

    private Integer wsOpen;

    private Integer wsSum;

    private Integer ysOpen;

    private Integer ysSum;

    private String gdsw;

    private Integer state;

}
