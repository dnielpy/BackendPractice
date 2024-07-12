package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.SocketException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

    @GetMapping("/videos")
    public String getVideos(Model model) {
        model.addAttribute("videos", service.getVideoFiles());
        model.addAttribute("path", service.getPath());
        return "videos";
    }
}
