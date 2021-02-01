package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.LssueEscation;
import com.wavenetframework.boot.common.service.BaseService;

import com.wavenet.maintenance.web.vo.LssueEscationQueryVo;
import com.wavenetframework.boot.common.vo.Paging;

import java.io.Serializable;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author zll
 * @since 2021-01-27
 */
public interface LssueEscationService extends BaseService<LssueEscation> {

    /**
     * 保存
     *
     * @param lssueEscation
     * @return
     * @throws Exception
     */
    boolean saveLssueEscation(LssueEscation lssueEscation) throws Exception;


}
