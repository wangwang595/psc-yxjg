package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.RoadWatch;
import com.wavenet.maintenance.dao.entity.RoadWatchParam;
import com.wavenet.maintenance.web.vo.CountByMileageQueryVo;
import com.wavenet.maintenance.web.vo.RoadWatchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
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
public interface RoadWatchMapper extends BaseMapper<RoadWatch> {
    List<RoadWatch> selectInfoByCount(@Param("param") RoadWatchParam param);

    void updatePatrolSpeed(@Param("speed")String speed,@Param("code")String code);

    RoadWatchVo selectSum(RoadWatchParam param);

    List<RoadWatch> selectRoadList(RoadWatchParam param);

    /**
     * 公里数统计
     * @return
     */
    List<RoadWatch> countByMileage(@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("town") String town);

    /**
     * 巡查次数统计
     */
    CountByMileageQueryVo selectTotal(@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("town") String town);

    /**
     * 巡查次数按城镇分组统计
     */
    List<RoadWatch> selectTotalGroupByTow(@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("town") String town);

    /**
     * 发现问题
     */
    List<RoadWatch> selectEventConGroupByTown(@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("town") String town);

    List<RoadWatch> selectInfoByCountFirst(@Param("param")RoadWatchParam param,
                                           @Param("type")List<String> firstStrings);

    List<RoadWatch> selectInfoByCountSecond(@Param("param")RoadWatchParam param,
                                            @Param("type")List<String> secondStrings);

    List<RoadWatch> selectInfoByCountThree(@Param("param")RoadWatchParam param,
                                           @Param("type")List<String> threeStrings);
    RoadWatchVo selectSum1 (@Param("param")RoadWatchParam param,
                                           @Param("type")List<String> firstStrings);

    RoadWatchVo selectSum2(@Param("param")RoadWatchParam param,
                                            @Param("type")List<String> secondStrings);

    RoadWatchVo selectSum3(@Param("param")RoadWatchParam param,
                                           @Param("type")List<String> threeStrings);




}


