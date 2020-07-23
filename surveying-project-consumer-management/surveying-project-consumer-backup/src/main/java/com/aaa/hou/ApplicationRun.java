package com.aaa.hou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/7/15 9:13
 * @Description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.aaa.hou"})
public class ApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }

}
