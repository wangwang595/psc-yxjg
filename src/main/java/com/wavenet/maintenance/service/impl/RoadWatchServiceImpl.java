/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: RoadWatchServiceImpl.java
 * @Package com.wavenet.maintenance.service.impl
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-22 13:45
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.RoadWatch;
import com.wavenet.maintenance.dao.entity.RoadWatchParam;
import com.wavenet.maintenance.dao.mapper.RoadWatchMapper;
import com.wavenet.maintenance.service.RoadWatchService;
import com.wavenet.maintenance.web.vo.CountByMileageQueryVo;
import com.wavenet.maintenance.web.vo.EventInfoQueryVoStatus;
import com.wavenet.maintenance.web.vo.NameLvVO;
import com.wavenet.maintenance.web.vo.RoadWatchVo;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import com.wavenetframework.boot.common.vo.Paging;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @ClassName: RoadWatchServiceImpl
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-22 13:45     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Service
public class RoadWatchServiceImpl extends BaseServiceImpl<RoadWatchMapper,RoadWatch> implements RoadWatchService {
    @Resource
    private RoadWatchMapper mapper;
    @Override
    public PageInfo<RoadWatch> selectRoadWatchTable(RoadWatchParam param) throws Exception{

//        List<NameLvVO> nameLvVOList = param.getNameLvVOList();
//        // 第一等级
//        List<String> firstStrings = new ArrayList<>();
//
//        //第二等级
//        List<String> secondStrings = new ArrayList<>();
//        // 第三等级
//        List<String>  threeStrings = new ArrayList<>();
//        if (nameLvVOList.size()>0) {
//            for (NameLvVO nameLvVO : nameLvVOList) {
//                Integer lv = nameLvVO.getLv();
//                if (lv == 3) {
//                    threeStrings.add(nameLvVO.getName());
//                } else if (lv == 2) {
//                    secondStrings.add(nameLvVO.getName());
//                } else {
//                    firstStrings.add(nameLvVO.getName());
//                }
//            }
//
//        List<RoadWatch> roadWatchIPage=new ArrayList<>();
//        List<RoadWatch> roadWatchIPage1=new ArrayList<>();
//        List<RoadWatch> roadWatchIPage2=new ArrayList<>();
//        if (firstStrings.size()>0){
//           roadWatchIPage = mapper.selectInfoByCountFirst(param,firstStrings);
//        }
//        if (secondStrings.size()>0){
//           roadWatchIPage1 = mapper.selectInfoByCountSecond(param,secondStrings);
//        }
//        if (threeStrings.size()>0){
//          roadWatchIPage2 = mapper.selectInfoByCountThree(param,threeStrings);
//        }
//        List<RoadWatch> roadWatches = new ArrayList<>();
//        if (roadWatchIPage.size()>0){
//            roadWatches.addAll(roadWatchIPage);
//        }
//        if (roadWatchIPage1.size()>0){
//            roadWatches.addAll(roadWatchIPage1);
//        }
//        if (roadWatchIPage2.size()>0){
//            roadWatches.addAll(roadWatchIPage2);
//        }
//        TreeSet<RoadWatch> collect = roadWatches.stream()
//                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RoadWatch::getPatrolCode))));
//      List<RoadWatch> roadWatches1 = new ArrayList<>(collect);
//            roadWatchIPages.setRecords(roadWatches1);
//        }else {
        if (param.getTown()!=null) {
            if (!(param.getTown().equals(""))) {
                String town = param.getTown();
                String replace = "'" + town + "'";
                if (town.contains(",")) {
                    replace = town.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                param.setTown(replace);
            }
        }
        if (param.getProjectTeam()!=null) {
            if (!(param.getProjectTeam().equals(""))) {
                String projectTeam = param.getProjectTeam();
                String replace = "'" + projectTeam + "'";
                if (projectTeam.contains(",")) {
                    replace = projectTeam.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                param.setTown(replace);
            }
        }


       PageHelper.startPage(param.getFindArticleDto().getPage(), param.getFindArticleDto().getPageSize(),"");
        List<RoadWatch>  roadWatchIPages = mapper.selectInfoByCount(param);
        PageInfo<RoadWatch> eventInfoQueryVoStatusPageInfo = new PageInfo<>(roadWatchIPages);
        return eventInfoQueryVoStatusPageInfo;
    }

    @Override
    public RoadWatchVo selectSum(RoadWatchParam param) {
//        List<NameLvVO> nameLvVOList = param.getNameLvVOList();
//        // 第一等级
//        List<String> firstStrings = new ArrayList<>();
//
//        //第二等级
//        List<String> secondStrings = new ArrayList<>();
//        // 第三等级
//        List<String>  threeStrings = new ArrayList<>();
//        if (nameLvVOList.size()>0) {
//            for (NameLvVO nameLvVO : nameLvVOList) {
//                Integer lv = nameLvVO.getLv();
//                if (lv == 3) {
//                    threeStrings.add(nameLvVO.getName());
//                } else if (lv == 2) {
//                    secondStrings.add(nameLvVO.getName());
//                } else {
//                    firstStrings.add(nameLvVO.getName());
//                }
//            }
//        RoadWatchVo roadWatchIPage=new RoadWatchVo();
//        RoadWatchVo roadWatchIPage1=new RoadWatchVo();
//        RoadWatchVo roadWatchIPage2=new RoadWatchVo();
//        if (firstStrings.size()>0){
//            roadWatchIPage = mapper.selectSum1(param,firstStrings);
//        }
//        if (secondStrings.size()>0){
//            roadWatchIPage1 = mapper.selectSum2(param,secondStrings);
//        }
//        if (threeStrings.size()>0){
//            roadWatchIPage2 = mapper.selectSum3(param,threeStrings);
//        }
//        List<RoadWatchVo> roadWatches = new ArrayList<>();
//        if (roadWatchIPage!=null){
//            roadWatches.add(roadWatchIPage);
//        }
//        if (roadWatchIPage1!=null){
//            roadWatches.add(roadWatchIPage1);
//        }
//        if (roadWatchIPage2!=null){
//            roadWatches.add(roadWatchIPage2);
//        }
//            RoadWatchVo roadWatchIPage4=new RoadWatchVo();
//            Integer integer1 = 0;
//            Double integer2 = 0.0;
//            Integer integer3 = 0;
//            for (RoadWatchVo roadWatch : roadWatches) {
//                Double count = roadWatch.getCount();
//                integer2=count+integer2;
//                Integer eventcon = roadWatch.getEventcon();
//                integer1=integer1+eventcon;
//                Integer patrolcon = roadWatch.getPatrolcon();
//                integer3=integer3+eventcon;
//
//            }
//            roadWatchIPage4.setCount(integer2);
//            roadWatchIPage4.setEventcon(integer1);
//            roadWatchIPage4.setPatrolcon(integer3);
//            return roadWatchIPage4;
//        }
        if (param.getTown()!=null) {
            if (!(param.getTown().equals(""))) {
                String town = param.getTown();
                String replace = "'" + town + "'";
                if (town.contains(",")) {
                    replace = town.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                param.setTown(replace);
            }
        }
        if (param.getProjectTeam()!=null) {
            if (!(param.getProjectTeam().equals(""))) {
                String projectTeam = param.getProjectTeam();
                String replace = "'" + projectTeam + "'";
                if (projectTeam.contains(",")) {
                    replace = projectTeam.replace(",", "','");
                    replace = "'" + replace + "'";
                }
                param.setTown(replace);
            }
        }

        RoadWatchVo list = mapper.selectSum(param);
        return list;
    }

    @Override
    public CountByMileageQueryVo patrolMileage(String beginTime,String endTime,String town) {
        CountByMileageQueryVo vo = new CountByMileageQueryVo();
        List<RoadWatch> list = mapper.countByMileage(beginTime,endTime,town);
        CountByMileageQueryVo totalVo = mapper.selectTotal(beginTime,endTime,town);
        String total = totalVo.getTotal();
        vo.setMileage(list);
        vo.setTotal(total);
        return vo;
    }

    @Override
    public List<RoadWatch> selectTotalGroupByTow(String beginTime,String endTime,String town) {
        List<RoadWatch> list = mapper.selectTotalGroupByTow(beginTime,endTime,town);
        return list;
    }

    @Override
    public List<RoadWatch> selectEventConGroupByTown(String beginTime, String endTime, String town) {
        List<RoadWatch> list = mapper.selectEventConGroupByTown(beginTime, endTime, town);
        return list;
    }

}
