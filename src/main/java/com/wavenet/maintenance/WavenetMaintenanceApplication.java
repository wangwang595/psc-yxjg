package com.wavenet.maintenance;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableAdminServer
@MapperScan({"com.wavenet.maintenance.**.mapper"})
@ServletComponentScan
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class},scanBasePackages = {"com.wavenet*"})

public class WavenetMaintenanceApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WavenetMaintenanceApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(com.wavenet.maintenance.WavenetMaintenanceApplication.class, args);
    }
}
