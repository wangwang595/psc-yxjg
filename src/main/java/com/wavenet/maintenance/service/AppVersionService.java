package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.AppVersion;
import com.wavenet.maintenance.dao.entity.OrgDept;
import com.wavenetframework.boot.common.service.BaseService;

import java.util.List;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-04-29
 */
public interface AppVersionService extends BaseService<AppVersion> {


    AppVersion selectNewData(String modular);

    List<OrgDept> areaList();

}
