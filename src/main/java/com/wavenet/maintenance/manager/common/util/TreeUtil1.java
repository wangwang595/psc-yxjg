/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: TreeUtil1.java
 * @Package com.wavenet.maintenance.manager.common.util
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-06-30 17:42
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.manager.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @ClassName: TreeUtil1
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-06-30 17:42     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
public class TreeUtil1<T> {

    public TreeUtil1() {
    }


    public List<T> createMenu(List<T> list, String id, String pid,String pidValue) {
        List<T> listAll = new ArrayList();
        TreeMap map = new TreeMap();

        try {
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                T t = (T) var6.next();
                Field field = t.getClass().getDeclaredField(id);
                field.setAccessible(true);
                String code = field.get(t).toString();
                System.out.println("code === " + code);
                map.put(code, t);
            }
        } catch (IllegalAccessException var10) {
            var10.printStackTrace();
        } catch (NoSuchFieldException var11) {
            var11.printStackTrace();
        }

        map.forEach((k, tx) -> {
            try {
                Field field = tx.getClass().getDeclaredField(pid);
                Field field1 = tx.getClass().getDeclaredField(id);
                field.setAccessible(true);
                field1.setAccessible(true);
                String pCode = field.get(tx).toString();
                String code1 = field1.get(tx).toString();
                if(pidValue==null){
                    if(StringUtils.isBlank(pCode)){
                        listAll.add((T) tx);
                    }else{
                        T pt = (T) map.get(pCode);
                        Field children = pt.getClass().getDeclaredField("children");
                        children.setAccessible(true);
                        List<T> o = (List)children.get(pt);
                        if (o == null) {
                            children.set(pt, new LinkedList());
                        }

                        ((List)children.get(pt)).add(tx);
                    }
                }else {

                    if (code1.equals(pidValue)) {
                        listAll.add((T) tx);
                    } else {
                        try {
                            T pt = (T) map.get(pCode);
                            Field children = pt.getClass().getDeclaredField("children");
                            children.setAccessible(true);
                            List<T> o = (List) children.get(pt);
                            if (o == null) {
                                children.set(pt, new LinkedList());
                            }

                            ((List) children.get(pt)).add(tx);
                        }catch (Exception e){

                        }
                    }
                }
            } catch (Exception var10) {
                System.out.print(var10.getMessage());
            }

        });
        return listAll;
    }

}
