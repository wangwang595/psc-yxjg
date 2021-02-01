package com.wavenet.maintenance.service;

import com.wavenet.maintenance.dao.entity.Dict;
import com.wavenet.maintenance.web.vo.EventTypeQueryVo;
import com.wavenetframework.boot.common.service.BaseService;
import com.wavenet.maintenance.web.query.DictQueryParam;
import com.wavenet.maintenance.web.vo.DictQueryVo;
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
public interface DictService extends BaseService<Dict> {



    /**
     * 根据父Id获取查询子对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<DictQueryVo> getDictTypeByParentCode(String id) throws Exception;
}
