/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: PlanServiceImpl.java
 * @Package com.wavenet.maintenance.service.impl
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-17 9:58
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.service.impl;

import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.dao.entity.inPlans;
import com.wavenet.maintenance.dao.mapper.PlanMapper;
import com.wavenet.maintenance.service.PlanService;
import com.wavenet.maintenance.web.vo.PlanTableVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PlanServiceImpl
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-17 9:58
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Service
public class PlanServiceImpl implements PlanService {
    @Resource
    private PlanMapper mapper;

    @Override
    public List<PlanTableVo> selectPlan(String year,String month,String team) throws Exception {
        //查询项目组
        List<PlanTableVo> planTable = mapper.selectPlanLength(year, month,team);
        for (int i=0;i<planTable.size();i++) {
            String projectTeam = planTable.get(i).getProjectTeam();
            //计划内
            List<inPlans> inPlans = mapper.selectByTeam(projectTeam, year, month, "计划内");
            planTable.get(i).setInPlans(inPlans);
            //计划外
            List<inPlans> outPlans = mapper.selectByTeam(projectTeam, year, month, "计划外");
            planTable.get(i).setOutPlans(outPlans);
        }

        return planTable;
    }

    @Override
    public List<MaintenanceDetail> selectRoadName(String year, String month, String team) throws Exception {
        List<MaintenanceDetail> list = mapper.selectRoadName(year, month, team);
        return list;
    }
}
