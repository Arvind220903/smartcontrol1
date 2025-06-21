package com.arvind.smartcontrol.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnlockConfig {

    @Value("${smartcontrol.password}")
    public String password;

    @Value("${smartcontrol.unlock.delay}")
    public int delay;
}
