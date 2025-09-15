package com.tissue.captioner.repository;

import com.tissue.captioner.model.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {
    
    // Récupérer toutes les analyses d'un utilisateur, triées par date décroissante
    List<AnalysisResult> findByUsernameOrderByTimestampDesc(String username);
    
    // Récupérer toutes les analyses d'un utilisateur
    List<AnalysisResult> findByUsername(String username);
    
    // Récupérer une analyse par ID et nom d'utilisateur
    Optional<AnalysisResult> findByIdAndUsername(Long id, String username);
    
    // Récupérer les analyses d'un utilisateur par statut
    List<AnalysisResult> findByUsernameAndStatus(String username, String status);
    
    // Récupérer les analyses d'un utilisateur par organe
    List<AnalysisResult> findByUsernameAndOrgan(String username, String organ);
    
    // Récupérer les analyses d'un utilisateur par type de tissu
    List<AnalysisResult> findByUsernameAndTissueType(String username, String tissueType);
    
    // Rechercher dans les descriptions des analyses d'un utilisateur
    List<AnalysisResult> findByUsernameAndDescriptionContainingIgnoreCase(String username, String description);
    
    // Récupérer les analyses d'un utilisateur dans une plage de dates
    @Query("SELECT a FROM AnalysisResult a WHERE a.username = :username AND a.timestamp BETWEEN :startDate AND :endDate ORDER BY a.timestamp DESC")
    List<AnalysisResult> findByUsernameAndTimestampBetween(@Param("username") String username, 
                                                          @Param("startDate") LocalDateTime startDate, 
                                                          @Param("endDate") LocalDateTime endDate);
    
    // Récupérer les analyses d'un utilisateur avec un score de confiance minimum
    List<AnalysisResult> findByUsernameAndConfidenceGreaterThanEqual(String username, Double minConfidence);
    
    // Récupérer les analyses d'un utilisateur avec des anomalies
    @Query("SELECT a FROM AnalysisResult a WHERE a.username = :username AND SIZE(a.abnormalities) > 0 ORDER BY a.timestamp DESC")
    List<AnalysisResult> findByUsernameWithAbnormalities(@Param("username") String username);
    
    // Récupérer les analyses d'un utilisateur sans anomalies
    @Query("SELECT a FROM AnalysisResult a WHERE a.username = :username AND (a.abnormalities IS NULL OR SIZE(a.abnormalities) = 0) ORDER BY a.timestamp DESC")
    List<AnalysisResult> findByUsernameWithoutAbnormalities(@Param("username") String username);
    
    // Compter le nombre total d'analyses d'un utilisateur
    long countByUsername(String username);
    
    // Compter le nombre d'analyses d'un utilisateur par statut
    long countByUsernameAndStatus(String username, String status);
    
    // Compter le nombre d'analyses d'un utilisateur par organe
    long countByUsernameAndOrgan(String username, String organ);
    
    // Récupérer les analyses les plus récentes d'un utilisateur
    List<AnalysisResult> findTop5ByUsernameOrderByTimestampDesc(String username);
    
    // Récupérer les analyses avec le score de confiance le plus élevé d'un utilisateur
    List<AnalysisResult> findTop5ByUsernameOrderByConfidenceDesc(String username);
    
    // Récupérer les analyses d'un utilisateur par modèle ML
    List<AnalysisResult> findByUsernameAndMlModel(String username, String mlModel);
    
    // Récupérer les analyses d'un utilisateur par version d'analyse
    List<AnalysisResult> findByUsernameAndAnalysisVersion(String username, String analysisVersion);
    
    // Rechercher par nom de fichier original
    List<AnalysisResult> findByUsernameAndOriginalFilenameContainingIgnoreCase(String username, String filename);
    
    // Récupérer les analyses d'un utilisateur par niveau de risque
    List<AnalysisResult> findByUsernameAndRiskLevel(String username, String riskLevel);
    
    // Récupérer les analyses d'un utilisateur par niveau d'urgence
    List<AnalysisResult> findByUsernameAndUrgencyLevel(String username, String urgencyLevel);
    
    // Récupérer les analyses d'un utilisateur par méthode de coloration
    List<AnalysisResult> findByUsernameAndStainingMethod(String username, String stainingMethod);
    
    // Récupérer les analyses d'un utilisateur par source d'échantillon
    List<AnalysisResult> findByUsernameAndSampleSource(String username, String sampleSource);
    
    // Récupérer les analyses d'un utilisateur par statut de validation
    List<AnalysisResult> findByUsernameAndValidationStatus(String username, String validationStatus);
    
    // Récupérer les analyses d'un utilisateur par statut de revue par les pairs
    List<AnalysisResult> findByUsernameAndPeerReviewStatus(String username, String peerReviewStatus);
    
    // Recherche avancée avec plusieurs critères
    @Query("SELECT a FROM AnalysisResult a WHERE a.username = :username " +
           "AND (:organ IS NULL OR a.organ = :organ) " +
           "AND (:status IS NULL OR a.status = :status) " +
           "AND (:minConfidence IS NULL OR a.confidence >= :minConfidence) " +
           "AND (:startDate IS NULL OR a.timestamp >= :startDate) " +
           "AND (:endDate IS NULL OR a.timestamp <= :endDate) " +
           "ORDER BY a.timestamp DESC")
    List<AnalysisResult> findAdvancedSearch(@Param("username") String username,
                                           @Param("organ") String organ,
                                           @Param("status") String status,
                                           @Param("minConfidence") Double minConfidence,
                                           @Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);
    
    // Statistiques par organe pour un utilisateur
    @Query("SELECT a.organ, COUNT(a) FROM AnalysisResult a WHERE a.username = :username GROUP BY a.organ")
    List<Object[]> getOrganStatisticsByUsername(@Param("username") String username);
    
    // Statistiques par statut pour un utilisateur
    @Query("SELECT a.status, COUNT(a) FROM AnalysisResult a WHERE a.username = :username GROUP BY a.status")
    List<Object[]> getStatusStatisticsByUsername(@Param("username") String username);
    
    // Score de confiance moyen par organe pour un utilisateur
    @Query("SELECT a.organ, AVG(a.confidence) FROM AnalysisResult a WHERE a.username = :username AND a.confidence IS NOT NULL GROUP BY a.organ")
    List<Object[]> getAverageConfidenceByOrgan(@Param("username") String username);
    
    // Récupérer les analyses avec des notes de pathologiste
    @Query("SELECT a FROM AnalysisResult a WHERE a.username = :username AND a.pathologistNotes IS NOT NULL AND a.pathologistNotes != '' ORDER BY a.timestamp DESC")
    List<AnalysisResult> findByUsernameWithPathologistNotes(@Param("username") String username);
    
    // Récupérer les analyses nécessitant un suivi
    @Query("SELECT a FROM AnalysisResult a WHERE a.username = :username AND a.followUpRecommendations IS NOT NULL AND a.followUpRecommendations != '' ORDER BY a.timestamp DESC")
    List<AnalysisResult> findByUsernameNeedingFollowUp(@Param("username") String username);
}
