package com.hxb.smartspace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zhao Jinyan
 * @date 2019/1/31 11:38
 */
@Configuration
@EnableWebMvc
@ConfigurationProperties
@Slf4j
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${front-end.front-end-project-path}")
    private String frontEndProjectPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("前端工程:{}", frontEndProjectPath);
        registry.addResourceHandler("/*").addResourceLocations(frontEndProjectPath);
        registry.addResourceHandler("/my/accountBalance/").addResourceLocations(frontEndProjectPath + "index.html");
        registry.addResourceHandler("/js/*").addResourceLocations(frontEndProjectPath + "js\\");
        registry.addResourceHandler("/css/*").addResourceLocations(frontEndProjectPath + "css\\");
        registry.addResourceHandler("/fonts/*").addResourceLocations(frontEndProjectPath + "fonts\\");
        registry.addResourceHandler("/img/*").addResourceLocations(frontEndProjectPath + "img\\");
        //registry.addResourceHandler("/favicon.ico").addResourceLocations(frontEndProjectPath + "favicon.ico");
    }

    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
            @Override
            public void customize(ConfigurableServletWebServerFactory factory) {
                ErrorPage page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
                factory.addErrorPages(page);
            }
        };
    }

}
