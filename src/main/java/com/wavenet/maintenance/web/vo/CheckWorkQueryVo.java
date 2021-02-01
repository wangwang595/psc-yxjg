package com.wavenet.maintenance.web.vo;

import com.wavenet.maintenance.dao.entity.EventDisposal;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.dao.entity.EventInfo;
import lombok.Data;

import java.util.List;

@Data
public class CheckWorkQueryVo {
    private List<EventInfo> eventInfo;
    private List<EventDisposalWorkload> workload;
    private EventDisposal disposals;
}