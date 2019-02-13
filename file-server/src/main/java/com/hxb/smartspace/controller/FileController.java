package com.hxb.smartspace.controller;

import com.hxb.smartspace.entity.FileResource;
import com.hxb.smartspace.entity.State;
import com.hxb.smartspace.service.FileResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zhao Jinyan
 * @date 2019/2/11 13:56
 */
@RestController("/")
@Slf4j
public class FileController {


    private final FileResourceService service;

    @Autowired
    public FileController(FileResourceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    public FileResource upload(@RequestParam("file") MultipartFile file) {
        return service.upload(file);
    }

    @GetMapping("/{uuid}")
    public FileResource get(@PathVariable(name = "uuid") String name){
        return service.get(name);
    }

    @PutMapping("/{uuid}")
    public FileResource save(@PathVariable(name = "uuid") String name){
        return service.save(service.get(name));
    }

    @DeleteMapping("/{uuid}")
    public FileResource delete(@PathVariable(name = "uuid") String name){
        return service.delete(service.get(name));
    }

}


