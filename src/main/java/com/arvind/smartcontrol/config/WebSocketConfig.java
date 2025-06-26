package com.arvind.smartcontrol.config;

import com.arvind.smartcontrol.websocket.TrackpadSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        try {
            registry.addHandler(new TrackpadSocketHandler(), "/ws/trackpad")
                    .setAllowedOrigins("*"); // Allows access from phone
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
