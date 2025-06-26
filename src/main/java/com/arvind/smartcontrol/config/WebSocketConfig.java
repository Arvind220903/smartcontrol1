package com.arvind.smartcontrol.config;

import com.arvind.smartcontrol.websocket.TrackpadSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private TrackpadSocketHandler trackpadSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("✅ Registering WebSocket handler...");
        registry.addHandler(trackpadSocketHandler, "/ws/trackpad")
                .setAllowedOrigins("*");
    }
}
