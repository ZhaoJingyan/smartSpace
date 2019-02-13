package com.hxb.smartspace;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Zhao Jinyan
 * @date 2019/2/11 14:04
 */
@Data
@Component
@ConfigurationProperties(prefix = "file-server")
public class FileServerConfiguration {

    /**
     * 存放文件的目录
     */
    private String repertory;

    /**
     *
     */
    private String temp;

    /**
     *
     */
    private String trashCan;

}
