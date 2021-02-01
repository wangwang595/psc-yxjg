package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.EventDisposalWorkload;
import com.wavenet.maintenance.web.query.EventDisposalWorkloadQueryParam;
import com.wavenet.maintenance.web.vo.EventDisposalWorkloadQueryVo;
import org.apache.ibatis.annotations.Param;
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
public interface EventDisposalWorkloadMapper extends BaseMapper<EventDisposalWorkload> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    EventDisposalWorkloadQueryVo getEventDisposalWorkloadById(Serializable id);

    List<EventDisposalWorkloadQueryVo> findById(Serializable id);
    /**
     * 获取分页对象
     *
     * @param page
     * @param eventDisposalWorkloadQueryParam
     * @return
     */
    IPage<EventDisposalWorkloadQueryVo> getEventDisposalWorkloadPageList(@Param("page") Page page, @Param("eventDisposalWorkloadQueryParam") EventDisposalWorkloadQueryParam eventDisposalWorkloadQueryParam);

    /**
     * 根据disposalCode查询翻挖厚度是否填写
     */
    List<EventDisposalWorkloadQueryVo> selectDisposalIsThick(String disposalCode);

    List<EventDisposalWorkload> getByDisposalCode(String disposalCode);

    void deleteByDispoalCode(String dispatchCode);


}
