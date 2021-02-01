/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: PushService.java
 * @Package com.wavenet.maintenance.service
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-05-14 15:02
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.service;

import cn.jpush.api.push.PushResult;
import com.wavenet.maintenance.dao.entity.PushInfo;

/**
 * @ClassName: PushService
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-05-14 15:02     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
public interface PushService {
     PushResult push(PushInfo info) throws Exception;


}
