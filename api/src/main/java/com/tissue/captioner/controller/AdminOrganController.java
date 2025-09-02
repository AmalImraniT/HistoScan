package com.tissue.captioner.controller;

import com.tissue.captioner.model.Organ;
import com.tissue.captioner.service.AdminOrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/organs")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.11.123:8080"})
public class AdminOrganController {
    
    @Autowired
    private AdminOrganService adminOrganService;
    
    @GetMapping
    public ResponseEntity<List<Organ>> getAllOrgans() {
        try {
            List<Organ> organs = adminOrganService.getAllOrgans();
            return ResponseEntity.ok(organs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Organ> getOrganById(@PathVariable Long id) {
        try {
            Organ organ = adminOrganService.getOrganById(id);
            return ResponseEntity.ok(organ);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Organ> createOrgan(@RequestBody Organ organ) {
        try {
            Organ createdOrgan = adminOrganService.createOrgan(organ);
            return ResponseEntity.ok(createdOrgan);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Organ> updateOrgan(@PathVariable Long id, @RequestBody Organ organDetails) {
        try {
            Organ updatedOrgan = adminOrganService.updateOrgan(id, organDetails);
            return ResponseEntity.ok(updatedOrgan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrgan(@PathVariable Long id) {
        try {
            adminOrganService.deleteOrgan(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Organ>> searchOrgans(@RequestParam String q) {
        try {
            List<Organ> organs = adminOrganService.searchOrgans(q);
            return ResponseEntity.ok(organs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Organ>> getOrgansByStatus(@PathVariable String status) {
        try {
            Organ.Status organStatus = Organ.Status.valueOf(status.toUpperCase());
            List<Organ> organs = adminOrganService.getOrgansByStatus(organStatus);
            return ResponseEntity.ok(organs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Organ>> getOrgansByCategory(@PathVariable String category) {
        try {
            List<Organ> organs = adminOrganService.getOrgansByCategory(category);
            return ResponseEntity.ok(organs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Organ>> getOrgansByPriority(@PathVariable String priority) {
        try {
            Organ.Priority organPriority = Organ.Priority.valueOf(priority.toUpperCase());
            List<Organ> organs = adminOrganService.getOrgansByPriority(organPriority);
            return ResponseEntity.ok(organs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/filters")
    public ResponseEntity<List<Organ>> getOrgansByFilters(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String priority) {
        try {
            Organ.Status organStatus = status != null ? Organ.Status.valueOf(status.toUpperCase()) : null;
            Organ.Priority organPriority = priority != null ? Organ.Priority.valueOf(priority.toUpperCase()) : null;
            
            List<Organ> organs = adminOrganService.getOrgansByFilters(organStatus, category, organPriority);
            return ResponseEntity.ok(organs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        try {
            List<String> categories = adminOrganService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getOrganStatistics() {
        try {
            Map<String, Object> stats = Map.of(
                "totalOrgans", adminOrganService.getTotalOrganCount(),
                "publishedOrgans", adminOrganService.getOrganCountByStatus(Organ.Status.PUBLISHED),
                "draftOrgans", adminOrganService.getOrganCountByStatus(Organ.Status.DRAFT),
                "archivedOrgans", adminOrganService.getOrganCountByStatus(Organ.Status.ARCHIVED)
            );
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/recent")
    public ResponseEntity<List<Organ>> getRecentModifiedOrgans() {
        try {
            List<Organ> organs = adminOrganService.getRecentModifiedOrgans();
            return ResponseEntity.ok(organs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

