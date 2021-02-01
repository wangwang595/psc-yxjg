/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: PlanController.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-17 9:58
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.dao.entity.PatrolGpsHistory;
import com.wavenet.maintenance.service.PlanService;
import com.wavenet.maintenance.web.vo.PlanTableVo;
import com.wavenetframework.boot.common.api.ApiResult;
import com.wavenetframework.boot.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

/**
 * @ClassName: PlanController
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-17 9:58     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@RestController
@RequestMapping(value = "/plan")
@Api(tags = "计划疏通列表")
@CrossOrigin
public class PlanController extends BaseController {
    @Resource
    private PlanService service;


    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 查询计划疏通
     */
    @ApiOperation(value = "查询疏通计划")
    @GetMapping(value = "/plans")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",value = "年"),
            @ApiImplicitParam(name = "month",value = "月"),
            @ApiImplicitParam(name = "team",value = "项目组")
    })
    public ApiResult<PlanTableVo> selectPlan(String year,String month,String team ) throws Exception {
        List<PlanTableVo> list = service.selectPlan(year, month,team);
        return ApiResult.ok(list);
    }

    /**
     * 查询路段名称
     */
    @ApiOperation(value = "查询路段名称")
    @GetMapping(value = "/roadName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",value = "年"),
            @ApiImplicitParam(name = "month",value = "月"),
            @ApiImplicitParam(name = "team",value = "项目组")
    })
    public ApiResult<MaintenanceDetail> selectRoadName(String year, String month, String team ) throws Exception {
        List<MaintenanceDetail> list = service.selectRoadName(year, month,team);
        return ApiResult.ok(list);
    }

    @ApiOperation(value = "mongodb测试")
    @GetMapping(value = "/jsonObject")
    public void jsonObject() {

        PatrolGpsHistory patrolGpsHistory = new PatrolGpsHistory();
        patrolGpsHistory.setPatrolCode("ffe52da479d0-4f7d9641-2da13fb4d945");
        patrolGpsHistory.setUplaodDate("2020-11-04 13:20:03");
        patrolGpsHistory.setX("31.2333");
        patrolGpsHistory.setY("121.144");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sss","ssss");

        List<JSONObject> jsonObject1 = null;
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(Criteria.where("patrolCode").is("ffe52da479d0-4f7d9641-2da13fb4d945"));
        query.addCriteria(criteria);
        jsonObject1 = mongoTemplate.find(query, JSONObject.class, "patrolGpsHistory");
        System.out.println(jsonObject1);
        if(jsonObject1.size()==0){
            mongoTemplate.insert(patrolGpsHistory);
        }else {
            JSONObject jsonObject3 = jsonObject1.get(0);
            Gson gson = new Gson();
            PatrolGpsHistory patrolGpsHistory1 = gson.fromJson(jsonObject3.toString(), patrolGpsHistory.getClass());
            patrolGpsHistory1.setPatrolCode(patrolGpsHistory.getPatrolCode());
            patrolGpsHistory1.setX(patrolGpsHistory1.getX()+","+patrolGpsHistory.getX());
            patrolGpsHistory1.setY(patrolGpsHistory1.getY()+","+patrolGpsHistory.getY());
            mongoTemplate.remove(query, JSONObject.class,"patrolGpsHistory");
            mongoTemplate.insert(patrolGpsHistory1);
            System.out.println(patrolGpsHistory1);

        }
    }
}
