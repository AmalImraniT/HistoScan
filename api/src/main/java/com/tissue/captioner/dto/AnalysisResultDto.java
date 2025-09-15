package com.tissue.captioner.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AnalysisResultDto {
    
    private Long id;
    private String filename;
    private String originalFilename;
    private String imageUrl;
    private LocalDateTime timestamp;
    private String status;
    private Double confidence;
    private String description;
    private String organ;
    private String tissueType;
    private List<String> abnormalities;
    private List<String> recommendations;
    private String processingTime;
    private String mlModel;
    private String username;
    private String imageHash;
    private String analysisVersion;
    private String technicalNotes;
    private String qualityScore;
    private String magnification;
    private String stainingMethod;
    private String sampleSource;
    private String pathologistNotes;
    private String followUpRecommendations;
    private String riskLevel;
    private String urgencyLevel;
    private String differentialDiagnosis;
    private String histopathologicalFeatures;
    private String molecularMarkers;
    private String geneticMutations;
    private String treatmentSuggestions;
    private String prognosis;
    private String references;
    private String validationStatus;
    private String peerReviewStatus;
    private String clinicalCorrelation;
    private String researchNotes;
    
    // Constructeurs
    public AnalysisResultDto() {}
    
    public AnalysisResultDto(Long id, String filename, String originalFilename, String imageUrl, 
                           LocalDateTime timestamp, String status, Double confidence, String description,
                           String organ, String tissueType, List<String> abnormalities, 
                           List<String> recommendations, String processingTime, String mlModel, 
                           String username) {
        this.id = id;
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.status = status;
        this.confidence = confidence;
        this.description = description;
        this.organ = organ;
        this.tissueType = tissueType;
        this.abnormalities = abnormalities;
        this.recommendations = recommendations;
        this.processingTime = processingTime;
        this.mlModel = mlModel;
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
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
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
