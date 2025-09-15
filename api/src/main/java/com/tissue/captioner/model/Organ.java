package com.tissue.captioner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "organs")
public class Organ {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 100)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Size(max = 200)
    private String imageUrl;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Size(max = 100)
    private String category;
    
    @Enumerated(EnumType.STRING)
    private Priority priority;
    
    // Nouveau: chemin d'enregistrement et emplacement (LOCAL/DRIVE)
    @Size(max = 255)
    private String storagePath;

    @Enumerated(EnumType.STRING)
    private StorageLocation storageLocation;

    private LocalDateTime lastModified;
    
    private LocalDateTime createdAt;
    
    public enum Status {
        DRAFT, PUBLISHED, ARCHIVED
    }
    
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public enum StorageLocation {
        LOCAL, DRIVE
    }
    
    // Constructeurs
    public Organ() {
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.status = Status.DRAFT;
        this.priority = Priority.MEDIUM;
    }
    
    public Organ(String name, String description, String category) {
        this();
        this.name = name;
        this.description = description;
        this.category = category;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Priority getPriority() {
        return priority;
    }
    
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public StorageLocation getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
    }
    
    public LocalDateTime getLastModified() {
        return lastModified;
    }
    
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }
}

