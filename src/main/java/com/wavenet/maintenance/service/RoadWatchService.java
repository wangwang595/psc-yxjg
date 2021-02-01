/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: RoadWatchService.java
 * @Package com.wavenet.maintenance.service
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-22 13:18
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.wavenet.maintenance.dao.entity.RoadWatch;
import com.wavenet.maintenance.dao.entity.RoadWatchParam;
import com.wavenet.maintenance.web.vo.CountByMileageQueryVo;
import com.wavenet.maintenance.web.vo.RoadWatchVo;
import com.wavenetframework.boot.common.vo.Paging;

import java.util.List;

/**
 * @ClassName: RoadWatchService
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-22 13:18     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
public interface RoadWatchService {
    PageInfo<RoadWatch> selectRoadWatchTable(RoadWatchParam param) throws Exception;

    RoadWatchVo selectSum(RoadWatchParam param);

    /**
     * 巡查里程数统计
     * @return
     */
    CountByMileageQueryVo patrolMileage(String beginTime,String endTime,String town);

    /**
     * 巡查次数按城镇分组统计
     */
    List<RoadWatch> selectTotalGroupByTow(String beginTime,String endTime,String town);

    /**
     * 发现问题
     */
    List<RoadWatch> selectEventConGroupByTown(String beginTime,String endTime,String town);
}
