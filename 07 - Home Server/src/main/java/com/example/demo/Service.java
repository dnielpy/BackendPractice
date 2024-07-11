package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    String path;

    public Service(String path) {
        this.path = path;
    }

    public Service() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Path> getVideoFiles() {
        List<Path> videoFiles = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path), "*.{ts,mp4,mkv,avi}")) {
            for (Path path : stream) {
                videoFiles.add(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading video folder", e);
        }
        return videoFiles;
    }
}