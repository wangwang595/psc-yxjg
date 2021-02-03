package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.SludgeSite;

import com.wavenet.maintenance.web.vo.SludgeSiteQueryVo;
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
 * @since 2021-02-02
 */
@Repository
public interface SludgeSiteMapper extends BaseMapper<SludgeSite> {



    /**
     * 获取分页对象
     *

     * @param sludgeSiteQueryParam
     * @return
     */
    List<SludgeSiteQueryVo> getSludgeSitePageList( @Param("param") SludgeSiteQueryVo sludgeSiteQueryParam);

}
