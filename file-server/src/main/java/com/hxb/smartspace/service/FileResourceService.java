package com.hxb.smartspace.service;

import com.hxb.smartspace.FileServerConfiguration;
import com.hxb.smartspace.FileServerDefinition;
import com.hxb.smartspace.common.FileServerException;
import com.hxb.smartspace.dao.FileResourceDao;
import com.hxb.smartspace.entity.FileResource;
import com.hxb.smartspace.entity.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

/**
 * @author Zhao Jinyan
 * @date 2019/2/12 13:40
 */
@Service
@Slf4j
public class FileResourceService implements FileServerDefinition {

    private final FileServerConfiguration configuration;

    private final FileResourceDao dao;

    @Autowired
    public FileResourceService(FileServerConfiguration configuration, FileResourceDao dao) {
        this.configuration = configuration;
        this.dao = dao;
    }

    /**
     * 上传文件
     *
     * @param file 上传的文件
     * @return 资源对象
     */
    @Override
    public FileResource upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return FileResource.error("文件为空!");
        }

        String fileRealName = file.getOriginalFilename();
        String uuid = getUuid();
        File loadFile = new File(Paths.get(configuration.getTemp(), uuid).toString());

        try {
            file.transferTo(loadFile);
            return dao.save(new FileResource().setRealName(fileRealName).setName(uuid).setState(State.TEMP));
        } catch (Exception e) {
            e.printStackTrace();
            return FileResource.error("保存失败:" + e.getMessage());
        }

    }

    private String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 保存一个文件
     *
     * @param resource 文件资源
     * @return 文件资源
     */
    @Override
    public FileResource save(FileResource resource) {
        if (resource == null) {
            return FileResource.error("文件资源输入错误");
        }
        if (resource.getState() == State.SAVED) {
            return resource;
        }
        try {
            moveFileResourceTo(resource, Paths.get(configuration.getRepertory()).toString());
            return dao.save(resource.setState(State.SAVED).setSaveTime(new Date()));
        } catch (Exception e) {
            return FileResource.error("保存失败:" + e.getMessage());
        }
    }

    private void moveFileResourceTo(FileResource resource, String path) throws FileServerException {
        File localFile = getLocalFile(resource);
        if (!localFile.exists()) {
            throw new FileServerException("{}不存在!", resource.getName());
        }
        if (!localFile.renameTo(new File(path))) {
            throw new FileServerException("{}移动失败!", resource.getName());
        }
    }

    private File getLocalFile(FileResource resource) {
        return new File(getRealPath(resource));
    }

    private String getRealPath(FileResource resource) {
        String path;
        switch (resource.getState()) {
            case TEMP:
                path = configuration.getTemp();
                break;
            case SAVED:
                path = configuration.getRepertory();
                break;
            case DELETED:
                path = configuration.getTrashCan();
                break;
            default:
                path = configuration.getRepertory();
        }
        return Paths.get(path, resource.getName()).toString();
    }

    @Override
    public FileResource delete(FileResource resource) {
        if (resource == null) {
            return FileResource.error("文件资源输入错误");
        }
        if (resource.getState() == State.DELETED) {
            return resource;
        }

        try {
            moveFileResourceTo(resource, Paths.get(configuration.getTrashCan(), resource.getName()).toString());
            return dao.save(resource.setState(State.DELETED).setDeleteTime(new Date()));
        } catch (Exception e){
            return FileResource.error("删除文件失败:" + e.getMessage());
        }
    }

    @Override
    public FileResource get(String uuid) {
        return dao.findTopByName(uuid);
    }

    @Scheduled(cron = "0 */10 * * * ?")
    public void clearFiles(){
        log.info("run clearFiles...");
    }

}
