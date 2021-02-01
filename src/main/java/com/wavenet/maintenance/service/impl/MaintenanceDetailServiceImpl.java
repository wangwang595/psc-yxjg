package com.wavenet.maintenance.service.impl;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.convert.CellValueUtil;
import com.wavenet.maintenance.dao.entity.MaintenancePlan;
import com.wavenet.maintenance.dao.entity.PushInfo;
import com.wavenet.maintenance.dao.mapper.MaintenancePlanMapper;
import com.wavenet.maintenance.service.PushService;
import com.wavenet.maintenance.web.query.MaintenanceDetailQueryParamCode;
import com.wavenet.maintenance.web.vo.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.MaintenanceDetail;
import com.wavenet.maintenance.dao.mapper.MaintenanceDetailMapper;
import com.wavenet.maintenance.service.MaintenanceDetailService;
import com.wavenet.maintenance.web.query.MaintenanceDetailQueryParam;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;


/**
 * <pre>
 *  服务实现类
 * </pre>
 * @author zll
 * @since 2020-03-13
 */
@Slf4j
@Service
public class MaintenanceDetailServiceImpl extends BaseServiceImpl<MaintenanceDetailMapper, MaintenanceDetail> implements MaintenanceDetailService {

    @Autowired
    private MaintenanceDetailMapper maintenanceDetailMapper;

    @Autowired
    private MaintenancePlanMapper planMapper;

    @Autowired
    private PushService pushService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveMaintenanceDetail(MaintenanceDetail maintenanceDetail) throws Exception {
        //查看参数中的年，月，片区 是否存在plan表中
        String month = maintenanceDetail.getMonth().toString();
        String year = maintenanceDetail.getYear().toString();
        String town = maintenanceDetail.getTown();
        List<MaintenancePlanQueryVo> existList = planMapper.selectExist(year, month, town);
        //如果不存在则在Plan表中新增记录
        if (existList.size()==0) {
            MaintenancePlan plan =new MaintenancePlan();
            plan.setMonth(maintenanceDetail.getMonth());
            plan.setYear(maintenanceDetail.getYear());
            plan.setTown(maintenanceDetail.getTown());
            plan.setManhole(maintenanceDetail.getManhole());
            plan.setCatchBasin(maintenanceDetail.getCatchBasin());
            plan.setType(maintenanceDetail.getType());
            planMapper.insert(plan);
        }else {
            //如果存在则直接在detail表中新增记录
            List<MaintenancePlanQueryVo> planList = planMapper.selectExist(year, month, town);
            for (int i=0;i<planList.size();i++) {
                String planCode = planList.get(i).getPlanCode();
                maintenanceDetail.setCuringState("未执行");
                maintenanceDetail.setPlanCode(planCode);
                super.save(maintenanceDetail);
                PushInfo info = new PushInfo();
                info.setType("2");
                pushService.push(info);
            }

        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMaintenanceDetail(MaintenanceDetail maintenanceDetail) throws Exception {
        return super.updateById(maintenanceDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteMaintenanceDetail(String id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public MaintenanceDetailQueryVo getMaintenanceDetailById(Serializable id) throws Exception {
        return maintenanceDetailMapper.getMaintenanceDetailById(id);
    }

    @Override
    public Paging<MaintenanceDetailQueryVo> getMaintenanceDetailPageList(MaintenanceDetailQueryParam maintenanceDetailQueryParam) throws Exception {
        Page page = setPageParam(maintenanceDetailQueryParam, OrderItem.desc("month"));
        IPage<MaintenanceDetailQueryVo> iPage = maintenanceDetailMapper.getMaintenanceDetailPageList(page, maintenanceDetailQueryParam);
        return new Paging(iPage);
    }

    @Override
    public PageInfo<MaintenanceDetailQueryVo> getMaintenanceDetail(MaintenanceDetailQueryParamCode maintenanceDetailQueryParam) throws Exception {
        PageHelper.startPage(maintenanceDetailQueryParam.getFindArticleDto().getPage(), maintenanceDetailQueryParam.getFindArticleDto().getPageSize(),"");
        String curingState = maintenanceDetailQueryParam.getCuringState();
        List<String> objects = new ArrayList<>();
        if ("未处置".equals(curingState)){
            objects.add("执行中");
            objects.add("未执行");
            maintenanceDetailQueryParam.setCuringState("");
        }else if("已处置".equals(curingState)){
            objects.add("已完成");
            objects.add("已审核");
            maintenanceDetailQueryParam.setCuringState("");
        }
        // 分页
        if (maintenanceDetailQueryParam.getTown()!=null) {
            if (!(maintenanceDetailQueryParam.getTown().equals(""))) {
                String town = maintenanceDetailQueryParam.getTown();
                String replace = "'" + town + "'";
                if (town.contains(",")) {
                    replace = town.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                maintenanceDetailQueryParam.setTown(replace);
            }
        }
        List<MaintenanceDetailQueryVo> vo=maintenanceDetailMapper.getMaintenanceDetail(maintenanceDetailQueryParam,objects);

        PageInfo<MaintenanceDetailQueryVo> objectPageInfo = new PageInfo<MaintenanceDetailQueryVo>(vo);

        return objectPageInfo;
    }

    @Override
    public boolean insertExcel(MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet sheetAt02 = xssfWorkbook.getSheetAt(0);
        for (int i = 0; i < sheetAt02.getLastRowNum(); i++) {
            MaintenanceDetail maintenanceDetail = new MaintenanceDetail();
            XSSFRow row = sheetAt02.getRow(i+1);
            //Year
            XSSFCell c2 = row.getCell(1);
            //Month
            XSSFCell c3 = row.getCell(2);
            //ProjectTeam
            XSSFCell c4 = row.getCell(3);
            //Town
            XSSFCell c5 = row.getCell(4);
            //Type
            XSSFCell c6 = row.getCell(5);
            //Plans
            XSSFCell c7 = row.getCell(6);
            //RoadName
            XSSFCell c8 = row.getCell(7);
            //PipeGrade
            XSSFCell c9 = row.getCell(8);
            //Manhole
            XSSFCell c10 = row.getCell(9);
            //CatchBasin
            XSSFCell c11 = row.getCell(10);

            String v2 = CellValueUtil.getCellValueByCell(c2);
            String v3 = CellValueUtil.getCellValueByCell(c3);
            String v4 = CellValueUtil.getCellValueByCell(c4);
            String v5 = CellValueUtil.getCellValueByCell(c5);
            String v6 = CellValueUtil.getCellValueByCell(c6);
            String v7 = CellValueUtil.getCellValueByCell(c7);
            String v8 = CellValueUtil.getCellValueByCell(c8);
            String v9 = CellValueUtil.getCellValueByCell(c9);
            String v10 = CellValueUtil.getCellValueByCell(c10);
            String v11 = CellValueUtil.getCellValueByCell(c11);
            maintenanceDetail.setYear(v2==null||v2.equals("")?null:Double.valueOf(v2));
            maintenanceDetail.setMonth(v3==null||v3.equals("")?null:Double.valueOf(v3));
            maintenanceDetail.setProjectTeam(v4==null||v4.equals("")?null:v4);
            maintenanceDetail.setTown(v5==null||v5.equals("")?null:v5);
            maintenanceDetail.setType(v6==null||v6.equals("")?null:v6);
            maintenanceDetail.setPlans(v7==null||v7.equals("")?null:v7);
            maintenanceDetail.setRoadName(v8==null||v8.equals("")?null:v8);
            maintenanceDetail.setPipeGrade(v9==null||v9.equals("")?null:v9);
            maintenanceDetail.setManhole(v10==null||v10.equals("")?null:new BigDecimal(v10).doubleValue());
            maintenanceDetail.setCatchBasin(v11==null||v11.equals("")?null:new BigDecimal(v11).doubleValue());
            try {
                maintenanceDetailMapper.insert(maintenanceDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        is.close();
        return true;
    }

    @Override
    public UnblockStatisticsCountListVo statisticCount(String times,String type,String district) throws ParseException {
        UnblockStatisticsCountListVo unblockStatisticsCountListVo = new UnblockStatisticsCountListVo();
        String[] split = times.split("-");
        Integer year2 =0;
        Integer month1 =0;
        if (split.length == 3) {
            year2 = Integer.valueOf(split[0]);
            month1 = Integer.valueOf(split[1]);
        }
        if (split.length == 2) {
            year2 = Integer.valueOf(split[0]);
            month1 = Integer.valueOf(split[1]);
        }
        if (split.length == 1) {
            year2 = Integer.valueOf(split[0]);
        }
        List<UnblockStatisticsVo>  pratolInfoVos= new ArrayList<>();
        // 获取 雨水口 井口 官网长度
        UnblockStatisticsCountVo list= new UnblockStatisticsCountVo();
        if (("1").equals(type)){
             pratolInfoVos= maintenanceDetailMapper.statisticCount(year2,month1);
            // 获取 雨水口 井口 官网长度
            list= maintenanceDetailMapper.getcount(year2,month1);
        }else {
            pratolInfoVos= maintenanceDetailMapper.statisticCountDistrict(year2,month1,district);
            // 获取 雨水口 井口 官网长度
            list= maintenanceDetailMapper.getcountDistrict(year2,month1,district);
        }

        unblockStatisticsCountListVo.setUnblockStatisticsVos(pratolInfoVos);
        unblockStatisticsCountListVo.setUnblockStatisticsCountVo(list);
        return unblockStatisticsCountListVo;

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
            day2 = split[2];
            month1 = split[1];
            startTime = year2 + "-" + month1 + "-" + day2 + " 00:00:00";
            endTime = year2 + "-" + month1 + "-" + day2 + " 23:59:59";
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


}
