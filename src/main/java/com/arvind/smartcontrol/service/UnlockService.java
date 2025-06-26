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

    // ✅ Option 1: Use AHK Script to unlock Windows
    public boolean runWindowsUnlockScript() {
        try {
           // 📌 Your script path
            Runtime.getRuntime().exec(new String[] {
            	    "C:\\Program Files\\AutoHotkey\\AutoHotkey.exe",
            	    "C:\\smartcontrol\\scripts\\unlock.ahk"
            	});

            System.out.println("🚀 AHK script executed.");
            return true;
        } catch (Exception e) {
            System.err.println("❌ Failed to run AHK script: " + e.getMessage());
            return false;
        }
    }

    // ✅ Option 2: Simulate typing (legacy fallback method)
    public boolean verifyAndUnlock(String deviceId, String token) {
        if (config.deviceId.equals(deviceId) && config.token.equals(token)) {
            System.out.println("✅ Verified device and token");

            try {
                Thread.sleep(config.delay); // wait for screen to wake up

                Robot robot = new Robot();

                // Open Notepad (or skip this if doing real unlock)
                Runtime.getRuntime().exec("notepad");
                Thread.sleep(1000); // Give Notepad time to open

                typeString(robot, config.password);
                robot.keyPress(KeyEvent.VK_ENTER);
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

    // 🔠 Helper method to type full strings
    private void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            typeChar(robot, c);
            try {
                Thread.sleep(50); // slight delay between keystrokes
            } catch (InterruptedException ignored) {}
        }
    }

    // ⌨️ Helper method to type one character
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
