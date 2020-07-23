package com.aaa.hou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author hou
 * @description
 * @data 2020/7/17 14:50
 **/
@SpringBootApplication
@MapperScan("com.aaa.hou.mapper")
public class ApplicationRun8081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8081.class,args);
    }
}
