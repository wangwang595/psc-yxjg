package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.EventDispatchRel;
import com.wavenet.maintenance.web.vo.EventDispatchRelQueryVo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
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
public interface EventDispatchRelMapper extends BaseMapper<EventDispatchRel> {



    EventDispatchRelQueryVo getEventDispatchRelByEventId(Serializable code);

    /**
     * 根据dispatchCode查询
     */
    List<EventDispatchRelQueryVo> selectByDispatchCode(Serializable code);

}
