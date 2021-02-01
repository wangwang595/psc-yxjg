/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: DisposalAndWorkloadParam.java
 * @Package com.wavenet.maintenance.web.query
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-08-10 17:50
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.query;

import com.wavenet.maintenance.dao.entity.EventDisposal;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: DisposalAndWorkloadParam
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-08-10 17:50     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class DisposalAndWorkloadParam {
    private List<EventDisposalWorkload> eventDisposalWorkloads;
    private EventDisposal eventDisposal;
}
