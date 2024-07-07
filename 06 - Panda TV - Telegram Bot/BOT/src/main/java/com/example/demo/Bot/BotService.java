package com.example.demo.Bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class BotService {

    private String userid;

    public BotService() {
    }

    public BotService(String userid){
        this.userid = userid;
    }

    public Boolean checkUser(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/users?userid=" + userid;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }

    public void addUser(String name, String userid, String firstname, String lastname, String lenguage){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/users";

        // Create HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a Map to store user data
        Map<String, String> userData = new HashMap<>();
        userData.put("username", name);
        userData.put("userid", userid);
        userData.put("firstname", firstname);
        userData.put("lastname", lastname);
        userData.put("lenguage", lenguage);

        // Convert Map into JSON string
        ObjectMapper mapper = new ObjectMapper();
        String jsonUserEntity = "";
        try {
            jsonUserEntity = mapper.writeValueAsString(userData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a new HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(jsonUserEntity, headers);

        // Send the request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
