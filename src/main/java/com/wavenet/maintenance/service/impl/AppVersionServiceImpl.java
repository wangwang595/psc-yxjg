package com.wavenet.maintenance.service.impl;

import com.wavenet.maintenance.dao.entity.AppVersion;
import com.wavenet.maintenance.dao.entity.OrgDept;
import com.wavenet.maintenance.dao.mapper.AppVersionMapper;
import com.wavenet.maintenance.service.AppVersionService;
import com.wavenetframework.boot.common.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author zll
 * @since 2020-04-29
 */
@Slf4j
@Service
public class AppVersionServiceImpl extends BaseServiceImpl<AppVersionMapper, AppVersion> implements AppVersionService {

    @Autowired
    private AppVersionMapper appVersionMapper;



    @Override
    public AppVersion selectNewData(String modular) {
        AppVersion list = appVersionMapper.selectNewData(modular);

        return list;
    }

    @Override
    public List<OrgDept> areaList() {
        List<OrgDept> list = appVersionMapper.areaList();

        return list;
    }

}
