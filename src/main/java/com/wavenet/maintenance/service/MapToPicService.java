package com.wavenet.maintenance.service;

import com.alibaba.fastjson.JSONObject;
import com.wavenet.maintenance.dao.entity.Dict;
import com.wavenet.maintenance.web.query.DictQueryParam;
import com.wavenet.maintenance.web.vo.DictQueryVo;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2020-03-28
 */
public interface MapToPicService{
    JSONObject getMapToPic(String patrolCode, String substring);
}
