/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: TAccount.java
 * @Package com.wavenet.entity
 * @Description: (用一句话描述该文件做什么)
 * @author: hp
 * @date: 2019/10/31 9:49
 * @version V1.0
 * @Copyright: 2019 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.manager.common;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class MongoDbFile {
    private String _id;

    private Binary content;



    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }


    public String getsId() {
        return _id;
    }

    public void setsId(String sId) {
        this._id = sId;
    }

}
