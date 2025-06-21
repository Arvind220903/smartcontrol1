package com.arvind.smartcontrol.config;

import org.springframework.stereotype.Component;

@Component
public class UnlockConfig {
    public String deviceId = "trusted-device-123";
    public String token = "secure-token";
    public String password = "mySecureWindowsPassword"; // type this exactly
    public int delay = 2000; // ms
}
