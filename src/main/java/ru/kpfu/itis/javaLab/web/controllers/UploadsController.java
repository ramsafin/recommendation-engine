package ru.kpfu.itis.javaLab.web.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.javaLab.service.interfaces.StorageService;

import java.io.IOException;

/**
 * Created by Safin Ramil on 12.06.17
 * RamilSafNab1996@gmail.com
 */
@RestController
public class UploadsController {

    private final StorageService storageService;

    @Autowired
    public UploadsController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/uploads/post/{path:.+}")
    public byte[] uploadPostPicture(@PathVariable("path") String path) {
        try {
            return IOUtils.toByteArray(storageService.loadAsResource(path).getInputStream());
        } catch (IOException e) {
            return new byte[]{};
        }

    }
}
