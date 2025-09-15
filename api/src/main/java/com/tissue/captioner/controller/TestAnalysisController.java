package com.tissue.captioner.controller;

import com.tissue.captioner.dto.AnalysisResultDto;
import com.tissue.captioner.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

@RestController
@RequestMapping("/api/test/analysis")
@CrossOrigin(origins = "*")
public class TestAnalysisController {

    @Autowired
    private AnalysisService analysisService;

    // Analyser une image avec le modèle ML (sans authentification)
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

            // Utiliser un nom d'utilisateur de test
            String testUsername = "test_user";

            // Analyser l'image
            AnalysisResultDto result = analysisService.analyzeImage(imageFile, testUsername);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Récupérer l'historique des analyses (sans authentification)
    @GetMapping("/history")
    public ResponseEntity<List<AnalysisResultDto>> getAnalysisHistory() {
        try {
            // Retourner des données de démonstration
            List<AnalysisResultDto> demoHistory = List.of(
                createDemoAnalysis(1L, "brain-sample-001.jpg", "Cerveau", 92.1),
                createDemoAnalysis(2L, "liver-tissue-002.jpg", "Foie", 96.7),
                createDemoAnalysis(3L, "kidney-biopsy-003.jpg", "Rein", 89.3)
            );
            return ResponseEntity.ok(demoHistory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Récupérer une analyse par ID (sans authentification)
    @GetMapping("/{id}")
    public ResponseEntity<AnalysisResultDto> getAnalysisById(@PathVariable Long id) {
        try {
            AnalysisResultDto demoAnalysis = createDemoAnalysis(id, "sample-" + id + ".jpg", "Organe " + id, 90.0);
            return ResponseEntity.ok(demoAnalysis);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Supprimer une analyse (sans authentification)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        try {
            // Simulation de suppression
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Télécharger le rapport (sans authentification)
    @GetMapping("/{id}/report")
    public ResponseEntity<Map<String, String>> downloadReport(@PathVariable Long id) {
        try {
            // Simulation de rapport
            Map<String, String> report = Map.of(
                "message", "Rapport simulé pour l'analyse " + id,
                "status", "success"
            );
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Obtenir les statistiques (sans authentification)
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getAnalysisStatistics() {
        try {
            Map<String, Object> stats = Map.of(
                "totalAnalyses", 3,
                "completedAnalyses", 3,
                "failedAnalyses", 0,
                "averageConfidence", 92.7
            );
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Créer une analyse de démonstration
    private AnalysisResultDto createDemoAnalysis(Long id, String filename, String organ, Double confidence) {
        AnalysisResultDto analysis = new AnalysisResultDto();
        analysis.setId(id);
        analysis.setFilename(filename);
        analysis.setOriginalFilename(filename);
        analysis.setImageUrl("/api/test/analysis/" + id + "/image");
        analysis.setTimestamp(java.time.LocalDateTime.now());
        analysis.setStatus("completed");
        analysis.setConfidence(confidence);
        analysis.setDescription("Tissu " + organ.toLowerCase() + " analysé avec succès. Architecture tissulaire normale observée avec quelques variations mineures.");
        analysis.setOrgan(organ);
        analysis.setTissueType("Tissu " + organ.toLowerCase());
        analysis.setAbnormalities(Arrays.asList("Variations mineures", "Architecture normale"));
        analysis.setRecommendations(Arrays.asList("Surveillance recommandée. Contrôle dans 6 mois."));
        analysis.setProcessingTime("2.1s");
        analysis.setMlModel("HistoScan v2.1");
        analysis.setUsername("test_user");
        analysis.setImageHash("hash_" + id);
        analysis.setAnalysisVersion("v2.1");
        analysis.setRiskLevel("Faible");
        analysis.setUrgencyLevel("Normale");
        analysis.setDifferentialDiagnosis("Tissu normal, Variations mineures");
        analysis.setHistopathologicalFeatures("Architecture normale, Cellules bien organisées");
        analysis.setPrognosis("Excellent");
        
        return analysis;
    }
}
