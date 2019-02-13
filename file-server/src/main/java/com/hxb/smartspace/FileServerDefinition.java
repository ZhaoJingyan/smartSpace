package com.hxb.smartspace;

import com.hxb.smartspace.entity.FileResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * file server 功能定义
 * @author Zhao Jinyan
 * @date 2019/2/11 21:32
 */
public interface FileServerDefinition {

    /**
     * 上传文件
     * @param file 上传的文件
     * @return 文件资源对象
     */
    FileResource upload(MultipartFile file);

    /**
     * 保存
     * @param resource 文件资源
     * @return 文件资源
     */
    FileResource save(FileResource resource);

    /**
     * 删除
     * @param resource 文件资源
     * @return 文件资源
     */
    FileResource delete(FileResource resource);

    /**
     * 获取
     * @param uuid 文件资源
     * @return 文件资源
     */
    FileResource get(String uuid);

}
