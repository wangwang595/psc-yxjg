package com.wavenet.maintenance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wavenet.maintenance.dao.entity.*;
import com.wavenet.maintenance.dao.mapper.MaintenanceDetailMapper;
import com.wavenet.maintenance.dao.mapper.MaintenanceDisposalMapper;
import com.wavenet.maintenance.dao.mapper.MaintenancePlanMapper;
import com.wavenet.maintenance.service.MaintenancePlanService;
import com.wavenet.maintenance.web.query.*;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-04-22
 */
@Slf4j
@Service
public class MaintenancePlanServiceImpl extends BaseServiceImpl<MaintenancePlanMapper, MaintenancePlan> implements MaintenancePlanService {

    @Autowired
    private MaintenancePlanMapper maintenancePlanMapper;

    @Autowired
    private MaintenanceDetailMapper maintenanceDetailMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveMaintenancePlan(MaintenancePlan maintenancePlan) throws Exception {
        return super.save(maintenancePlan);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMaintenancePlan(MaintenancePlan maintenancePlan) throws Exception {
        return super.updateById(maintenancePlan);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteMaintenancePlan(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public MaintenancePlanQueryVo getMaintenancePlanById(Serializable id) throws Exception {
        return maintenancePlanMapper.getMaintenancePlanById(id);
    }

    @Override
    public Paging<MaintenancePlanQueryVo> getMaintenancePlanPageList(MaintenancePlanQueryParam maintenancePlanQueryParam) throws Exception {
        Page page = setPageParam(maintenancePlanQueryParam, OrderItem.desc("create_time"));
        IPage<MaintenancePlanQueryVo> iPage = maintenancePlanMapper.getMaintenancePlanPageList(page, maintenancePlanQueryParam);
        return new Paging(iPage);
    }



    @Override
    public MannitVo sewerInformation(PatrolInfoObjectQuerParam queryParameter) throws ParseException {
        String date = queryParameter.getDate();
        List<Date> getdate = getdates(date);
        // 根据时间来进行三种比较 下水道 检查井 雨水口
        Date startDate = getdate.get(0);
        Date endDate = getdate.get(1);
        String project = queryParameter.getProject();
       // String replace = project.replace(",", "','");
        List<String > list=new ArrayList<>();
        if (project != null) {


        project = "["+project+"]";
        //json转换为list
        Gson gson = new Gson();
            list = gson.fromJson(project, new TypeToken<List<String>>(){}.getType());
            if (project.equals("[杨浦市政养护建设工程有限公司]")){
                list=new ArrayList<>();
            }
        }
        MannitVo mannitVo=maintenancePlanMapper.sewerInformation(startDate,endDate,list);


        return mannitVo;
    }

    @Override
    public Boolean dataInsertion(InsertManVo insertManVo) {
        List<MaintenanceDetail> maintenanceDisposal = insertManVo.getMaintenanceDisposal();
        MaintenancePlan maintenancePlan = insertManVo.getMaintenancePlan();
        String replace = UUID.randomUUID().toString().replace("-", "");
        maintenancePlan.setPlanCode(replace);
        String town = maintenancePlan.getTown();
        Double year = maintenancePlan.getYear();
        Double month = maintenancePlan.getMonth();
        String plans = maintenancePlan.getPlans();
        String type = maintenancePlan.getType();
        String projectTeam = maintenancePlan.getProjectTeam();
        int insert = maintenancePlanMapper.insert(maintenancePlan);
                if(!(maintenanceDisposal.size()==0)){
                    for (MaintenanceDetail disposal : maintenanceDisposal) {
                        disposal.setPlans(plans);
                        disposal.setPlanCode(replace);
                        disposal.setYear(year);
                        disposal.setMonth(month);
                        disposal.setTown(town);
                        disposal.setType(type);
                        disposal.setProjectTeam(projectTeam);
                        int insert1 = maintenanceDetailMapper.insert(disposal);
                    }
                }
                Boolean flag=false;
        if (insert==0){
            return  false;
        }else {
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean DetalListUpdate(DetalListUpdateVo maintenanceDetailQueryParam) {
        List<MaintenanceDetail> maintenanceDetails = maintenanceDetailQueryParam.getMaintenanceDetails();
        String planCode = maintenanceDetailQueryParam.getPlanCode();
        //根据 planCode 查询  全部删除
       MaintenancePlan maintenancePlan= maintenancePlanMapper.getMaintenanceById(planCode);

       Boolean flag= maintenancePlanMapper.deleteByPlanCode(planCode);
        //List 集合进行全部插入
        for (MaintenanceDetail disposal : maintenanceDetails) {
            disposal.setPlans(maintenancePlan.getPlans());
            disposal.setYear(maintenancePlan.getYear());
            disposal.setMonth(maintenancePlan.getMonth());
            disposal.setTown(maintenancePlan.getTown());
            disposal.setType(maintenancePlan.getType());
            disposal.setProjectTeam(maintenancePlan.getProjectTeam());
            disposal.setPlanCode(planCode);
            int insert = maintenanceDetailMapper.insert(disposal);
        }
        return flag;
    }

    @Override
    public Boolean deleteByplanCode(String id) {
        Integer deleteByplanCode1=maintenancePlanMapper.deleteByplanCode(id);

        Integer deleteByplanCode= maintenanceDetailMapper.deleteByplanCode(id);
        Boolean flag=false;
        if(deleteByplanCode!=0){
            flag=true;
        }


        return flag;
    }

    @Override
    public List<MaintenanceDetailPercenvO> getPlan(String year, String month, String project, String task, String area) {
        List<MaintenancePlanQueryVos> plan = maintenancePlanMapper.getPlan(year, month, project, task, area);
        List<MaintenanceDetailPercenvO> maintenanceDetailPercenvOS = new ArrayList<>();
        for (int i = 0; i < plan.size(); i++) {
            MaintenanceDetailPercenvO maintenanceDetailPercenvO = new MaintenanceDetailPercenvO();
            String planCode = plan.get(i).getPlanCode();

            BeanUtils.copyProperties(plan.get(i),maintenanceDetailPercenvO);
            Double carry= maintenancePlanMapper.getCarry(planCode);
            Double total= maintenancePlanMapper.gettotal(planCode);
            if (carry==null){
                carry=0.0;
            }
            if (total==null){
                total=0.0;
            }
            if (total==0.0){
                maintenanceDetailPercenvO.setPercentage("0.00%");
            }else {
                DecimalFormat df = new DecimalFormat("0.00%");
                String format = df.format(carry / total);
                maintenanceDetailPercenvO.setPercentage(format);
            }

            List<MaintenanceDetail> detalList = maintenancePlanMapper.getDetalList(planCode);
            maintenanceDetailPercenvO.setMaintenanceDetailList(detalList);
            maintenanceDetailPercenvOS.add(maintenanceDetailPercenvO);
        }


        return maintenanceDetailPercenvOS;
    }

    @Override
    public PageInfo<MaintenanceDetailPercenvO> getPlans(PlansVo plansVo) {
        PageHelper.startPage(plansVo.getFindArticleDto().getPage(), plansVo.getFindArticleDto().getPageSize(),"");
        String area = plansVo.getArea();
        String month = plansVo.getMonth();
        String project = plansVo.getProject();
        String task = plansVo.getTask();
        String year = plansVo.getYear();
        if (project!=null) {
            if (!(project.equals(""))) {
                 project = project;
                String replace = "'" + project + "'";
                if (project.contains(",")) {
                    replace = project.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                project=replace;
            }
        }
        if (area!=null) {
            if (!(area.equals(""))) {
                area =area;
                String replace = "'" + area + "'";
                if (area.contains(",")) {
                    replace = area.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                area=replace;
            }
        }
        List<MaintenancePlanQueryVos> plan = maintenancePlanMapper.getPlan(year, month, project, task, area);
        List<MaintenanceDetailPercenvO> maintenanceDetailPercenvOS = new ArrayList<>();
        for (int i = 0; i < plan.size(); i++) {
            MaintenanceDetailPercenvO maintenanceDetailPercenvO = new MaintenanceDetailPercenvO();
            String planCode = plan.get(i).getPlanCode();

            BeanUtils.copyProperties(plan.get(i), maintenanceDetailPercenvO);
            Double carry = maintenancePlanMapper.getCarry(planCode);
            Double total = maintenancePlanMapper.gettotal(planCode);
            if (carry == null) {
                carry = 0.0;
            }
            if (total == null) {
                total = 0.0;
            }
            if (total == 0.0) {
                maintenanceDetailPercenvO.setPercentage("0.00%");
            } else {
                DecimalFormat df = new DecimalFormat("0.00%");
                String format = df.format(carry / total);
                maintenanceDetailPercenvO.setPercentage(format);
            }

            List<MaintenanceDetail> detalList = maintenancePlanMapper.getDetalList(planCode);
            maintenanceDetailPercenvO.setMaintenanceDetailList(detalList);
            maintenanceDetailPercenvOS.add(maintenanceDetailPercenvO);
        }

        PageInfo pageInfo = new PageInfo(plan);

        pageInfo.setList(maintenanceDetailPercenvOS);

            return pageInfo;


    }
    @Override
    public PageInfo<MaintenanceDetails> getListPlan(QueryParameterObjectPageMainten queryParameter) throws ParseException {
            PageHelper.startPage(queryParameter.getFindArticleDto().getPage(), queryParameter.getFindArticleDto().getPageSize(),"");
            String month = queryParameter.getMonth();
            String year = queryParameter.getYear();
            String plan = queryParameter.getPlan();
            String type = queryParameter.getType();
            String twon = queryParameter.getTwon();
            String project = queryParameter.getProject();
        List<String > projectList=new ArrayList<>();
        if (project != null&&!(project.equals(""))) {

        project = "["+project+"]";
        //json转换为list
        Gson gson = new Gson();
            projectList  = gson.fromJson(project, new TypeToken<List<String>>(){}.getType());
            if (project.equals("[杨浦市政养护建设工程有限公司]")){
                projectList=new ArrayList<>();
            }
        }
        List<MaintenanceDetails> list=  maintenancePlanMapper.getListPlan(month,year,plan,type,twon,projectList);
        for (MaintenanceDetails maintenanceDetail : list) {
            String detailCode = maintenanceDetail.getDetailCode();
           List<String> attBefore =maintenancePlanMapper.getPicuer(detailCode);
            maintenanceDetail.setPicuter(attBefore);
        }

            PageHelper.clearPage();
            PageInfo<MaintenanceDetails> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    public  List<Date> getdates(String date) throws ParseException {
        //Date date1 = new Date();
        String month1 = "";
        String day2 = "";
        String year2 = "";
        String[] split = date.split("-");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> dates = new ArrayList<>();
        Date startDate = new Date();
        Date endDate = new Date();
        String startTime = "";
        String endTime = "";
        if (split.length == 3) {
            year2 = split[0];
            // 管道做处理
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Date dd = format.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd);
            Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            String day = cc.toString();
            startTime = year2 + "-" + month1 + "-01 00:00:00";
            // 日期问题
            endTime = year2 + "-" + month1 + "-" + day + " 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
        }
        if (split.length == 2) {
            year2 = split[0];
            month1 = split[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Date dd = format.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd);
            Integer cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            String day = cc.toString();
            startTime = year2 + "-" + month1 + "-01 00:00:00";
            // 日期问题
            endTime = year2 + "-" + month1 + "-" + day + " 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
        }
        if (split.length == 1) {
            year2 = split[0];
            // 01 12
            startTime = year2 + "-" + "01-01 00:00:00";
            endTime = year2 + "-" + "12-31 23:59:59";
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
            //01 12
        }
        dates.add(startDate);
        dates.add(endDate);
        return dates;
    }


    public Date getdate(String date) throws ParseException {

        Date date1 = new Date();
        int day = date1.getDate();
        int year = date1.getYear()+1900;
        int month = date1.getMonth()+1;
        String month1="";
        String day2="";
        if (month<=9){
            month1="0"+month;

        }else {
            month1=Integer.toString(month);
        }
        if (day<=9){
            day2="0"+date1;

        }else {
            day2=Integer.toString(day);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2=null;
        if(date.equals("day")){
            //为天的时候 为 2020-09-23 00:00:00
            String time=year+"-"+month1+"-"+"01"+" 00:00:00";
            date2=simpleDateFormat.parse(time);

        }
        if(date.equals("month")){
            //为月的时候 为 2020-09-01 00:00:00
            String time=year+"-"+month1+"-"+"01"+" 00:00:00";
            date2=simpleDateFormat.parse(time);
        }
        if(date.equals("year")){
            //为年的时候 为 2020-01-01 00:00:00
            String time=year+"-"+"01"+"-"+"01"+" 00:00:00";
            date2=simpleDateFormat.parse(time);
        }

        return date2;
    }


}
