package com.tissue.captioner.controller;

import com.tissue.captioner.dto.AnalysisResultDto;
import com.tissue.captioner.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
@CrossOrigin(origins = "*")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    // Analyser une image avec le modèle ML
    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResultDto> analyzeImage(@RequestParam("image") MultipartFile imageFile) {
        try {
            // Vérifier le type de fichier
            if (!imageFile.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().build();
            }

            // Vérifier la taille du fichier (max 10MB)
            if (imageFile.getSize() > 10 * 1024 * 1024) {
                return ResponseEntity.badRequest().build();
            }

            // Récupérer l'utilisateur connecté
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // Analyser l'image
            AnalysisResultDto result = analysisService.analyzeImage(imageFile, username);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Récupérer l'historique des analyses de l'utilisateur
    @GetMapping("/history")
    public ResponseEntity<List<AnalysisResultDto>> getAnalysisHistory() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            List<AnalysisResultDto> history = analysisService.getAnalysisHistory(username);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Récupérer une analyse spécifique par ID
    @GetMapping("/{id}")
    public ResponseEntity<AnalysisResultDto> getAnalysisById(@PathVariable Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            AnalysisResultDto analysis = analysisService.getAnalysisById(id, username);
            if (analysis != null) {
                return ResponseEntity.ok(analysis);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Supprimer une analyse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            boolean deleted = analysisService.deleteAnalysis(id, username);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Télécharger le rapport d'analyse
    @GetMapping("/{id}/report")
    public ResponseEntity<Resource> downloadReport(@PathVariable Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            Resource report = analysisService.generateReport(id, username);
            if (report != null) {
                return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"rapport-analyse-" + id + ".pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(report);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Partager une analyse
    @PostMapping("/{id}/share")
    public ResponseEntity<Map<String, String>> shareAnalysis(@PathVariable Long id, @RequestBody Map<String, String> shareData) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            String shareUrl = analysisService.shareAnalysis(id, username, shareData);
            return ResponseEntity.ok(Map.of("shareUrl", shareUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Obtenir les statistiques d'analyse de l'utilisateur
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getAnalysisStatistics() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            Map<String, Object> statistics = analysisService.getAnalysisStatistics(username);
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Rechercher dans l'historique des analyses
    @GetMapping("/search")
    public ResponseEntity<List<AnalysisResultDto>> searchAnalysisHistory(@RequestParam String q) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            List<AnalysisResultDto> results = analysisService.searchAnalysisHistory(username, q);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Filtrer les analyses par organe
    @GetMapping("/organ/{organ}")
    public ResponseEntity<List<AnalysisResultDto>> getAnalysisByOrgan(@PathVariable String organ) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            List<AnalysisResultDto> results = analysisService.getAnalysisByOrgan(username, organ);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Filtrer les analyses par date
    @GetMapping("/date-range")
    public ResponseEntity<List<AnalysisResultDto>> getAnalysisByDateRange(
            @RequestParam String start, 
            @RequestParam String end) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            List<AnalysisResultDto> results = analysisService.getAnalysisByDateRange(username, start, end);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Récupérer l'image d'une analyse
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getAnalysisImage(@PathVariable Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            Resource image = analysisService.getAnalysisImage(id, username);
            if (image != null) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Vérifier le statut d'une analyse en cours
    @GetMapping("/{id}/status")
    public ResponseEntity<Map<String, String>> getAnalysisStatus(@PathVariable Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            String status = analysisService.getAnalysisStatus(id, username);
            return ResponseEntity.ok(Map.of("status", status));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
