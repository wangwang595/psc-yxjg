/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: EventTypeConvert.java
 * @Package com.wavenet.maintenance.convert
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-01 18:33
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.convert;

import com.wavenet.maintenance.dao.entity.EventTreeVo;
import com.wavenet.maintenance.dao.entity.EventType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @ClassName: EventTypeConvert
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-01 18:33     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Mapper
public interface EventTypeConvert {
    EventTypeConvert INSTANCE = Mappers.getMapper(EventTypeConvert.class);

    /**
     * OrgDept转换成OrgDeptTreeVo对象
     *
     * @param eventType
     * @return
     */
    EventTreeVo eventTreeVo(EventType eventType);

    /**
     * SysDepartment列表转换成SysDepartmentTreeVo列表
     *
     * @param list
     * @return
     */
    List<EventTreeVo> listToTreeVoList(List<EventType> list);

}
