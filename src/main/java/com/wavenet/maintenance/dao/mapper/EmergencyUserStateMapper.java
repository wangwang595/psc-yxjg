package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wavenet.maintenance.dao.entity.EmergencyUserState;
import com.wavenet.maintenance.dao.entity.PatrolGpsRealtime;

import com.wavenet.maintenance.web.vo.EmergencyUserStateQueryVo;

import com.wavenet.maintenance.web.vo.PresonQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-06-10
 */
@Repository
public interface EmergencyUserStateMapper extends BaseMapper<EmergencyUserState> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    EmergencyUserStateQueryVo getEmergencyUserStateById(Serializable id);


}
