package com.wavenet.maintenance.common;

import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.aspectj.weaver.ast.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 注解
 *
 * @Autor 王争光
 * @Date 2020/12/7 14:01
 * @Version 1.0
 */
public class test {

    public static void main(String[] args) {

        for (int i = 0; i < 21; i++) {
            String replace = UUID.randomUUID().toString().replace("-", "");
            System.out.println(replace);
        }
    }
}
@Data
 class User{
    private String Uname;

    private Integer age;

}
