package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.LssueEscation;

import com.wavenet.maintenance.web.vo.LssueEscationQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2021-01-27
 */
@Repository
public interface LssueEscationMapper extends BaseMapper<LssueEscation> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    LssueEscationQueryVo getLssueEscationById(Serializable id);


}
