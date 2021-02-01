package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.Dict;
import com.wavenet.maintenance.web.query.DictQueryParam;
import com.wavenet.maintenance.web.vo.DictQueryVo;
import com.wavenet.maintenance.web.vo.EventTypeQueryVo;
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
 * @since 2020-03-28
 */
@Repository
public interface DictMapper extends BaseMapper<Dict> {


    /**
     * 根据父id获取查询对象
     *
     * @param id
     * @return
     */
    List<DictQueryVo> selectListByParentCode(Serializable id);
}
