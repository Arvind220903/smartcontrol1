package com.arvind.smartcontrol.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "unlock")
public class UnlockConfig {
    public String deviceId = "Infinix";
    public String token = "DefaultPassword";
    public String password = "Arvind@220903";
    public int delay = 1000;
}
