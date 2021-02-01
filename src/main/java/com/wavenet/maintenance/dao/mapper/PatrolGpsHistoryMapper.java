package com.wavenet.maintenance.dao.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.PatrolGpsHistory;
import com.wavenet.maintenance.web.query.PatrolGpsHistoryQueryParam;
import com.wavenet.maintenance.web.vo.PatrolGpsHistoryQueryVo;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-03-13
 */
@Repository
public interface PatrolGpsHistoryMapper extends BaseMapper<PatrolGpsHistory> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    PatrolGpsHistoryQueryVo getPatrolGpsHistoryById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param patrolGpsHistoryQueryParam
     * @return
     */
    IPage<PatrolGpsHistoryQueryVo> getPatrolGpsHistoryPageList(@Param("page") Page page, @Param("param") PatrolGpsHistoryQueryParam patrolGpsHistoryQueryParam);

    /**
     * 根据patrolCode获取信息
     */
    List<PatrolGpsHistoryQueryVo> selectXy(String patrolCode);

    /**
     * 查询轨迹历史表中状态为0
     */
    List<PatrolGpsHistoryQueryVo> selectStateIs0();

    /**
     * 修改轨迹历史表中状态为0
     */
    boolean updateByGpsCode();

}
