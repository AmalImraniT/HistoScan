package com.tissue.captioner.controller;

import com.tissue.captioner.service.AdminStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/statistics")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.11.123:8080"})
public class AdminStatisticsController {
    
    @Autowired
    private AdminStatisticsService adminStatisticsService;
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardStatistics() {
        try {
            Map<String, Object> stats = adminStatisticsService.getDashboardStatistics();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/top-users")
    public ResponseEntity<Map<String, Object>> getTopUsers(@RequestParam(defaultValue = "5") int limit) {
        try {
            Map<String, Object> result = Map.of(
                "topUsers", adminStatisticsService.getTopUsers(limit)
            );
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/monthly-trends")
    public ResponseEntity<Map<String, Object>> getMonthlyTrends() {
        try {
            Map<String, Object> trends = adminStatisticsService.getMonthlyTrends();
            return ResponseEntity.ok(trends);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/system-metrics")
    public ResponseEntity<Map<String, Object>> getSystemMetrics() {
        try {
            Map<String, Object> metrics = adminStatisticsService.getSystemMetrics();
            return ResponseEntity.ok(metrics);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/record")
    public ResponseEntity<Void> recordSystemStatistics() {
        try {
            adminStatisticsService.recordSystemStatistics();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

