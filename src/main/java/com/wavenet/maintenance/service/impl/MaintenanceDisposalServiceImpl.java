package com.wavenet.maintenance.service.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.dao.entity.EventInfo;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.web.query.MaintenanceDisposalQueryParams;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.api.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.MaintenanceDisposal;
import com.wavenet.maintenance.dao.mapper.MaintenanceDetailMapper;
import com.wavenet.maintenance.dao.mapper.MaintenanceDisposalMapper;
import com.wavenet.maintenance.service.MaintenanceDisposalService;
import com.wavenet.maintenance.web.query.MaintenanceDisposalQueryParam;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;

import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.Result;


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
public class MaintenanceDisposalServiceImpl extends BaseServiceImpl<MaintenanceDisposalMapper, MaintenanceDisposal> implements MaintenanceDisposalService {

    @Autowired
    private MaintenanceDisposalMapper maintenanceDisposalMapper;
    @Autowired
    private MaintenanceDetailMapper maintenanceDetailMapper;
    @Autowired
    private  PushServiceImpl pushService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveMaintenanceDisposal(MaintenanceDisposal maintenanceDisposal ) throws Exception {
        if (super.save(maintenanceDisposal)) {
            return maintenanceDisposal.getDisposalCode();
        }else {
            return null;
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMaintenanceDisposal(MaintenanceDisposal maintenanceDisposal) throws Exception {
        if (maintenanceDisposal.getCuringState()!=null) {
            MaintenanceDetail maintenanceDetail = new MaintenanceDetail();
            BeanUtils.copyProperties(maintenanceDisposal,maintenanceDetail);
            maintenanceDetail.setCuringState(maintenanceDisposal.getCuringState());
            maintenanceDetail.setDetailCode(maintenanceDisposal.getDetailCode());
            maintenanceDetailMapper.updateById(maintenanceDetail);
        }
        return super.updateById(maintenanceDisposal);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteMaintenanceDisposal(String id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public MaintenanceDisposalQueryVo getMaintenanceDisposalById(Serializable id) throws Exception {
        return maintenanceDisposalMapper.getMaintenanceDisposalById(id);
    }

    @Override
    public Paging<MaintenanceDisposalQueryVo> getMaintenanceDisposalPageList(MaintenanceDisposalQueryParams maintenanceDisposalQueryParam) throws Exception {
        Page page = setPageParam(maintenanceDisposalQueryParam,OrderItem.desc("date_start"));
        // month 或者 year 为空
        Date startDate=null;
        Date  endDate=null;
        String year = maintenanceDisposalQueryParam.getYear();
        String month = maintenanceDisposalQueryParam.getMonth();
        // 当年不为空 月不为空
        if (!(year==null||year.equals(""))) {
            if (!(month == null || month.equals(""))) {
                Integer monthIn = Integer.valueOf(month);
                String month1 = "";
                if (monthIn <= 9) {
                    month1 = "0" + month;
                } else {
                    month1 = Integer.toString(monthIn);
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                String date = year + "-" + month1;
                Date dd = format.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dd);
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                String day = cc.toString();
                String startTime = year + "-" + month1 + "-01 00:00:00";
                // 日期问题
                String endTime = year + "-" + month1 + "-" + day + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }
        // 当年不为空 月为空
        if (!(year==null||year.equals(""))) {
            if ((month == null || month.equals(""))) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                String date = year;
                Date dd = format.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dd);
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                int actualMaximum = cal.get(Calendar.MONTH) + 1;
                String day = cc.toString();
                String startTime = year + "-" + 01 + "-01 00:00:00";
                // 日期问题
                String endTime = year + "-" + 12 + "-" + 30 + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }
        // 当年为空 月不为空
        if (year==null||year.equals("")){
            if (!(month==null||month.equals(""))){
                Integer monthIn = Integer.valueOf(month);
                String month1="";
                if (monthIn<=9){
                    month1="0"+month;
                }else {
                    month1=Integer.toString(monthIn);
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                Integer year1 = cal.get(Calendar.YEAR);
                String day = cc.toString();
                String startTime = year1 + "-" + month1 + "-01 00:00:00";
                // 日期问题
                String  endTime = year1 + "-" + month1 + "-" + day + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }

        IPage<MaintenanceDisposalQueryVo> iPage = maintenanceDisposalMapper.getMaintenanceDisposalPageList(page, maintenanceDisposalQueryParam,startDate,endDate);
        return  new Paging(iPage);
    }
    @Override
    public Paging<MaintenanceDisposalQueryVo> getMaintenanceDisposalPageLists(MaintenanceDisposalQueryParam maintenanceDisposalQueryParam) {
        Page page = setPageParam(maintenanceDisposalQueryParam,OrderItem.desc("date_start"));
        IPage<MaintenanceDisposalQueryVo> iPage=null;
        if (maintenanceDisposalQueryParam.getTown()!=null) {
            if (!(maintenanceDisposalQueryParam.getTown().equals(""))) {
                String town = maintenanceDisposalQueryParam.getTown();
                String replace = "'" + town + "'";
                if (town.contains(",")) {
                    replace = town.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                maintenanceDisposalQueryParam.setTown(replace);
            }
        }
        if (maintenanceDisposalQueryParam.getProjectTeam()!=null) {
            if (!(maintenanceDisposalQueryParam.getProjectTeam().equals(""))) {
                String projectTeam = maintenanceDisposalQueryParam.getProjectTeam();
                String replace = "'" + projectTeam + "'";
                if (projectTeam.contains(",")) {
                    replace = projectTeam.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                maintenanceDisposalQueryParam.setTown(replace);
            }
        }
            iPage = maintenanceDisposalMapper.getMaintenanceDisposalPageLists(page, maintenanceDisposalQueryParam);
        return  new Paging(iPage);

    }
    @Override
    public Paging<MaintenanceDisposalQueryVo> getPageListNew(MaintenanceDisposalQueryParam maintenanceDisposalQueryParam) {
        Page page = setPageParam(maintenanceDisposalQueryParam,OrderItem.desc("date_start"));
        IPage<MaintenanceDisposalQueryVo> iPage=null;
        iPage = maintenanceDisposalMapper.getPageListNew(page, maintenanceDisposalQueryParam);
        return  new Paging(iPage);
    }

    @Override
    public PageInfo<MaintenanceDisposalCheckSpitVo> inquireReview(PgeVoParmterVo parmterVo) {
        PageHelper.startPage(parmterVo.getFindArticleDto().getPage(), parmterVo.getFindArticleDto().getPageSize(),"");
        List<MaintenanceDisposalCheckVo> patrolInfos1= maintenanceDisposalMapper.inquireReview(parmterVo);
       List<MaintenanceDisposalCheckSpitVo> maintenanceDisposalCheckSpitVos = new ArrayList<>();
        for (MaintenanceDisposalCheckVo maintenanceDisposalCheckVo : patrolInfos1) {
            MaintenanceDisposalCheckSpitVo maintenanceDisposalCheckSpitVo = new MaintenanceDisposalCheckSpitVo();
            String attBefore = maintenanceDisposalCheckVo.getAttBefore();
            String[] split = attBefore.split(",");
            BeanUtils.copyProperties(maintenanceDisposalCheckVo,maintenanceDisposalCheckSpitVo);
            maintenanceDisposalCheckSpitVo.setAttBefore(split);
            maintenanceDisposalCheckSpitVos.add(maintenanceDisposalCheckSpitVo);
        }
        PageInfo pageInfo = new PageInfo(patrolInfos1);

        pageInfo.setList(maintenanceDisposalCheckSpitVos);

        return pageInfo;
    }

    @Override
    public MaintenanceDisposalQueryVo getMaintenanceDisposalByCode(String id) {
        MaintenanceDisposalQueryVo  maintenanceDisposalByCode= maintenanceDisposalMapper.getMaintenanceDisposalByCode(id);

        return maintenanceDisposalByCode;
    }

    @Override
    public boolean updateDisposal(MaintenanceDisposal maintenanceDisposal) {
        if (maintenanceDisposal.getDetailCode()==null||"".equals(maintenanceDisposal.getDetailCode())){
            return  false;
        }
        MaintenanceDetail maintenanceDetail = new MaintenanceDetail();
        maintenanceDetail.setDetailCode(maintenanceDisposal.getDetailCode());
        maintenanceDetail.setCuringState(maintenanceDisposal.getCuringState());
        // 修改 maintenanceDetail 状态
        maintenanceDetailMapper.updateById(maintenanceDetail);
        Integer integer=0;
        // 修改或者新增 MaintenanceDisposal 根据 maintenanceDisposal.getDetailCode() 判断是否存在 更新 不存在 插入
       Integer count= maintenanceDisposalMapper.getInfoByDetailCode(maintenanceDisposal.getDetailCode());
       if (count>0){
           // 有数据
           String detailCode = maintenanceDisposal.getDetailCode();
           String town= maintenanceDisposalMapper.getTown(detailCode);
           maintenanceDisposal.setTown(town);
           integer = maintenanceDisposalMapper.updateById(maintenanceDisposal);
       }else {
           // 没数据
           maintenanceDisposal.setDisposalCode(UUID.randomUUID().toString().replace("_",""));
           String detailCode = maintenanceDisposal.getDetailCode();
           // 获取片区
          String town= maintenanceDisposalMapper.getTown(detailCode);
           maintenanceDisposal.setTown(town);
           integer = maintenanceDisposalMapper.insert(maintenanceDisposal);

       }
       Boolean flag=false;
       if (integer==0){
           flag=false;
       }else {
           flag=true;
       }


        return flag;
    }

    @Override
    public MaintenanceDisposalCountVo getLengthCounts(MaintenanceDisposalQueryParams maintenanceDisposalQueryParam) throws ParseException {
        Date startDate=null;
        Date  endDate=null;
        String year = maintenanceDisposalQueryParam.getYear();
        String month = maintenanceDisposalQueryParam.getMonth();
        // 当年不为空 月不为空
        if (!(year==null||year.equals(""))) {
            if (!(month == null || month.equals(""))) {
                Integer monthIn = Integer.valueOf(month);
                String month1 = "";
                if (monthIn <= 9) {
                    month1 = "0" + month;
                } else {
                    month1 = Integer.toString(monthIn);
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                String date = year + "-" + month1;
                Date dd = format.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dd);
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                String day = cc.toString();
                String startTime = year + "-" + month1 + "-01 00:00:00";
                // 日期问题
                String endTime = year + "-" + month1 + "-" + day + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }
        // 当年不为空 月为空
        if (!(year==null||year.equals(""))) {
            if ((month == null || month.equals(""))) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                String date = year;
                Date dd = format.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dd);
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                int actualMaximum = cal.get(Calendar.MONTH) + 1;
                String day = cc.toString();
                String startTime = year + "-" + 01 + "-01 00:00:00";
                // 日期问题
                String endTime = year + "-" + 12 + "-" + 30 + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }
        // 当年为空 月不为空
        if (year==null||year.equals("")){
            if (!(month==null||month.equals(""))){
                Integer monthIn = Integer.valueOf(month);
                String month1="";
                if (monthIn<=9){
                    month1="0"+month;
                }else {
                    month1=Integer.toString(monthIn);
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                Integer year1 = cal.get(Calendar.YEAR);
                String day = cc.toString();
                String startTime = year1 + "-" + month1 + "-01 00:00:00";
                // 日期问题
                String  endTime = year1 + "-" + month1 + "-" + day + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }
        MaintenanceDisposalCountVo count = maintenanceDisposalMapper.getLengthCounts(maintenanceDisposalQueryParam,startDate,endDate);
        return count;
    }



    @Override
    public MaintenanceDisposalCountVo getLengthCount(MaintenanceDisposalQueryParams maintenanceDisposalQueryParam) throws Exception {
        Date startDate=null;
        Date  endDate=null;
        String year = maintenanceDisposalQueryParam.getYear();
        String month = maintenanceDisposalQueryParam.getMonth();
        // 当年不为空 月不为空
        if (!(year==null||year.equals(""))) {
            if (!(month == null || month.equals(""))) {
                Integer monthIn = Integer.valueOf(month);
                String month1 = "";
                if (monthIn <= 9) {
                    month1 = "0" + month;
                } else {
                    month1 = Integer.toString(monthIn);
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                String date = year + "-" + month1;
                Date dd = format.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dd);
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                String day = cc.toString();
                String startTime = year + "-" + month1 + "-01 00:00:00";
                // 日期问题
                String endTime = year + "-" + month1 + "-" + day + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }
        // 当年不为空 月为空
        if (!(year==null||year.equals(""))) {
            if ((month == null || month.equals(""))) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                String date = year;
                Date dd = format.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dd);
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                int actualMaximum = cal.get(Calendar.MONTH) + 1;
                String day = cc.toString();
                String startTime = year + "-" + 01 + "-01 00:00:00";
                // 日期问题
                String endTime = year + "-" + 12 + "-" + 30 + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }
        // 当年为空 月不为空
        if (year==null||year.equals("")){
            if (!(month==null||month.equals(""))){
                Integer monthIn = Integer.valueOf(month);
                String month1="";
                if (monthIn<=9){
                    month1="0"+month;
                }else {
                    month1=Integer.toString(monthIn);
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                Integer year1 = cal.get(Calendar.YEAR);
                String day = cc.toString();
                String startTime = year1 + "-" + month1 + "-01 00:00:00";
                // 日期问题
                String  endTime = year1 + "-" + month1 + "-" + day + " 23:59:59";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDate = simpleDateFormat.parse(startTime);
                endDate = simpleDateFormat.parse(endTime);
            }
        }
        if (maintenanceDisposalQueryParam.getTown()!=null) {
            if (!(maintenanceDisposalQueryParam.getTown().equals(""))) {
                String town = maintenanceDisposalQueryParam.getTown();
                String replace = "'" + town + "'";
                if (town.contains(",")) {
                    replace = town.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                maintenanceDisposalQueryParam.setTown(replace);
            }
        }
        if (maintenanceDisposalQueryParam.getProjectTeam()!=null) {
            if (!(maintenanceDisposalQueryParam.getProjectTeam().equals(""))) {
                String projectTeam = maintenanceDisposalQueryParam.getProjectTeam();
                String replace = "'" + projectTeam + "'";
                if (projectTeam.contains(",")) {
                    replace = projectTeam.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                maintenanceDisposalQueryParam.setTown(replace);
            }
        }
        MaintenanceDisposalCountVo count = maintenanceDisposalMapper.getLengthCount(maintenanceDisposalQueryParam,startDate,endDate);
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult<Integer> updateLengthByDisposalCode(MaintenanceDisposal maintenanceDisposal) {
        int i = maintenanceDisposalMapper.updateById(maintenanceDisposal);
        return ApiResult.ok(i);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean statusBack(MaintenanceDisposal maintenanceDisposal) throws Exception {
        //点击取消状态修改为 执行中
        maintenanceDisposal.setDeleteState("0");
        maintenanceDisposal.setWebReview(maintenanceDisposal.getWebReview());
        boolean b = super.updateById(maintenanceDisposal);
        //修改detailCode修改 信息表
        MaintenanceDetail maintenanceDetail = new MaintenanceDetail();
        maintenanceDetail.setDetailCode(maintenanceDisposal.getDetailCode());
        maintenanceDetail.setCuringState("未执行");

        maintenanceDetailMapper.updateById(maintenanceDetail);
        pushService.pushCustomMessageS(maintenanceDisposal.getTown());
        //
        return b;
    }




}
