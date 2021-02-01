package com.wavenet.maintenance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wavenet.maintenance.dao.entity.AppVersion;
import com.wavenet.maintenance.dao.entity.OrgDept;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author zll
 * @since 2020-04-29
 */
@Repository
public interface AppVersionMapper extends BaseMapper<AppVersion> {


    AppVersion selectNewData(String modular);

    List<OrgDept> areaList();


}
