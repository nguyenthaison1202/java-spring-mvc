package com.example.demo.service;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadFileService {
    private final ServletContext servletContext;

    public UploadFileService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String uploadFile(MultipartFile file, String targetFolder) {
        String rootPath = "src/main/resources/static/resource/images";
        String fileName = "";
        try {
            byte[] bytes = file.getBytes();
            File dir = new File(rootPath + File.separator + "avatar");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator +fileName) ;
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
}
