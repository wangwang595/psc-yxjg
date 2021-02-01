package com.wavenet.maintenance.service;

import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.AddEventInfoAndEventDispatch;
import com.wavenet.maintenance.dao.entity.EventDisposalRel;
import com.wavenet.maintenance.dao.entity.EventInfo;
import com.wavenet.maintenance.dao.entity.OrgDept;
import com.wavenet.maintenance.web.query.*;
import com.wavenet.maintenance.web.vo.*;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
public interface EventInfoService extends BaseService<EventInfo> {

    /**
     * 保存
     *
     * @param eventInfo
     * @return
     * @throws Exception
     */
    boolean saveEventInfo(EventInfo eventInfo) throws Exception;

    /**
     * 修改
     *
     * @param eventInfo
     * @return
     * @throws Exception
     */
    boolean updateEventInfo(EventInfo eventInfo) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteEventInfo(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    EventInfoQueryVoPiager getEventInfoById(Serializable id) throws Exception;
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
     * @param eventInfoQueryParam
     * @return
     * @throws Exception
     */
    Paging<EventInfoQueryVoStatus> getEventInfoPageList(EventInfoQueryParam eventInfoQueryParam) throws Exception;

    /**
     * 根据code进行关联查询
     */
    List<EventInfoQueryVo> selectInfoByCode(String code);

    /**
     * 根据路段编号查询返回dispatch_code
     */
    List<EventDisposalRel> selectDispatchCode(String code);


    /*
    * 获取所有的路段信息
    * */
    List<RoadVo> selectRoadList();


    /*
     * 获取路段code获取所有的事件信息
     * */
    List<EventInfo> selectEventInfoListByRoadSectionCode(EventInfoQueryParamCopy eventInfoQueryParamCopy);

    /**
     * 新增事件并建立关联关系
     */

    boolean insertEventInfo(EventInfo eventInfo,String endTime) throws Exception;

    /**
     * 事件处置信息
     */
    EventDisposalQueryVo getEventDisposalById(Serializable code) throws Exception;

    /**
     * 根据dispatch_code查询event_info列表
     */
    List<EventInfo> selectEventInfoByDispatchCode(String dispatchCode);

    /**
     * 新增病害信息时同时生成派遣记录
     *
     * @param
     * @return
     * @throws Exception
     */
    String saveEventInfoNew(AddEventInfoAndEventDispatch addEventInfoAndEventDispatch) throws Exception;

    /**
     * 根据personCode查询执行中的事件
     */
    List<EventInfoQueryVo> selectByPersonCode(String personCode);

    /**
     * 查询上报时间总和
     */
    EventInfoCountVo selectEventCount();

    /**
     * 查询eventInfo表中未完成的记录
     */
    List<EventInfoQueryVo> selectUnfinished(String type, String town);

    /**
     * Info,dispatch,disposal 状态改为延期
     */
    boolean postpone(String eventCode) throws Exception;

    /**
     * 分组统计
     */
    List<EventInfoCountVo> groupStatistics();

    /**
     * 路面养护统计
     */
    List<EventCountQueryVo> selectEventCount(String beginTime,String endTime,String town);

    /**
     * 事件来源统计
     * @return
     */
    List<EventInfoSourceQueryVo> selectSource(String beginTime, String endTime);

    /**
     * 片区年养护统计
     */
    List<EventInfoSourceByMonthQueryVo> getInfoSourceGroupByMonth(String beginTime,String endTime,String town);



    PageInfo<EventInfo> cquiredQuantityQueryList(QueryParameterEventPage queryParameter) throws ParseException;


    EvenInfoVo pAcquiredQuantityQuery(PatrolInfoObjectQuerParam queryParameter) throws ParseException;

    Map<String, GroupStatisticsVo> getEventRecord(String source, String beginTime, String endTime, String eventState, String town, String projectTeam, String type);

    HistoricalEmergencyVoS listHistoricalEmergency(String year) throws ParseException;



    Boolean updateImage(EventInfoImage eventInfo);


    EventInfoQueryVoStatus getEventInfoByCode(String eventCode);


    QuestionsCountListVo statisticCount(String times,String type,String district) throws ParseException;

    PageInfo<EventInfoQueryVoStatus> getPageLists(EventInfoQueryParamPage eventInfoQueryParam);


    Boolean timeCode();


    List<OrgDeptTreeVo> areaTree(  List<OrgDept> orgDeptApiResult);


    PageInfo<EventInfoQueryVoStatus> pageListWeb(EventInfoQueryParamPage eventInfoQueryParam);

}
