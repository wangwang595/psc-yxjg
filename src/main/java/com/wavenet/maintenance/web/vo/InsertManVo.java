package com.wavenet.maintenance.web.vo;


import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.dao.entity.MaintenanceDisposal;
import com.wavenet.maintenance.dao.entity.MaintenancePlan;
import lombok.Data;

import java.util.List;

@Data
public class InsertManVo {


    private MaintenancePlan maintenancePlan;

    private List<MaintenanceDetail> maintenanceDisposal;
}
