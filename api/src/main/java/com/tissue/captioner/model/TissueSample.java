package com.tissue.captioner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tissue_samples")
public class TissueSample {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String filename;
    
    @NotBlank
    private String originalFilename;
    
    @NotBlank
    private String filePath;
    
    @NotBlank
    private String caption;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;
    
    private LocalDateTime uploadedAt;
    
    private LocalDateTime updatedAt;
    
    private String description;
    
    private String tissueType;
    
    private String magnification;
    
    private String stainType;
    
    // Constructeurs
    public TissueSample() {
        this.uploadedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public TissueSample(String filename, String originalFilename, String filePath, 
                       String caption, User uploadedBy) {
        this();
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.filePath = filePath;
        this.caption = caption;
        this.uploadedBy = uploadedBy;
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
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getCaption() {
        return caption;
    }
    
    public void setCaption(String caption) {
        this.caption = caption;
        this.updatedAt = LocalDateTime.now();
    }
    
    public User getUploadedBy() {
        return uploadedBy;
    }
    
    public void setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
    
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getTissueType() {
        return tissueType;
    }
    
    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getMagnification() {
        return magnification;
    }
    
    public void setMagnification(String magnification) {
        this.magnification = magnification;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getStainType() {
        return stainType;
    }
    
    public void setStainType(String stainType) {
        this.stainType = stainType;
        this.updatedAt = LocalDateTime.now();
    }
}
