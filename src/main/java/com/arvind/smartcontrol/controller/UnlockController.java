package com.arvind.smartcontrol.controller;

import com.arvind.smartcontrol.dto.UnlockRequest;
import com.arvind.smartcontrol.service.UnlockService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UnlockController {

    @PostMapping("/unlock")
    public ResponseEntity<String> unlock(@RequestBody Map<String, String> request) {
        System.out.println("📥 Received unlock request: " + request);

        String device = request.get("device");
        String token = request.get("token");

        if (!"Infinix".equals(device) || !"DefaultPassword".equals(token)) {
            System.out.println("❌ Rejected: device=" + device + ", token=" + token);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid device or token.");
        }

        System.out.println("✅ Accepted: Unlocking laptop...");
        // TODO: Add your unlock logic here

        return ResponseEntity.ok("Unlocked Successfully");
    }
}

