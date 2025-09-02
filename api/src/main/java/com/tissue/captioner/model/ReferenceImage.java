package com.tissue.captioner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "reference_images")
public class ReferenceImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 100)
    private String title;
    
    @Size(max = 500)
    private String description;
    
    @NotBlank
    private String imageUrl;
    
    @Size(max = 100)
    private String category;
    
    @Size(max = 100)
    private String organType;
    
    @Enumerated(EnumType.STRING)
    private ImageStatus status;
    
    @Size(max = 200)
    private String tags;
    
    private Long fileSize;
    
    @Size(max = 50)
    private String fileType;
    
    private LocalDateTime uploadedAt;
    
    private LocalDateTime lastModified;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;
    
    public enum ImageStatus {
        PENDING, APPROVED, REJECTED, ARCHIVED
    }
    
    // Constructeurs
    public ReferenceImage() {
        this.uploadedAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.status = ImageStatus.PENDING;
    }
    
    public ReferenceImage(String title, String description, String imageUrl, String category) {
        this();
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getOrganType() {
        return organType;
    }
    
    public void setOrganType(String organType) {
        this.organType = organType;
    }
    
    public ImageStatus getStatus() {
        return status;
    }
    
    public void setStatus(ImageStatus status) {
        this.status = status;
    }
    
    public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public Long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
    
    public LocalDateTime getLastModified() {
        return lastModified;
    }
    
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = LocalDateTime.now();
    }
    
    public User getUploadedBy() {
        return uploadedBy;
    }
    
    public void setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }
}

