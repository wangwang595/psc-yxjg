package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.EventDisposalRel;
import com.wavenet.maintenance.web.query.EventDisposalRelQueryParam;
import com.wavenet.maintenance.web.vo.EventDisposalRelQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-03-30
 */
@Repository
public interface EventDisposalRelMapper extends BaseMapper<EventDisposalRel> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    EventDisposalRelQueryVo getEventDisposalRelById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param eventDisposalRelQueryParam
     * @return
     */
    IPage<EventDisposalRelQueryVo> getEventDisposalRelPageList(@Param("page") Page page, @Param("param") EventDisposalRelQueryParam eventDisposalRelQueryParam);

}
