package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.SludgeSite;
import com.wavenetframework.boot.common.service.BaseService;

import com.wavenet.maintenance.web.vo.SludgeSiteQueryVo;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2021-02-02
 */
public interface SludgeSiteService extends BaseService<SludgeSite> {



    /**
     * 获取分页对象
     *
     * @param sludgeSiteQueryParam
     * @return
     * @throws Exception
     */
    List<SludgeSiteQueryVo> getSludgeSitePageList(SludgeSiteQueryVo sludgeSiteQueryParam) throws Exception;

}
