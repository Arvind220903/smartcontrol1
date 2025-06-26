package com.arvind.smartcontrol.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.awt.*;

public class TrackpadSocketHandler extends TextWebSocketHandler {
    Robot robot;

    public TrackpadSocketHandler() throws AWTException {
        robot = new Robot();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            String[] parts = message.getPayload().split(",");
            double dx = Double.parseDouble(parts[0]);
            double dy = Double.parseDouble(parts[1]);

            Point current = MouseInfo.getPointerInfo().getLocation();
            robot.mouseMove((int)(current.x + dx), (int)(current.y + dy));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
