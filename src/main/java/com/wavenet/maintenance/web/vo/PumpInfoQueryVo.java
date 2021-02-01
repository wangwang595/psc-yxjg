package com.wavenet.maintenance.web.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName RainPump
 * @Description TODO
 * @Author Alex
 * @Date 2019/8/26
 * @Version 1.0
 **/
@Data
@ToString(callSuper = true)
@ApiModel(description = "泵站弹窗基础数据查询")
public class PumpInfoQueryVo {
    private String bzmc;
    private String bzdl;
    private String gldj;
    private Integer bjzs;
    private String prst;
    private Double yssjll;
    private Double wssjll;
    private String gldw;
    private String time;
    private Double sw;
    private Integer sumopen;
    private Integer yszs;
    private Integer yskjgs;
    private Double ysmp;
    private Double ysrlj;
    private Integer wszs;
    private Integer wskjgs;
    private Double wsmp;
    private Double wsrlj;
    private Double jsjdmbg;
    private Double gdbg;
    private Double jsggj;
}
