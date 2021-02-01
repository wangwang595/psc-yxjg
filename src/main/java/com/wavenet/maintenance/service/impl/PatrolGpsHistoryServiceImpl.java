package com.wavenet.maintenance.service.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.wavenet.maintenance.convert.jdbcUtil;
import com.wavenet.maintenance.dao.entity.EmergencyUserState;
import com.wavenet.maintenance.dao.entity.Trajectory;
import com.wavenet.maintenance.dao.mapper.EmergencyUserStateMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.PatrolGpsHistory;
import com.wavenet.maintenance.dao.entity.PatrolGpsRealtime;
import com.wavenet.maintenance.dao.mapper.PatrolGpsHistoryMapper;
import com.wavenet.maintenance.service.PatrolGpsHistoryService;
import com.wavenet.maintenance.service.PatrolGpsRealtimeService;
import com.wavenet.maintenance.web.query.PatrolGpsHistoryQueryParam;
import com.wavenet.maintenance.web.vo.PatrolGpsHistoryQueryVo;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import com.wavenetframework.boot.util.BeanUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-03-13
 */
@Slf4j
@Service
public class PatrolGpsHistoryServiceImpl extends BaseServiceImpl<PatrolGpsHistoryMapper, PatrolGpsHistory> implements PatrolGpsHistoryService {

    @Autowired
    private PatrolGpsHistoryMapper patrolGpsHistoryMapper;
    @Autowired
    private PatrolGpsRealtimeService patrolGpsRealtimeService;
    @Autowired
    private EmergencyUserStateMapper emergencyUserStateMapper;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean savePatrolGpsHistory(PatrolGpsHistory patrolGpsHistory) throws Exception {
        PatrolGpsRealtime realtime = new PatrolGpsRealtime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date format = df.parse(patrolGpsHistory.getUplaodDate());
       // patrolGpsHistory.setUplaodDate(format);
        //根据用户code 查询用户状态
        EmergencyUserState emergencyUserState = emergencyUserStateMapper.selectById(patrolGpsHistory.getPersonCode());
        if (emergencyUserState == null) {
            realtime.setState("1");
            patrolGpsHistory.setState("1");
        } else {
            String userState = emergencyUserState.getUserState();
            realtime.setState(userState);
            patrolGpsHistory.setState(userState);
        }
        //super.save(patrolGpsHistory);
        List<JSONObject> jsonObject1 = null;
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(Criteria.where("patrolCode").is(patrolGpsHistory.getPatrolCode()));
        query.addCriteria(criteria);
        jsonObject1 = mongoTemplate.find(query, JSONObject.class, "PatrolGpsHistory");
        System.out.println(jsonObject1);
        if(jsonObject1.size()==0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("patrolCode",patrolGpsHistory.getPatrolCode());
            Gson gson = new Gson();
            String json = gson.toJson(patrolGpsHistory);
            List<String> strings = new ArrayList<>();
            strings.add(json);
            jsonObject.put("data",strings);
            mongoTemplate.insert(jsonObject,"PatrolGpsHistory");
        }else {
            JSONObject jsonObject3 = jsonObject1.get(0);
            List<String> date =(List<String>)jsonObject3.get("data");
            Gson gson = new Gson();
            mongoTemplate.remove(query, JSONObject.class,"PatrolGpsHistory");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("patrolCode",patrolGpsHistory.getPatrolCode());
            String json = gson.toJson(patrolGpsHistory);
            date.add(json);
            jsonObject.put("data",date);
            mongoTemplate.insert(jsonObject,"PatrolGpsHistory");

        }
        BeanUtils.copyProperties(patrolGpsHistory, realtime);
        realtime.setUplaodDate(format);
        patrolGpsRealtimeService.saveOrUpdate(realtime);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePatrolGpsHistory(PatrolGpsHistory patrolGpsHistory) throws Exception {
        return super.updateById(patrolGpsHistory);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deletePatrolGpsHistory(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public PatrolGpsHistoryQueryVo getPatrolGpsHistoryById(Serializable id) throws Exception {
        return patrolGpsHistoryMapper.getPatrolGpsHistoryById(id);
    }

    @Override
    public Paging<PatrolGpsHistoryQueryVo> getPatrolGpsHistoryPageList(PatrolGpsHistoryQueryParam patrolGpsHistoryQueryParam) throws Exception {
        Page page = setPageParam(patrolGpsHistoryQueryParam, OrderItem.asc("uplaod_date"));
        IPage<PatrolGpsHistoryQueryVo> iPage = patrolGpsHistoryMapper.getPatrolGpsHistoryPageList(page, patrolGpsHistoryQueryParam);
        return new Paging(iPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean savePatrolGpsHistoryAndUpdatePatrolRelam(PatrolGpsHistory patrolGpsHistory) throws Exception {
        savePatrolGpsHistory(patrolGpsHistory);
        PatrolGpsRealtime patrolGpsRealtime = new PatrolGpsRealtime();
        BeanUtil.copyProperties(patrolGpsRealtime, patrolGpsHistory);
        boolean flag = patrolGpsRealtimeService.saveOrUpdate(patrolGpsRealtime);
        return flag;
    }

    @Override
    public boolean createLine(Trajectory trajectory) {
        String patrolCode = trajectory.getPatrolCode();
        String uuid = UUID.randomUUID().toString();
        trajectory.setGpsCode(uuid);
        //查询历史轨迹表
        String substring = patrolGpsRealtimeService.selectShape(patrolCode);
        trajectory.setShape(substring);

        //使用原生JDBC
        jdbcUtil util = new jdbcUtil();
        int row = 0;
        try {
            row = util.jdbcIns(trajectory);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<PatrolGpsHistory> getPatrolGpsHistoryPageList1(PatrolGpsHistory patrolGpsHistoryQueryParam) {
                List<JSONObject> jsonObject1 = null;
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(Criteria.where("patrolCode").is(patrolGpsHistoryQueryParam.getPatrolCode()));
        query.addCriteria(criteria);
        jsonObject1 = mongoTemplate.find(query, JSONObject.class, "PatrolGpsHistory");
        PatrolGpsHistory patrolGpsHistory = new PatrolGpsHistory();
        Gson gson = new Gson();
        List<String> data = (List<String>)jsonObject1.get(0).get("data");
        //PatrolGpsHistory patrolGpsHistory1 = gson.fromJson(jsonObject1.get(0).toString(), patrolGpsHistory.getClass());
        List<PatrolGpsHistory> patrolGpsHistories = new ArrayList<>();
        for (String datum : data) {
            PatrolGpsHistory patrolGpsHistory1 = gson.fromJson(datum, patrolGpsHistory.getClass());
            patrolGpsHistories.add(patrolGpsHistory1);
        }
        return patrolGpsHistories;
    }


}

