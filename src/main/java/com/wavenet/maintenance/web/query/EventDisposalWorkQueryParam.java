package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.dao.entity.EventDisposal;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 *  查询参数对象
 * </pre>
 *
 * @author zll
 * @date 2020-04-22
 */
@Data
public class EventDisposalWorkQueryParam {
    private EventDisposal eventDisposal;
    private List<EventDisposalWorkload> workload;
}
