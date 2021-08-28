package com.bs.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author bingshao
 * @date 2021/8/23
 **/
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ServiceThirdPartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceThirdPartApplication.class,args);
    }
}
