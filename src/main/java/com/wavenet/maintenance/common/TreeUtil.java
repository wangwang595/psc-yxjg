//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wavenet.maintenance.common;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class TreeUtil<T> {
    public TreeUtil() {
    }

    public List<T> createMenu(List<T> list, String id, String pid, String value) {
        List<T> listAll = new ArrayList();
        Map<String, T> map = new LinkedHashMap<String, T>();

        try {
            Iterator var6 = list.iterator();

            while (var6.hasNext()) {
                T t = (T) var6.next();
                Field field = t.getClass().getDeclaredField(id);
                field.setAccessible(true);
                String code = field.get(t).toString();
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
                field.setAccessible(true);
                Object pCode = field.get(tx);
                if (StringUtils.isNotBlank(value)) {
                    if (StringUtils.equals(k, value)) {
                        listAll.add((T) tx);
                    } else {
                        T pt = (T) map.get(pCode);
                        if (Objects.isNull(pt)) {
                            return;
                        }
                        Field children = pt.getClass().getDeclaredField("children");
                        children.setAccessible(true);
                        List<T> o = (List) children.get(pt);
                        if (o == null) {
                            children.set(pt, new LinkedList());
                        }

                        ((List) children.get(pt)).add(tx);
                    }
                } else {
                    if (Objects.isNull(pCode) || StringUtils.isBlank(pCode.toString())) {
                        listAll.add((T) tx);
                    } else {
                        T pt = (T) map.get(pCode);
                        if (Objects.isNull(pt)) {
                            return;
                        }
                        Field children = pt.getClass().getDeclaredField("children");
                        children.setAccessible(true);
                        List<T> o = (List) children.get(pt);
                        if (o == null) {
                            children.set(pt, new LinkedList());
                        }

                        ((List) children.get(pt)).add(tx);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        return listAll;
    }

    public List<T> createMenu(List<T> list, String id, String pid) {
        return this.createMenu(list, id, pid, null);
    }
}
