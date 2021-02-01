package com.wavenet.maintenance.dao.mapper;

import com.wavenet.maintenance.web.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-03-28
 */
@Repository
public interface PumpInfoMapper {

    List<PumpInfoQueryVo> findPumpById(String id);

    List<WaterPointsRoadQueryVo> findPointsById(String id);

    List<WaterPointsRoadQueryVo> findUnderpassList(String id);

    List<WaterPointsQueryVo> findPointsDay(String param1, String param2);

    List<WaterPointsExtendQueryVo> findOrdinary();

    List<PumpWarningQueryVo> findPumpList(String value);

    List<PumpWarningNewQueryVo> findPumpListNew();
}
