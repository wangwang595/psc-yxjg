/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: PlanService.java
 * @Package com.wavenet.maintenance.service
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-17 9:58
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.web.vo.PlanTableVo;

import java.util.List;

/**
 * @ClassName: PlanService
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-17 9:58     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
public interface PlanService {
    List<PlanTableVo> selectPlan(String year,String month,String team) throws Exception;

    /**
     * 查询路段名称
     */
    List<MaintenanceDetail> selectRoadName(String year,String month,String team) throws Exception;
}
