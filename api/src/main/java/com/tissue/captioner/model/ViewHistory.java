package com.tissue.captioner.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "view_history")
public class ViewHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tissue_sample_id")
    private TissueSample tissueSample;
    
    private LocalDateTime viewedAt;
    
    private String ipAddress;
    
    private String userAgent;
    
    // Constructeurs
    public ViewHistory() {
        this.viewedAt = LocalDateTime.now();
    }
    
    public ViewHistory(User user, TissueSample tissueSample) {
        this();
        this.user = user;
        this.tissueSample = tissueSample;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public TissueSample getTissueSample() {
        return tissueSample;
    }
    
    public void setTissueSample(TissueSample tissueSample) {
        this.tissueSample = tissueSample;
    }
    
    public LocalDateTime getViewedAt() {
        return viewedAt;
    }
    
    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public String getUserAgent() {
        return userAgent;
    }
    
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
