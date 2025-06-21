package com.arvind.smartcontrol.controller;

import com.arvind.smartcontrol.dto.UnlockRequest;
import com.arvind.smartcontrol.service.UnlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UnlockController {

    @Autowired
    private UnlockService unlockService;

    @PostMapping("/unlock")
    public ResponseEntity<String> unlock(@RequestBody UnlockRequest request) {
        boolean success = unlockService.verifyAndUnlock(request.getDeviceId(), request.getToken());
        if (success) {
            return ResponseEntity.ok("Laptop unlocked!");
        } else {
            return ResponseEntity.status(403).body("Invalid device or token.");
        }
    }
}
