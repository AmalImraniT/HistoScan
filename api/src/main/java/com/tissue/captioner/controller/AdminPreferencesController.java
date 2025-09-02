package com.tissue.captioner.controller;

import com.tissue.captioner.service.AdminPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/preferences")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.11.123:8080"})
public class AdminPreferencesController {
    
    @Autowired
    private AdminPreferencesService adminPreferencesService;
    
    @GetMapping
    public ResponseEntity<Map<String, String>> getAllPreferences() {
        try {
            Map<String, String> preferences = adminPreferencesService.getAllPreferences();
            return ResponseEntity.ok(preferences);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/{key}")
    public ResponseEntity<String> getPreference(@PathVariable String key) {
        try {
            String value = adminPreferencesService.getPreference(key);
            if (value != null) {
                return ResponseEntity.ok(value);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<Map<String, String>> getPreferencesByCategory(@PathVariable String category) {
        try {
            Map<String, String> preferences = adminPreferencesService.getPreferencesByCategory(category);
            return ResponseEntity.ok(preferences);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/{key}")
    public ResponseEntity<Map<String, String>> setPreference(
            @PathVariable String key,
            @RequestBody Map<String, String> request) {
        try {
            String value = request.get("value");
            String description = request.getOrDefault("description", "");
            String category = request.getOrDefault("category", "general");
            
            adminPreferencesService.setPreference(key, value, description, category);
            
            Map<String, String> result = Map.of("key", key, "value", value, "status", "updated");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/batch")
    public ResponseEntity<Map<String, String>> setMultiplePreferences(@RequestBody Map<String, String> preferences) {
        try {
            adminPreferencesService.setMultiplePreferences(preferences);
            return ResponseEntity.ok(Map.of("status", "updated", "count", String.valueOf(preferences.size())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @DeleteMapping("/{key}")
    public ResponseEntity<Void> deletePreference(@PathVariable String key) {
        try {
            adminPreferencesService.deletePreference(key);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/initialize")
    public ResponseEntity<Map<String, String>> initializeDefaultPreferences() {
        try {
            adminPreferencesService.initializeDefaultPreferences();
            return ResponseEntity.ok(Map.of("status", "initialized"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/structure")
    public ResponseEntity<Map<String, Object>> getPreferencesStructure() {
        try {
            Map<String, Object> structure = adminPreferencesService.getPreferencesStructure();
            return ResponseEntity.ok(structure);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

