package com.wavenet.maintenance.web.vo;

import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;

@Data
public class DetalListUpdateVo {

    @ApiModelProperty(value = "计划编码")
    private String planCode;

    private List<MaintenanceDetail> maintenanceDetails;
}
