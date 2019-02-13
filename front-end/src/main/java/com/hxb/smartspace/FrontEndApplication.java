package com.hxb.smartspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Zhao Jinyan
 * @date 2019/1/31 11:27
 */
@SpringBootApplication
@EnableEurekaClient
public class FrontEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontEndApplication.class, args);
    }

}
