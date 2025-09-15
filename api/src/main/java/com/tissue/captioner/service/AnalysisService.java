package com.tissue.captioner.service;

import com.tissue.captioner.dto.AnalysisResultDto;
import com.tissue.captioner.model.AnalysisResult;
import com.tissue.captioner.model.User;
import com.tissue.captioner.repository.AnalysisResultRepository;
import com.tissue.captioner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;

@Service
public class AnalysisService {

    @Autowired
    private AnalysisResultRepository analysisResultRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    @Value("${app.ml.model.endpoint:http://localhost:5000/predict}")
    private String mlModelEndpoint;

    @Value("${app.ml.model.version:HistoScan v2.1}")
    private String mlModelVersion;

    // Analyser une image avec le modèle ML
    public AnalysisResultDto analyzeImage(MultipartFile imageFile, String username) throws Exception {
        // Vérifier que l'utilisateur existe
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Sauvegarder l'image
        String savedFilename = saveImage(imageFile, username);
        
        // Créer l'enregistrement d'analyse
        AnalysisResult analysisResult = new AnalysisResult();
        analysisResult.setFilename(savedFilename);
        analysisResult.setOriginalFilename(imageFile.getOriginalFilename());
        analysisResult.setUsername(username);
        analysisResult.setTimestamp(LocalDateTime.now());
        analysisResult.setStatus("processing");
        analysisResult.setMlModel(mlModelVersion);
        
        // Sauvegarder l'analyse
        AnalysisResult savedResult = analysisResultRepository.save(analysisResult);
        
        try {
            // Appeler le modèle ML pour l'analyse
            AnalysisResultDto mlResult = callMLModel(imageFile);
            
            // Mettre à jour avec les résultats du ML
            savedResult.setStatus("completed");
            savedResult.setConfidence(mlResult.getConfidence());
            savedResult.setDescription(mlResult.getDescription());
            savedResult.setOrgan(mlResult.getOrgan());
            savedResult.setTissueType(mlResult.getTissueType());
            savedResult.setAbnormalities(mlResult.getAbnormalities());
            savedResult.setRecommendations(mlResult.getRecommendations());
            savedResult.setProcessingTime(mlResult.getProcessingTime());
            savedResult.setImageHash(generateImageHash(imageFile));
            savedResult.setAnalysisVersion(mlModelVersion);
            
            // Sauvegarder les résultats complets
            AnalysisResult finalResult = analysisResultRepository.save(savedResult);
            
            return convertToDto(finalResult);
            
        } catch (Exception e) {
            // En cas d'erreur, marquer comme échoué
            savedResult.setStatus("failed");
            savedResult.setTechnicalNotes("Erreur lors de l'analyse ML: " + e.getMessage());
            analysisResultRepository.save(savedResult);
            throw e;
        }
    }

    // Appeler le modèle ML
    private AnalysisResultDto callMLModel(MultipartFile imageFile) throws Exception {
        // TODO: Implémenter l'appel réel au modèle ML
        // Pour l'instant, simulation avec des données de démonstration
        
        // Simuler un délai de traitement
        Thread.sleep(2000);
        
        // Générer des résultats simulés basés sur le nom du fichier
        String filename = imageFile.getOriginalFilename().toLowerCase();
        
        if (filename.contains("brain") || filename.contains("cerebral")) {
            return generateBrainAnalysisResult();
        } else if (filename.contains("liver") || filename.contains("hepatic")) {
            return generateLiverAnalysisResult();
        } else if (filename.contains("kidney") || filename.contains("renal")) {
            return generateKidneyAnalysisResult();
        } else if (filename.contains("heart") || filename.contains("cardiac")) {
            return generateHeartAnalysisResult();
        } else if (filename.contains("lung") || filename.contains("pulmonary")) {
            return generateLungAnalysisResult();
        } else {
            return generateGenericAnalysisResult();
        }
    }

    // Résultats simulés pour différents organes
    private AnalysisResultDto generateBrainAnalysisResult() {
        AnalysisResultDto result = new AnalysisResultDto();
        result.setConfidence(92.5);
        result.setDescription("Tissu cérébral avec architecture neuronale préservée. Les cellules gliales sont bien distribuées et la vascularisation est normale. Quelques zones d'œdème léger observées dans la substance blanche.");
        result.setOrgan("Cerveau");
        result.setTissueType("Tissu nerveux");
        result.setAbnormalities(Arrays.asList("Œdème léger", "Inflammation péri-vasculaire modérée"));
        result.setRecommendations(Arrays.asList("Surveillance recommandée. Contrôle dans 3 mois. Évaluation clinique pour les symptômes neurologiques."));
        result.setProcessingTime("2.1s");
        result.setMlModel(mlModelVersion);
        result.setRiskLevel("Faible");
        result.setUrgencyLevel("Normale");
        result.setDifferentialDiagnosis("Encéphalite légère, Œdème cérébral, Ischémie cérébrale");
        result.setHistopathologicalFeatures("Architecture neuronale préservée, Œdème périvasculaire, Infiltration gliale modérée");
        result.setPrognosis("Favorable avec traitement approprié");
        return result;
    }

    private AnalysisResultDto generateLiverAnalysisResult() {
        AnalysisResultDto result = new AnalysisResultDto();
        result.setConfidence(96.8);
        result.setDescription("Tissu hépatique normal avec architecture lobulaire préservée. Les cellules hépatiques sont bien organisées, la vascularisation est normale et les espaces portes sont bien définis.");
        result.setOrgan("Foie");
        result.setTissueType("Tissu digestif");
        result.setAbnormalities(new ArrayList<>());
        result.setRecommendations(Arrays.asList("Aucune action requise. Tissu normal."));
        result.setProcessingTime("1.9s");
        result.setMlModel(mlModelVersion);
        result.setRiskLevel("Aucun");
        result.setUrgencyLevel("Aucune");
        result.setDifferentialDiagnosis("Foie normal, Stéatose hépatique légère");
        result.setHistopathologicalFeatures("Architecture lobulaire normale, Cellules hépatiques bien organisées, Vascularisation normale");
        result.setPrognosis("Excellent");
        return result;
    }

    private AnalysisResultDto generateKidneyAnalysisResult() {
        AnalysisResultDto result = new AnalysisResultDto();
        result.setConfidence(89.3);
        result.setDescription("Tissu rénal avec signes de glomérulonéphrite chronique. Fibrose interstitielle modérée observée. Les glomérules montrent une sclérose focale.");
        result.setOrgan("Rein");
        result.setTissueType("Tissu urinaire");
        result.setAbnormalities(Arrays.asList("Glomérulonéphrite chronique", "Fibrose interstitielle", "Sclérose glomérulaire"));
        result.setRecommendations(Arrays.asList("Consultation néphrologique recommandée. Biopsie de contrôle dans 6 mois. Surveillance de la fonction rénale."));
        result.setProcessingTime("2.4s");
        result.setMlModel(mlModelVersion);
        result.setRiskLevel("Modéré");
        result.setUrgencyLevel("Élevée");
        result.setDifferentialDiagnosis("Glomérulonéphrite chronique, Néphropathie diabétique, Néphropathie hypertensive");
        result.setHistopathologicalFeatures("Sclérose glomérulaire, Fibrose interstitielle, Atrophie tubulaire");
        result.setPrognosis("Réservé");
        return result;
    }

    private AnalysisResultDto generateHeartAnalysisResult() {
        AnalysisResultDto result = new AnalysisResultDto();
        result.setConfidence(94.2);
        result.setDescription("Tissu cardiaque avec hypertrophie ventriculaire gauche modérée. Les cardiomyocytes montrent une organisation normale. Fibrose interstitielle légère observée.");
        result.setOrgan("Cœur");
        result.setTissueType("Tissu musculaire");
        result.setAbnormalities(Arrays.asList("Hypertrophie ventriculaire gauche", "Fibrose interstitielle légère"));
        result.setRecommendations(Arrays.asList("Évaluation cardiologique complète. Surveillance de la fonction cardiaque. Contrôle dans 6 mois."));
        result.setProcessingTime("2.0s");
        result.setMlModel(mlModelVersion);
        result.setRiskLevel("Modéré");
        result.setUrgencyLevel("Modérée");
        result.setDifferentialDiagnosis("Cardiomyopathie hypertrophique, Hypertension artérielle, Cardiomyopathie ischémique");
        result.setHistopathologicalFeatures("Hypertrophie cardiomyocytaire, Fibrose interstitielle, Architecture normale");
        result.setPrognosis("Stable avec traitement");
        return result;
    }

    private AnalysisResultDto generateLungAnalysisResult() {
        AnalysisResultDto result = new AnalysisResultDto();
        result.setConfidence(91.7);
        result.setDescription("Tissu pulmonaire avec emphysème centrolobulaire modéré. Les alvéoles sont dilatées et certaines parois alvéolaires sont rompues. Inflammation chronique légère.");
        result.setOrgan("Poumon");
        result.setTissueType("Tissu respiratoire");
        result.setAbnormalities(Arrays.asList("Emphysème centrolobulaire", "Destruction alvéolaire", "Inflammation chronique"));
        result.setRecommendations(Arrays.asList("Évaluation pneumologique. Arrêt du tabac impératif. Réhabilitation respiratoire recommandée."));
        result.setProcessingTime("2.3s");
        result.setMlModel(mlModelVersion);
        result.setRiskLevel("Élevé");
        result.setUrgencyLevel("Modérée");
        result.setDifferentialDiagnosis("BPCO, Emphysème, Bronchite chronique");
        result.setHistopathologicalFeatures("Destruction alvéolaire, Dilatation des espaces aériens, Inflammation chronique");
        result.setPrognosis("Progression lente avec traitement");
        return result;
    }

    private AnalysisResultDto generateGenericAnalysisResult() {
        AnalysisResultDto result = new AnalysisResultDto();
        result.setConfidence(87.5);
        result.setDescription("Tissu analysé avec architecture générale préservée. Quelques anomalies mineures détectées nécessitant une évaluation plus approfondie.");
        result.setOrgan("Non spécifié");
        result.setTissueType("Tissu non spécifié");
        result.setAbnormalities(Arrays.asList("Anomalies mineures", "Nécessite évaluation approfondie"));
        result.setRecommendations(Arrays.asList("Évaluation histopathologique complémentaire recommandée. Consultation spécialisée."));
        result.setProcessingTime("2.5s");
        result.setMlModel(mlModelVersion);
        result.setRiskLevel("Inconnu");
        result.setUrgencyLevel("Modérée");
        result.setDifferentialDiagnosis("À déterminer par évaluation complémentaire");
        result.setHistopathologicalFeatures("Architecture générale préservée, Anomalies mineures");
        result.setPrognosis("À déterminer");
        return result;
    }

    // Sauvegarder l'image
    private String saveImage(MultipartFile imageFile, String username) throws IOException {
        // Créer le répertoire de destination
        Path uploadPath = Paths.get(uploadDir, username);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Générer un nom de fichier unique
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String originalFilename = imageFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = timestamp + "_" + originalFilename;

        // Sauvegarder le fichier
        Path filePath = uploadPath.resolve(newFilename);
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return newFilename;
    }

    // Générer un hash de l'image
    private String generateImageHash(MultipartFile imageFile) throws IOException {
        // Hash simple basé sur la taille et le nom du fichier
        // En production, utiliser un algorithme de hash plus robuste
        return String.valueOf(imageFile.getSize()) + "_" + imageFile.getOriginalFilename().hashCode();
    }

    // Récupérer l'historique des analyses
    public List<AnalysisResultDto> getAnalysisHistory(String username) {
        List<AnalysisResult> results = analysisResultRepository.findByUsernameOrderByTimestampDesc(username);
        return results.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    // Récupérer une analyse par ID
    public AnalysisResultDto getAnalysisById(Long id, String username) {
        Optional<AnalysisResult> result = analysisResultRepository.findByIdAndUsername(id, username);
        return result.map(this::convertToDto).orElse(null);
    }

    // Supprimer une analyse
    public boolean deleteAnalysis(Long id, String username) {
        Optional<AnalysisResult> result = analysisResultRepository.findByIdAndUsername(id, username);
        if (result.isPresent()) {
            analysisResultRepository.delete(result.get());
            return true;
        }
        return false;
    }

    // Générer un rapport
    public Resource generateReport(Long id, String username) throws Exception {
        // TODO: Implémenter la génération de rapport PDF
        // Pour l'instant, retourner null
        return null;
    }

    // Partager une analyse
    public String shareAnalysis(Long id, String username, Map<String, String> shareData) {
        // TODO: Implémenter le partage d'analyse
        return "https://histoscan.com/share/" + id;
    }

    // Obtenir les statistiques
    public Map<String, Object> getAnalysisStatistics(String username) {
        List<AnalysisResult> results = analysisResultRepository.findByUsername(username);
        
        long totalAnalyses = results.size();
        long completedAnalyses = results.stream().filter(r -> "completed".equals(r.getStatus())).count();
        long failedAnalyses = results.stream().filter(r -> "failed".equals(r.getStatus())).count();
        
        // Statistiques par organe
        Map<String, Long> organStats = results.stream()
            .filter(r -> r.getOrgan() != null)
            .collect(Collectors.groupingBy(AnalysisResult::getOrgan, Collectors.counting()));
        
        // Score de confiance moyen
        double avgConfidence = results.stream()
            .filter(r -> r.getConfidence() != null)
            .mapToDouble(AnalysisResult::getConfidence)
            .average()
            .orElse(0.0);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalAnalyses", totalAnalyses);
        stats.put("completedAnalyses", completedAnalyses);
        stats.put("failedAnalyses", failedAnalyses);
        stats.put("organStats", organStats);
        stats.put("averageConfidence", Math.round(avgConfidence * 100.0) / 100.0);
        
        return stats;
    }

    // Rechercher dans l'historique
    public List<AnalysisResultDto> searchAnalysisHistory(String username, String query) {
        List<AnalysisResult> results = analysisResultRepository.findByUsernameAndDescriptionContainingIgnoreCase(username, query);
        return results.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    // Filtrer par organe
    public List<AnalysisResultDto> getAnalysisByOrgan(String username, String organ) {
        List<AnalysisResult> results = analysisResultRepository.findByUsernameAndOrgan(username, organ);
        return results.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    // Filtrer par date
    public List<AnalysisResultDto> getAnalysisByDateRange(String username, String start, String end) {
        // TODO: Implémenter le filtrage par date
        return getAnalysisHistory(username);
    }

    // Récupérer l'image d'une analyse
    public Resource getAnalysisImage(Long id, String username) throws Exception {
        Optional<AnalysisResult> result = analysisResultRepository.findByIdAndUsername(id, username);
        if (result.isPresent()) {
            Path imagePath = Paths.get(uploadDir, username, result.get().getFilename());
            if (Files.exists(imagePath)) {
                return new UrlResource(imagePath.toUri());
            }
        }
        return null;
    }

    // Obtenir le statut d'une analyse
    public String getAnalysisStatus(Long id, String username) {
        Optional<AnalysisResult> result = analysisResultRepository.findByIdAndUsername(id, username);
        return result.map(AnalysisResult::getStatus).orElse("not_found");
    }

    // Convertir l'entité en DTO
    private AnalysisResultDto convertToDto(AnalysisResult result) {
        AnalysisResultDto dto = new AnalysisResultDto();
        dto.setId(result.getId());
        dto.setFilename(result.getFilename());
        dto.setOriginalFilename(result.getOriginalFilename());
        dto.setImageUrl("/api/analysis/" + result.getId() + "/image");
        dto.setTimestamp(result.getTimestamp());
        dto.setStatus(result.getStatus());
        dto.setConfidence(result.getConfidence());
        dto.setDescription(result.getDescription());
        dto.setOrgan(result.getOrgan());
        dto.setTissueType(result.getTissueType());
        dto.setAbnormalities(result.getAbnormalities());
        dto.setRecommendations(result.getRecommendations());
        dto.setProcessingTime(result.getProcessingTime());
        dto.setMlModel(result.getMlModel());
        dto.setUsername(result.getUsername());
        dto.setImageHash(result.getImageHash());
        dto.setAnalysisVersion(result.getAnalysisVersion());
        dto.setTechnicalNotes(result.getTechnicalNotes());
        dto.setQualityScore(result.getQualityScore());
        dto.setMagnification(result.getMagnification());
        dto.setStainingMethod(result.getStainingMethod());
        dto.setSampleSource(result.getSampleSource());
        dto.setPathologistNotes(result.getPathologistNotes());
        dto.setFollowUpRecommendations(result.getFollowUpRecommendations());
        dto.setRiskLevel(result.getRiskLevel());
        dto.setUrgencyLevel(result.getUrgencyLevel());
        dto.setDifferentialDiagnosis(result.getDifferentialDiagnosis());
        dto.setHistopathologicalFeatures(result.getHistopathologicalFeatures());
        dto.setMolecularMarkers(result.getMolecularMarkers());
        dto.setGeneticMutations(result.getGeneticMutations());
        dto.setTreatmentSuggestions(result.getTreatmentSuggestions());
        dto.setPrognosis(result.getPrognosis());
        dto.setReferences(result.getReferences());
        dto.setValidationStatus(result.getValidationStatus());
        dto.setPeerReviewStatus(result.getPeerReviewStatus());
        dto.setClinicalCorrelation(result.getClinicalCorrelation());
        dto.setResearchNotes(result.getResearchNotes());
        
        return dto;
    }
}
