package com.wavenet.maintenance.dao.mapper;

import java.io.Serializable;
import java.util.List;

import com.wavenet.maintenance.web.vo.EventInfoQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wavenet.maintenance.dao.entity.EventType;
import com.wavenet.maintenance.web.query.EventTypeQueryParam;
import com.wavenet.maintenance.web.vo.EventTypeQueryVo;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-03-24
 */
@Repository
public interface EventTypeMapper extends BaseMapper<EventType> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    EventTypeQueryVo getEventTypeById(Serializable id);


    /**
     * 获取分页对象
     *
     * @param page
     * @param eventTypeDoQueryParam
     * @return
     */
    IPage<EventTypeQueryVo> getEventTypePageList(@Param("page") Page page, @Param("param") EventTypeQueryParam eventTypeDoQueryParam);

    /**
     * 根据父id获取查询对象
     *
     * @param id
     * @return
     */
    List<EventTypeQueryVo> selectListByParentCode(Serializable id);
    /**
     * 根据名字查询
     */
    List<EventTypeQueryVo> selectByName(String name);

    /**
     * 根据code查询
     */
    List<EventTypeQueryVo> selectByCode(String code);
    /**
     * 查询父节点和本身节点
     */
    List<EventTypeQueryVo> selectParent(String pcode);

    List<EventInfoQueryVo> selectInfoByName(String name);

    /**
     * 根据市政,非市政查询树型结构
     */
    List<EventType> selectTreeByStandard(@Param("eventStandard") String eventStandard, @Param("code") String code);

    /**
     * 根据父id获取查询对象
     *
     * @param id
     * @return
     */
    List<EventTypeQueryVo> selectListByParentCodeNew(@Param("id") String id , @Param("eventStandard")String eventStandard);

    List<EventType> selectAll();

    /**
     * 检查井作业大类小类查询
     */
    List<EventTypeQueryVo> selectTypeByPcode(@Param("pCode") String pCode, @Param("name") String name);

    List<EventType> getSelectList();


}
