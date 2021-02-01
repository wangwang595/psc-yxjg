/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: inPlans.java
 * @Package com.wavenet.maintenance.dao.entity
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-04-17 10:30
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.dao.entity;

import lombok.Data;

/**
 * @ClassName: inPlans
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-04-17 10:30     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class inPlans {
    private String planTotal;
    private String total;
    private String planRain;
    private String planSewage;
    private String planHl;
    private String rain;
    private String sewage;
    private String hl;
    private String complete;

}
