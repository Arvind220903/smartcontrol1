package com.arvind.smartcontrol.controller;

import org.springframework.web.bind.annotation.*;
import java.awt.*;
import java.util.Map;

@RestController
public class TrackpadController {
    private final Robot robot;

    public TrackpadController() throws AWTException {
        this.robot = new Robot();
    }

    @PostMapping("/trackpad/move")
    public void moveMouse(@RequestParam Map<String, String> params) {
        double dx = Double.parseDouble(params.getOrDefault("dx", "0"));
        double dy = Double.parseDouble(params.getOrDefault("dy", "0"));

        Point location = MouseInfo.getPointerInfo().getLocation();
        int newX = (int) (location.getX() + dx);
        int newY = (int) (location.getY() + dy);
        robot.mouseMove(newX, newY);

        System.out.println("Moved mouse to: " + newX + "," + newY); // For debug
    }
}
