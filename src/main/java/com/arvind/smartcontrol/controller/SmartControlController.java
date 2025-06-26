package com.arvind.smartcontrol.controller;

import org.springframework.web.bind.annotation.*;
import java.awt.*;
import java.awt.event.InputEvent;

@RestController
@RequestMapping("/api")
public class SmartControlController {

    private final Robot robot;

    public SmartControlController() throws AWTException {
        this.robot = new Robot();
    }

    @PostMapping("/wifi/on")
    public String enableWiFi() {
        return runCommand("netsh interface set interface name=\"Wi-Fi\" admin=enable");
    }

    @PostMapping("/wifi/off")
    public String disableWiFi() {
        return runCommand("netsh interface set interface name=\"Wi-Fi\" admin=disable");
    }

    @PostMapping("/bluetooth/on")
    public String enableBluetooth() {
        return runCommand("powershell -ExecutionPolicy Bypass -Command \"Get-PnpDevice -Class Bluetooth | Enable-PnpDevice -Confirm:$false\"");
    }

    @PostMapping("/bluetooth/off")
    public String disableBluetooth() {
        return runCommand("powershell -ExecutionPolicy Bypass -Command \"Get-PnpDevice -Class Bluetooth | Disable-PnpDevice -Confirm:$false\"");
    }

    @PostMapping("/mouse/move")
    public String moveMouse(@RequestParam int x, @RequestParam int y) {
        robot.mouseMove(x, y);
        return "Mouse moved to (" + x + ", " + y + ")";
    }

    @PostMapping("/mouse/click")
    public String clickMouse() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        return "Mouse clicked";
    }

    private String runCommand(String command) {
        try {
            Runtime.getRuntime().exec(command);
            return "Executed: " + command;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error running command: " + e.getMessage();
        }
    }
}
