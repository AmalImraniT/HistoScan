package com.tissue.captioner.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "analysis_results")
public class AnalysisResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String filename;
    
    @Column(name = "original_filename")
    private String originalFilename;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @Column(nullable = false)
    private String status;
    
    private Double confidence;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String organ;
    
    @Column(name = "tissue_type")
    private String tissueType;
    
    @ElementCollection
    @CollectionTable(name = "analysis_abnormalities", joinColumns = @JoinColumn(name = "analysis_id"))
    @Column(name = "abnormality")
    private List<String> abnormalities;
    
    @ElementCollection
    @CollectionTable(name = "analysis_recommendations", joinColumns = @JoinColumn(name = "analysis_id"))
    @Column(name = "recommendation")
    private List<String> recommendations;
    
    @Column(name = "processing_time")
    private String processingTime;
    
    @Column(name = "ml_model")
    private String mlModel;
    
    @Column(name = "image_hash")
    private String imageHash;
    
    @Column(name = "analysis_version")
    private String analysisVersion;
    
    @Column(name = "technical_notes", columnDefinition = "TEXT")
    private String technicalNotes;
    
    @Column(name = "quality_score")
    private String qualityScore;
    
    private String magnification;
    
    @Column(name = "staining_method")
    private String stainingMethod;
    
    @Column(name = "sample_source")
    private String sampleSource;
    
    @Column(name = "pathologist_notes", columnDefinition = "TEXT")
    private String pathologistNotes;
    
    @Column(name = "follow_up_recommendations", columnDefinition = "TEXT")
    private String followUpRecommendations;
    
    @Column(name = "risk_level")
    private String riskLevel;
    
    @Column(name = "urgency_level")
    private String urgencyLevel;
    
    @Column(name = "differential_diagnosis", columnDefinition = "TEXT")
    private String differentialDiagnosis;
    
    @Column(name = "histopathological_features", columnDefinition = "TEXT")
    private String histopathologicalFeatures;
    
    @Column(name = "molecular_markers", columnDefinition = "TEXT")
    private String molecularMarkers;
    
    @Column(name = "genetic_mutations", columnDefinition = "TEXT")
    private String geneticMutations;
    
    @Column(name = "treatment_suggestions", columnDefinition = "TEXT")
    private String treatmentSuggestions;
    
    private String prognosis;
    
    @Column(columnDefinition = "TEXT")
    private String references;
    
    @Column(name = "validation_status")
    private String validationStatus;
    
    @Column(name = "peer_review_status")
    private String peerReviewStatus;
    
    @Column(name = "clinical_correlation", columnDefinition = "TEXT")
    private String clinicalCorrelation;
    
    @Column(name = "research_notes", columnDefinition = "TEXT")
    private String researchNotes;
    
    // Constructeurs
    public AnalysisResult() {
        this.timestamp = LocalDateTime.now();
        this.status = "processing";
    }
    
    public AnalysisResult(String filename, String username) {
        this();
        this.filename = filename;
        this.username = username;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getOriginalFilename() {
        return originalFilename;
    }
    
    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Double getConfidence() {
        return confidence;
    }
    
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getOrgan() {
        return organ;
    }
    
    public void setOrgan(String organ) {
        this.organ = organ;
    }
    
    public String getTissueType() {
        return tissueType;
    }
    
    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }
    
    public List<String> getAbnormalities() {
        return abnormalities;
    }
    
    public void setAbnormalities(List<String> abnormalities) {
        this.abnormalities = abnormalities;
    }
    
    public List<String> getRecommendations() {
        return recommendations;
    }
    
    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }
    
    public String getProcessingTime() {
        return processingTime;
    }
    
    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }
    
    public String getMlModel() {
        return mlModel;
    }
    
    public void setMlModel(String mlModel) {
        this.mlModel = mlModel;
    }
    
    public String getImageHash() {
        return imageHash;
    }
    
    public void setImageHash(String imageHash) {
        this.imageHash = imageHash;
    }
    
    public String getAnalysisVersion() {
        return analysisVersion;
    }
    
    public void setAnalysisVersion(String analysisVersion) {
        this.analysisVersion = analysisVersion;
    }
    
    public String getTechnicalNotes() {
        return technicalNotes;
    }
    
    public void setTechnicalNotes(String technicalNotes) {
        this.technicalNotes = technicalNotes;
    }
    
    public String getQualityScore() {
        return qualityScore;
    }
    
    public void setQualityScore(String qualityScore) {
        this.qualityScore = qualityScore;
    }
    
    public String getMagnification() {
        return magnification;
    }
    
    public void setMagnification(String magnification) {
        this.magnification = magnification;
    }
    
    public String getStainingMethod() {
        return stainingMethod;
    }
    
    public void setStainingMethod(String stainingMethod) {
        this.stainingMethod = stainingMethod;
    }
    
    public String getSampleSource() {
        return sampleSource;
    }
    
    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }
    
    public String getPathologistNotes() {
        return pathologistNotes;
    }
    
    public void setPathologistNotes(String pathologistNotes) {
        this.pathologistNotes = pathologistNotes;
    }
    
    public String getFollowUpRecommendations() {
        return followUpRecommendations;
    }
    
    public void setFollowUpRecommendations(String followUpRecommendations) {
        this.followUpRecommendations = followUpRecommendations;
    }
    
    public String getRiskLevel() {
        return riskLevel;
    }
    
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
    
    public String getUrgencyLevel() {
        return urgencyLevel;
    }
    
    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }
    
    public String getDifferentialDiagnosis() {
        return differentialDiagnosis;
    }
    
    public void setDifferentialDiagnosis(String differentialDiagnosis) {
        this.differentialDiagnosis = differentialDiagnosis;
    }
    
    public String getHistopathologicalFeatures() {
        return histopathologicalFeatures;
    }
    
    public void setHistopathologicalFeatures(String histopathologicalFeatures) {
        this.histopathologicalFeatures = histopathologicalFeatures;
    }
    
    public String getMolecularMarkers() {
        return molecularMarkers;
    }
    
    public void setMolecularMarkers(String molecularMarkers) {
        this.molecularMarkers = molecularMarkers;
    }
    
    public String getGeneticMutations() {
        return geneticMutations;
    }
    
    public void setGeneticMutations(String geneticMutations) {
        this.geneticMutations = geneticMutations;
    }
    
    public String getTreatmentSuggestions() {
        return treatmentSuggestions;
    }
    
    public void setTreatmentSuggestions(String treatmentSuggestions) {
        this.treatmentSuggestions = treatmentSuggestions;
    }
    
    public String getPrognosis() {
        return prognosis;
    }
    
    public void setPrognosis(String prognosis) {
        this.prognosis = prognosis;
    }
    
    public String getReferences() {
        return references;
    }
    
    public void setReferences(String references) {
        this.references = references;
    }
    
    public String getValidationStatus() {
        return validationStatus;
    }
    
    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }
    
    public String getPeerReviewStatus() {
        return peerReviewStatus;
    }
    
    public void setPeerReviewStatus(String peerReviewStatus) {
        this.peerReviewStatus = peerReviewStatus;
    }
    
    public String getClinicalCorrelation() {
        return clinicalCorrelation;
    }
    
    public void setClinicalCorrelation(String clinicalCorrelation) {
        this.clinicalCorrelation = clinicalCorrelation;
    }
    
    public String getResearchNotes() {
        return researchNotes;
    }
    
    public void setResearchNotes(String researchNotes) {
        this.researchNotes = researchNotes;
    }
}
