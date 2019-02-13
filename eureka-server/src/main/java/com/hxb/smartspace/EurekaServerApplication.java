package com.hxb.smartspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Zhao Jinyan
 * @date 2019/1/31 11:01
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    /**
     * 程序入口
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
