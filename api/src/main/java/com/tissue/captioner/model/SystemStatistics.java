package com.tissue.captioner.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_statistics")
public class SystemStatistics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime recordedAt;
    
    private Long totalUsers;
    
    private Long activeUsers;
    
    private Long totalAnalyses;
    
    private Long todayAnalyses;
    
    private Long totalImages;
    
    private Double systemUptime;
    
    private Double avgResponseTime;
    
    private Double successRate;
    
    private Long storageUsed;
    
    private Long storageTotal;
    
    private String version;
    
    // Constructeurs
    public SystemStatistics() {
        this.recordedAt = LocalDateTime.now();
    }
    
    public SystemStatistics(Long totalUsers, Long activeUsers, Long totalAnalyses, Long todayAnalyses) {
        this();
        this.totalUsers = totalUsers;
        this.activeUsers = activeUsers;
        this.totalAnalyses = totalAnalyses;
        this.todayAnalyses = todayAnalyses;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
    
    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
    
    public Long getTotalUsers() {
        return totalUsers;
    }
    
    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }
    
    public Long getActiveUsers() {
        return activeUsers;
    }
    
    public void setActiveUsers(Long activeUsers) {
        this.activeUsers = activeUsers;
    }
    
    public Long getTotalAnalyses() {
        return totalAnalyses;
    }
    
    public void setTotalAnalyses(Long totalAnalyses) {
        this.totalAnalyses = totalAnalyses;
    }
    
    public Long getTodayAnalyses() {
        return todayAnalyses;
    }
    
    public void setTodayAnalyses(Long todayAnalyses) {
        this.todayAnalyses = todayAnalyses;
    }
    
    public Long getTotalImages() {
        return totalImages;
    }
    
    public void setTotalImages(Long totalImages) {
        this.totalImages = totalImages;
    }
    
    public Double getSystemUptime() {
        return systemUptime;
    }
    
    public void setSystemUptime(Double systemUptime) {
        this.systemUptime = systemUptime;
    }
    
    public Double getAvgResponseTime() {
        return avgResponseTime;
    }
    
    public void setAvgResponseTime(Double avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }
    
    public Double getSuccessRate() {
        return successRate;
    }
    
    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }
    
    public Long getStorageUsed() {
        return storageUsed;
    }
    
    public void setStorageUsed(Long storageUsed) {
        this.storageUsed = storageUsed;
    }
    
    public Long getStorageTotal() {
        return storageTotal;
    }
    
    public void setStorageTotal(Long storageTotal) {
        this.storageTotal = storageTotal;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
}

