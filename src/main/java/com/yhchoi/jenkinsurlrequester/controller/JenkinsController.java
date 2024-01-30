package com.yhchoi.jenkinsurlrequester.controller;

import com.yhchoi.jenkinsurlrequester.properties.JenkinsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@RestController
@RequestMapping("/jenkins")
@RequiredArgsConstructor
public class JenkinsController {

    private final JenkinsProperties jenkinsProperties;

    @GetMapping("/{job}/build")
    public String build(@PathVariable String job) {
        String domain = jenkinsProperties.getServerDomain();
        String username = jenkinsProperties.getUsername();
        String token = jenkinsProperties.getUserToken();

        String uri = domain + "/job/" + job + "/build?delay=0sec";
        return WebClient.builder().build()
            .post()
            .uri(uri)
            .header("Authorization", "Basic " + getBasicAuthToken(username, token))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    private static String getBasicAuthToken(String username, String token) {
        String authString = username + ":" + token;
        return Base64.getEncoder().encodeToString(authString.getBytes());
    }
}
