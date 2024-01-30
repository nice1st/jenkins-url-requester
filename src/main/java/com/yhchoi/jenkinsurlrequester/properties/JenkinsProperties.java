package com.yhchoi.jenkinsurlrequester.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("jenkins.build")
public class JenkinsProperties {

    private String serverDomain;
    private String username;
    private String userToken;
}
