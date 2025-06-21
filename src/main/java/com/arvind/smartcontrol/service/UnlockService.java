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
        if (config.deviceId.equals(deviceId) && config.token.equals(token)) {
            System.out.println("✅ Verified device and token");

            try {
                Thread.sleep(config.delay); // wait for screen to wake up

                Robot robot = new Robot();

                // OPTIONAL: Open notepad or another command
                Runtime.getRuntime().exec("notepad");
                Thread.sleep(1000); // Give notepad time to open

                typeString(robot, config.password); // Type password
                robot.keyPress(KeyEvent.VK_ENTER);  // Press enter
                robot.keyRelease(KeyEvent.VK_ENTER);

                System.out.println("🔓 Unlock sequence executed");
                return true;

            } catch (Exception e) {
                System.err.println("❌ Unlock error: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

        System.out.println("❌ Invalid device or token");
        return false;
    }

    private void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            typeChar(robot, c);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {}
        }
    }

    private void typeChar(Robot robot, char c) {
        try {
            boolean upperCase = Character.isUpperCase(c);
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                System.err.println("Cannot type character: " + c);
                return;
            }

            if (upperCase) robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            if (upperCase) robot.keyRelease(KeyEvent.VK_SHIFT);
        } catch (Exception e) {
            System.err.println("Error typing char: " + c + " → " + e.getMessage());
        }
    }
}
