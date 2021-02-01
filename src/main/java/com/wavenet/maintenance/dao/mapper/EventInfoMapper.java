package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.*;
import com.wavenet.maintenance.web.query.EventInfoQueryParam;
import com.wavenet.maintenance.web.query.EventInfoQueryParamCopy;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.vo.Paging;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
@Repository
public interface EventInfoMapper extends BaseMapper<EventInfo> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    EventInfoQueryVoPiager getEventInfoById(Serializable id);
    /*
     * @Author john
     * @Description //TODO 关联派遣查询
     * @Date 2020/4/22  19:01
     * @Param [id]
     * @return java.util.List<com.wavenet.maintenance.web.vo.EventInfoQueryVo>
     **/
    List<EventInfoQueryVo> findById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param eventInfoQueryParam
     * @return
     */
    IPage<EventInfoQueryVoStatus> getEventInfoPageList(@Param("page") Page page, @Param("eventInfoQueryParam") EventInfoQueryParam eventInfoQueryParam);

    IPage<EventInfoQueryVoStatus> getEventInfoPageLists(@Param("page") Page page, @Param("eventInfoQueryParam") EventInfoQueryParam eventInfoQueryParam);

    /**
     * 根据code关联查询
     */
    List<EventInfoQueryVo> selectInfoByCode(String code);

    /**
     * 根据路段名查询DispatchCode
     */
    List<EventDisposalRel> selectDispatchCode(String code);

    /*
    * 获取所有EventInfo的路段信息
    * */
    List<RoadVo> selectRoadVoList();

    /*
     * 获取roadSectionCode,起始时间获取eventList
     * */

    List<EventInfo> selectEventListBySectionCodeAndTime(@Param("param") EventInfoQueryParamCopy prarm);

    /*
    * 根据dispatchCode 查询 eventInfo
    * */
    List<EventInfo> selectEventInfoListByDispatchCode(@Param("dispatchCode")String dispatchCode);

    /**
     * 根据patrolCode查询事件表
     */
    List<EventInfoQueryVo> selectByPatrolCode(String PatrolCode);

    /**
     * 根据主键逻辑删除
     */
    boolean updateDelState(String eventCode);

    /**
     * 下拉选'全部'返回结果
     */
    IPage<EventInfoQueryVoStatus> selectTypeAll(@Param("page") Page page, @Param("eventInfoQueryParam") EventInfoQueryParam eventInfoQueryParam);

    /**
     * 根据personCode查询执行中的事件
     */
    List<EventInfoQueryVo> selectByPersonCode(String personCode);

    EventInfoCountVo selectEventInfoCount();

    /**
     * 查询eventInfo表中未完成的记录
     */
    List<EventInfoQueryVo> selectUnfinished(@Param("type") String type, @Param("town") String town);

    /**
     * 分组统计
     */
    List<EventInfoCountVo> groupStatistics();

    /**
     * 路面养护统计
     */
    List<EventCountQueryVo> selectEventCount(@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("town") String town);


    /**
     * 事件来源统计
     * @return
     */
    List<EventInfoSourceQueryVo> getInfoSource(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 片区年养护统计
     */
    List<EventInfoSourceByMonthQueryVo> getInfoSourceGroupByMonth(@Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("town") String town);





    Integer getFrequency(@Param("date")Date date2, @Param("projects")String projects);







    Integer getTotal(@Param("date")Date date2, @Param("projects")String projects);

    Integer getNow(@Param("date")Date date2, @Param("projects")String projects);

    Integer getClose(@Param("date")Date date2, @Param("projects")String projects);



    MileagesFreVOs getMileage(@Param("startDate")String startDate,
                             @Param("project") List<String> project,
                             @Param("endDate")String endDate);

    Integer getQuestions(@Param("startDate")String startDate,
                         @Param("project") List<String> project,
                         @Param("endDate")String endDate);



    Integer getWorkTotal(@Param("startDate")Date startDate,
                       @Param("endDate")Date endDate,
                       @Param("project") List<String> project
    );
    Integer getDispose(@Param("startDate")Date startDate,
                       @Param("endDate")Date endDate,
                       @Param("project") List<String> project
    );
    Integer getCarry(@Param("startDate")Date startDate,
                       @Param("endDate")Date endDate,
                       @Param("project") List<String> project
    );
    Integer getWork(@Param("startDate")Date startDate,
                       @Param("endDate")Date endDate,
                       @Param("project") List<String> project
    );


    Integer getProblem(@Param("startDate")Date startDate,
                       @Param("endDate")Date endDate,
                       @Param("project") List<String> project
                       );

    List<PatrolInfoS> listInformation(@Param("project")List<String> project,
                                      @Param("twon")String twon,
                                      @Param("type") String type,
                                      @Param("endDate") Date endDate,
                                      @Param("startDate")Date startDate);

    List<EventInfo> listInformationQuer( @Param("startDate")Date startDate,
                                         @Param("endDate") Date endDate,
                                         @Param("project")List<String> project,
                                         @Param("type")String type,
                                         @Param("twon")String twon,
                                         @Param("status")String status);


    List<EventInfo> cquiredQuantityQueryList(@Param("startDate")Date startDate,
                                             @Param("endDate") Date endDate,
                                             @Param("project")List<String> project,
                                             @Param("source")List<String> source,
                                             @Param("twon")String twon,
                                             @Param("status")String status,
                                             @Param("disease")String disease);

    Integer eventReport(@Param("sourceList") List<String> sourceList,
                                  @Param("beginTime")String beginTime,
                                  @Param("endTime")String endTime,
                                  @Param("eventState")String eventState,
                                  @Param("town")String town,
                                  @Param("projectTeam")String projectTeam,
                                  @Param("typeList")List<String> typeList);

    Integer eventCarry(@Param("sourceList") List<String> sourceList,
                                 @Param("beginTime")String beginTime,
                                 @Param("endTime")String endTime,
                                 @Param("eventState")String eventState,
                                 @Param("town")String town,
                                 @Param("projectTeam")String projectTeam,
                                 @Param("typeList")List<String> typeList);

    Integer eventReview(@Param("sourceList") List<String> sourceList,
                                  @Param("beginTime")String beginTime,
                                  @Param("endTime")String endTime,
                                  @Param("eventState")String eventState,
                                  @Param("town")String town,
                                  @Param("projectTeam")String projectTeam,
                                  @Param("typeList")List<String> typeList);


    List<EventInfoQueryVoStatus> getListTest(List<String> strings);

    List<EventInfoQueryVoStatus> getPageListsdasd();


    List<HistoricalEmergencyVo> listHistoricalEmergency(@Param("startDate")Date startDate,
                                                        @Param("endDate") Date endDate);

    List<EventInfo> getXy(@Param("startDate")Date startDate,
                     @Param("endDate") Date endDate);

    List<HistoricalEmergencyVo> listHistoricalRoad(@Param("startDate")Date startDate,
                                                   @Param("endDate") Date endDate);



    List<String> testsdad();


    void updatedada(@Param("name")String name, @Param("yd")double yd, @Param("xd")double xd);

    Integer getTotalReview();


    EventInfoQueryVoStatus getEventInfoByCode(@Param("eventCode")String eventCode);

    List<QuestionsVo> statisticCount(@Param("startDate")Date startDate,
                                     @Param("endDate") Date endDate);

    QuestionsCountVo getCount(@Param("startDate")Date startDate,
                           @Param("endDate") Date endDate);

    List<EventInfoQueryVoStatus> selectTypeAlls(@Param("eventInfoQueryParam") EventInfoQueryParamPage eventInfoQueryParam);

    List<EventInfoQueryVoStatus> getEventInfoPageListss(@Param("eventInfoQueryParam") EventInfoQueryParamPage eventInfoQueryParam);

    List<EventInfoQueryVoStatus> getEventInfoPageListsss(@Param("eventInfoQueryParam") EventInfoQueryParamPage eventInfoQueryParam);

    List<TimeCodeVo> getTimeList();


    List<EventInfo> getTimeLists(@Param("minDate")Date minDate,
                             @Param("maxDate")Date maxDate);

    String getTimeCode();


    List<QuestionsVo> statisticCountDistrict(@Param("startDate")Date startDate,
                                             @Param("endDate") Date endDate,
                                             @Param("district")String district);

    QuestionsCountVo getCountdistrict(@Param("startDate")Date startDate,
                                      @Param("endDate") Date endDate,
                                      @Param("district")String district);
}
