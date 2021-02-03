package com.wavenetframework.boot;

import java.util.UUID;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2021/2/2 11:12
 * @Version 1.0
 */
public class tets {
    public static void main(String[] args) {
        UUID.randomUUID().toString().replace("-","");
        for (int i = 0; i < 16; i++) {
            System.out.println( UUID.randomUUID().toString().replace("-",""));
        }


    }
}
