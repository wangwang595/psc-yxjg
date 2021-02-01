package com.wavenet.maintenance.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wavenet.maintenance.dao.entity.MaintenanceDisposal;
import com.wavenet.maintenance.dao.entity.PatrolInfo;
import com.wavenet.maintenance.dao.mapper.*;
import com.wavenet.maintenance.service.PatrolInfoService;
import com.wavenet.maintenance.web.vo.EveryReportErrorVo;
import com.wavenet.maintenance.web.vo.MaintenanceDisposalQueryVo;
import com.wavenet.maintenance.web.vo.PatrolGpsHistoryQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.PatrolGpsRealtime;
import com.wavenet.maintenance.service.PatrolGpsRealtimeService;
import com.wavenet.maintenance.web.query.PatrolGpsRealtimeQueryParam;
import com.wavenet.maintenance.web.vo.PatrolGpsRealtimeQueryVo;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;

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
public class PatrolGpsRealtimeServiceImpl extends BaseServiceImpl<PatrolGpsRealtimeMapper, PatrolGpsRealtime> implements PatrolGpsRealtimeService {

    @Autowired
    private PatrolGpsRealtimeMapper patrolGpsRealtimeMapper;
    @Autowired
    private MaintenanceDisposalMapper maintenanceDisposalMapper;
    @Autowired
    private EventDisposalMapper eventDisposalMapper;
    @Autowired
    private PatrolInfoMapper patrolInfoMapper;
    @Lazy
    @Autowired
    private PatrolInfoService patrolInfoService;
    @Autowired
    private PatrolGpsHistoryMapper patrolGpsHistoryMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean savePatrolGpsRealtime(PatrolGpsRealtime patrolGpsRealtime) throws Exception {
        return super.save(patrolGpsRealtime);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePatrolGpsRealtime(PatrolGpsRealtime patrolGpsRealtime) throws Exception {
        return super.updateById(patrolGpsRealtime);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deletePatrolGpsRealtime(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public PatrolGpsRealtimeQueryVo getPatrolGpsRealtimeById(Serializable id) throws Exception {
        return patrolGpsRealtimeMapper.getPatrolGpsRealtimeById(id);
    }

    @Override
    public Paging<PatrolGpsRealtimeQueryVo> getPatrolGpsRealtimePageList(PatrolGpsRealtimeQueryParam patrolGpsRealtimeQueryParam) throws Exception {
        Page page = setPageParam(patrolGpsRealtimeQueryParam, OrderItem.desc("create_time"));
        IPage<PatrolGpsRealtimeQueryVo> iPage = patrolGpsRealtimeMapper.getPatrolGpsRealtimePageList(page, patrolGpsRealtimeQueryParam);
        return new Paging(iPage);
    }

    @Override
    public List<PatrolGpsRealtimeQueryVo> selectAtWork() {
        List<PatrolGpsRealtimeQueryVo> list = patrolGpsRealtimeMapper.selectAtWork();
       List<PatrolGpsRealtimeQueryVo> patrolGpsRealtimeQueryVos = new ArrayList<>();
        for (PatrolGpsRealtimeQueryVo patrolGpsRealtimeQueryVo : list) {
            Date uplaodDate = patrolGpsRealtimeQueryVo.getUplaodDate();
            Date date = new Date();
            if (uplaodDate!=null){
                long time = uplaodDate.getTime();
                long time1 = date.getTime();
               long diff =time1 - time;
               int S=(int)diff;

                long aLong = 240001L;
                int S1=(int)aLong;
                if (S<S1) {
                    patrolGpsRealtimeQueryVos.add(patrolGpsRealtimeQueryVo);
                }
            }
        }
        return patrolGpsRealtimeQueryVos;
    }

    @Override
    public EveryReportErrorVo selectTypeByPersonCode(String personCode) throws Exception{
        EveryReportErrorVo everyReportErrorVo = new EveryReportErrorVo();
        PatrolGpsRealtimeQueryVo patrolGpsRealtimeQueryVo = patrolGpsRealtimeMapper.selectTypeByPersonCode(personCode);
        if (patrolGpsRealtimeQueryVo.getMileage()!=null){
            everyReportErrorVo.setMileage(patrolGpsRealtimeQueryVo.getMileage());
        }
        if (patrolGpsRealtimeQueryVo.getType().contains("养护")) {
            if (patrolGpsRealtimeQueryVo.getType().contains("下水道")) {
                //根据用户编号查询MaintenanceDisposal表
                 everyReportErrorVo = maintenanceDisposalMapper.selectNewByPersonCode(personCode);
            }else {
                //查询eventDisposal
                 everyReportErrorVo = eventDisposalMapper.selectNewByPersonCode(personCode);
            }
        }
        if (patrolGpsRealtimeQueryVo.getType().contains("巡视")) {
            //查询patrolInfo
             everyReportErrorVo = patrolInfoMapper.selectNewByPersonCode(personCode);
             //如果上次工作状态为执行中则表示程序中断
             if (everyReportErrorVo.getState().equals("执行中")){
                 //更改状态为已完成
                 PatrolInfo patrol = new PatrolInfo();
                 patrol.setPatrolCode(everyReportErrorVo.getPatrolCode());
                 if (patrolGpsRealtimeQueryVo.getMileage()!=null) {
                     patrol.setMileage(patrolGpsRealtimeQueryVo.getMileage());
                 }
                 patrol.setState("已完成");
                 patrol.setDateEnd(new Date());
                 patrolInfoService.updatePatrolInfo(patrol);
             }

        }
        return everyReportErrorVo;
    }

    @Override
    public String selectShape(String patrolCode) {
        List<PatrolGpsHistoryQueryVo> xyList = patrolGpsHistoryMapper.selectXy(patrolCode);
        if (xyList.size() < 3) {
            //第一个点对象
            PatrolGpsHistoryQueryVo patrolGpsHistoryQueryVo = new PatrolGpsHistoryQueryVo();
            double pointOne = xyList.get(0).getX() + 0.0000001;
            double pointTwo = pointOne + 0.0000001;
            //第一个点位在原记录的基础上+ 0.00000000001
            patrolGpsHistoryQueryVo.setX(pointOne);
            patrolGpsHistoryQueryVo.setY(xyList.get(0).getY());
            PatrolGpsHistoryQueryVo pointTwoVo = new PatrolGpsHistoryQueryVo();
            pointTwoVo.setX(pointTwo);
            pointTwoVo.setY(xyList.get(0).getY());
            xyList.add(patrolGpsHistoryQueryVo);
            xyList.add(pointTwoVo);
        }
        String shape = "";
        for (PatrolGpsHistoryQueryVo bean : xyList) {
            shape = shape + bean.toString();
        }
        String substring = null;
        if (shape.length() > 0) {
            substring = shape.substring(0, shape.length() - 1);
        }
        return substring;
    }

}
