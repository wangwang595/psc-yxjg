/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: EventDispatchParam.java
 * @Package com.wavenet.maintenance.dao.entity
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-23 14:02
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.dao.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: EventDispatchParam
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-23 14:02     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class EventDispatchParam {

    private EventDispatch eventDispatch;

    private List<String> code;
}
