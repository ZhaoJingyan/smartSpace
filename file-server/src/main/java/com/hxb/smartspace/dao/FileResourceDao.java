package com.hxb.smartspace.dao;

import com.hxb.smartspace.entity.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zhao Jinyan
 * @date 2019/2/12 13:52
 */
public interface FileResourceDao extends JpaRepository<FileResource, Integer> {

    /**
     * 根据uuid查找文件
     * @param name uuid
     * @return 文件资源
     */
    FileResource findTopByName(String name);

}
