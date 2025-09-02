package com.tissue.captioner.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_preferences")
public class SystemPreferences {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String preferenceKey;
    
    @Column(columnDefinition = "TEXT")
    private String preferenceValue;
    
    private String description;
    
    private String category;
    
    private LocalDateTime lastModified;
    
    private LocalDateTime createdAt;
    
    // Constructeurs
    public SystemPreferences() {
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }
    
    public SystemPreferences(String preferenceKey, String preferenceValue, String description, String category) {
        this();
        this.preferenceKey = preferenceKey;
        this.preferenceValue = preferenceValue;
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
    
    public String getPreferenceKey() {
        return preferenceKey;
    }
    
    public void setPreferenceKey(String preferenceKey) {
        this.preferenceKey = preferenceKey;
    }
    
    public String getPreferenceValue() {
        return preferenceValue;
    }
    
    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
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

