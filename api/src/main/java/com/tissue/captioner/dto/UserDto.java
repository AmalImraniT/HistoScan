package com.tissue.captioner.dto;

import com.tissue.captioner.model.User;
import java.time.LocalDateTime;

public class UserDto {
    
    private Long id;
    private String username;
    private String email;
    private User.Role role;
    private String status;
    private Long analysesCount;
    private LocalDateTime lastLogin;
    private LocalDateTime joinDate;
    private String department;
    private String speciality;
    
    // Constructeurs
    public UserDto() {}
    
    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.status = "active"; // Par défaut
        this.analysesCount = (long) user.getViewHistories().size();
        this.lastLogin = user.getLastLoginAt();
        this.joinDate = user.getCreatedAt();
        this.department = "À définir";
        this.speciality = "À définir";
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public User.Role getRole() {
        return role;
    }
    
    public void setRole(User.Role role) {
        this.role = role;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getAnalysesCount() {
        return analysesCount;
    }
    
    public void setAnalysesCount(Long analysesCount) {
        this.analysesCount = analysesCount;
    }
    
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    public LocalDateTime getJoinDate() {
        return joinDate;
    }
    
    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getSpeciality() {
        return speciality;
    }
    
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}

