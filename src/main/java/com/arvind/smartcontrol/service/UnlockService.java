package com.arvind.smartcontrol.service;

import com.arvind.smartcontrol.config.UnlockConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.KeyEvent;

@Service
public class UnlockService {

    @Autowired
    private UnlockConfig config;

    public boolean verifyAndUnlock(String deviceId, String token) {
        if ("trusted-device-123".equals(deviceId) && "secure-token".equals(token)) {
            System.out.println("✅ Device verified. Simulating unlock...");

            try {
                Thread.sleep(config.delay); // Wait a few seconds for screen to activate

                Robot robot = new Robot();
                Runtime.getRuntime().exec("notepad");
                Thread.sleep(2000);
                typeString(robot, "you Clown the king is ");

                typeString(robot, config.password);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private void typeString(Robot robot, String s) {
        for (char c : s.toCharArray()) {
            typeChar(robot, c);
            try {
                Thread.sleep(50); // delay between keystrokes
            } catch (InterruptedException ignored) {}
        }
    }

    private void typeChar(Robot robot, char c) {
        try {
            boolean upper = Character.isUpperCase(c);
            int code = KeyEvent.getExtendedKeyCodeForChar(c);
            if (upper) robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(code);
            robot.keyRelease(code);
            if (upper) robot.keyRelease(KeyEvent.VK_SHIFT);
        } catch (Exception e) {
            System.err.println("Cannot type character: " + c);
        }
    }
}
