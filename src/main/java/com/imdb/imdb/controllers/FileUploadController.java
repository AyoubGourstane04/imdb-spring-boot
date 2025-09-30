package com.imdb.imdb.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imdb.imdb.services.FileUploadService;

import org.springframework.core.io.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;

@CrossOrigin(origins = {"http://127.0.0.1:8080", "http://localhost:8080"}, maxAge = 3600)
@Data
@AllArgsConstructor
@RestController
@RequestMapping("users/files")
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file) throws IOException{
        return fileUploadService.uploadFile(file);
    }

    @GetMapping("/get/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) throws MalformedURLException,IOException{
        return fileUploadService.getFile(fileName);
    }

}
