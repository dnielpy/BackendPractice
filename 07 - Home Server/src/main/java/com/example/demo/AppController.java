package com.example.demo;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    Service service = new Service();

    @GetMapping("/path")
    public String path(Model model) {
        try {
            String ipAddress = Service.getLocalIpAddress();
            if (ipAddress != null) {
                model.addAttribute("serverIp", ipAddress);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "path";
    }

    @PostMapping("/path")
    public ResponseEntity<String> setPath(@RequestBody String path) {
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        path = path.replace("\\", "/");
        path = path.replace("path=", "");
        service.setPath(path);
        return ResponseEntity.ok().body("Path set successfully");
    }

    @GetMapping("/")
    public String home() {
        if (service.getPath() == null) {
            return "path";
        }
        return "index";
    }

    @GetMapping("/video")
    public ResponseEntity<Resource> downloadVideo(@RequestParam String path) {
        try {
            String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8);
            Path videoPath = Paths.get(decodedPath);
            Resource video = new UrlResource(videoPath.toUri());
            String mimeType = Files.probeContentType(videoPath);

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + video.getFilename() + "\"").body(video);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/videos")
    public String getVideos(Model model) {
        List<Path> videoFiles = service.getVideoFiles();
        List<VideoFile> videoFilesInfo = new ArrayList<>();
        for (Path videoPath : videoFiles) {
            String encodedPath = URLEncoder.encode(videoPath.toString(), StandardCharsets.UTF_8);
            String fileName = videoPath.getFileName().toString();
            videoFilesInfo.add(new VideoFile(encodedPath, fileName));
        }
        model.addAttribute("videos", videoFilesInfo);
        model.addAttribute("path", service.getPath());
        return "videos";
    }
}
