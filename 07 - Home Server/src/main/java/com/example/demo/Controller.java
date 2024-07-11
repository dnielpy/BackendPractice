package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    Service service;

    @PostMapping("/path")
    public ResponseEntity<String> setPath(@RequestBody String path) {

        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        path = path.replace("\\", "/");
        path = path.replace("path=", "");
        service.setPath(path);
        return ResponseEntity.ok().body("Path set successfully");
    }

    @GetMapping("/videos")
    public ResponseEntity<String> getVideos() {
        List<Path> videoFiles = service.getVideoFiles();
        StringBuilder html = new StringBuilder("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "    <title>Mis Videos</title>\n" + "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #000;\n" +
                "            color: #fff;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            height: 100vh;\n" +
                "            flex-direction: column;\n" +
                "        }\n" +
                "        .video-link {\n" +
                "            display: inline-block;\n" +
                "            background-color: #ff0000;\n" +
                "            color: #fff;\n" +
                "            text-decoration: none;\n" +
                "            padding: 20px 40px;\n" +
                "            margin-bottom: 10px;\n" +
                "            border-radius: 5px;\n" +
                "            transition: background-color 0.3s ease;\n" +
                "            font-size: 1.5em;\n" +
                "        }\n" +
                "        .video-link:hover {\n" +
                "            background-color: #cc0000;\n" +
                "        }\n" +
                "    </style>\n" + "</head>\n" + "<body>\n" + "    <div class=\"container\">");
        for (Path videoFile : videoFiles) {
            try {
                String videoPath = URLEncoder.encode(videoFile.toAbsolutePath().toString(), StandardCharsets.UTF_8);
                String videoName = videoFile.getFileName().toString();
                html.append("<a class=\"video-link\" href=\"/video?path=").append(videoPath).append("\" download>").append(videoName).append("</a><br>");
            } catch (Exception e) {
                throw new RuntimeException("Error creating URL for video", e);
            }
        }
        html.append("    </div>\n" + "</body>\n" + "</html>");
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html.toString());
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
}