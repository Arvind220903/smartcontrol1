package com.arvind.smartcontrol.controller;

import com.arvind.smartcontrol.service.UnlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UnlockController {

    @Autowired
    private UnlockService unlockService;

    @PostMapping("/unlock")
    public ResponseEntity<String> unlock(@RequestBody Map<String, String> request) {
        System.out.println("📥 Received unlock request: " + request);

        String device = request.get("device");
        String token = request.get("token");

        // ✅ Replace this with your real config check if needed
        if (!"Infinix".equals(device) || !"DefaultPassword".equals(token)) {
            System.out.println("❌ Rejected: device=" + device + ", token=" + token);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid device or token.");
        }

        System.out.println("✅ Accepted: Running unlock script...");

        boolean success = unlockService.runWindowsUnlockScript();

        if (success) {
            return ResponseEntity.ok("Unlocked Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unlock Failed");
        }
    }
}
