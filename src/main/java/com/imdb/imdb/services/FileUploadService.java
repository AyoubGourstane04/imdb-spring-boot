package com.imdb.imdb.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.imdb.imdb.config.FileUploadConfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Service
@Data
@Builder
@AllArgsConstructor
public class FileUploadService {

    private final FileUploadConfig.Properties fileUploadProperties;
    

    
    public String uploadFile(MultipartFile file) throws IOException{
        String dir = fileUploadProperties.getUploadDir();

        Path path = Paths.get(dir);

        if(!Files.exists(path)){
            Files.createDirectories(path);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = path.resolve(fileName);

        // System.out.println(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // System.out.println(filePath);

        return filePath.toString();
    }

    public ResponseEntity<Resource> getFile(String filename) {
        try {

            Path filePath = Paths.get(fileUploadProperties.getUploadDir()).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            // System.out.println(resource);

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
