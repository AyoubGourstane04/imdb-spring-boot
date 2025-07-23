package com.imdb.imdb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@EnableConfigurationProperties(FileUploadConfig.Properties.class)
public class FileUploadConfig {
    


    @ConfigurationProperties(prefix = "file")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Properties{
        private  String uploadDir;

    }
}
