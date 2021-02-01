/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: PumpWarningNewQueryVo.java
 * @Package com.wavenet.maintenance.web.vo
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-07-18 17:41
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web.vo;

import lombok.Data;

/**
 * @ClassName: PumpWarningNewQueryVo
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-07-18 17:41     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Data
public class PumpWarningNewQueryVo {
    private String tStime;

    private String nValue;

    private String sNo;

    private String sName;

    private String STATE;
}
