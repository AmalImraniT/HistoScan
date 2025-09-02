package com.tissue.captioner.service;

import com.tissue.captioner.model.SystemPreferences;
import com.tissue.captioner.repository.SystemPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminPreferencesService {
    
    @Autowired
    private SystemPreferencesRepository systemPreferencesRepository;
    
    public Map<String, String> getAllPreferences() {
        Map<String, String> preferences = new HashMap<>();
        List<SystemPreferences> allPrefs = systemPreferencesRepository.findAll();
        
        for (SystemPreferences pref : allPrefs) {
            preferences.put(pref.getPreferenceKey(), pref.getPreferenceValue());
        }
        
        return preferences;
    }
    
    public String getPreference(String key) {
        Optional<SystemPreferences> pref = systemPreferencesRepository.findByPreferenceKey(key);
        return pref.map(SystemPreferences::getPreferenceValue).orElse(null);
    }
    
    public Map<String, String> getPreferencesByCategory(String category) {
        Map<String, String> preferences = new HashMap<>();
        List<SystemPreferences> categoryPrefs = systemPreferencesRepository.findByCategory(category);
        
        for (SystemPreferences pref : categoryPrefs) {
            preferences.put(pref.getPreferenceKey(), pref.getPreferenceValue());
        }
        
        return preferences;
    }
    
    public SystemPreferences setPreference(String key, String value, String description, String category) {
        Optional<SystemPreferences> existingPref = systemPreferencesRepository.findByPreferenceKey(key);
        
        if (existingPref.isPresent()) {
            SystemPreferences pref = existingPref.get();
            pref.setPreferenceValue(value);
            return systemPreferencesRepository.save(pref);
        } else {
            SystemPreferences newPref = new SystemPreferences(key, value, description, category);
            return systemPreferencesRepository.save(newPref);
        }
    }
    
    public void setMultiplePreferences(Map<String, String> preferences) {
        for (Map.Entry<String, String> entry : preferences.entrySet()) {
            setPreference(entry.getKey(), entry.getValue(), "", "general");
        }
    }
    
    public void deletePreference(String key) {
        Optional<SystemPreferences> pref = systemPreferencesRepository.findByPreferenceKey(key);
        pref.ifPresent(systemPreferencesRepository::delete);
    }
    
    public void initializeDefaultPreferences() {
        // Notifications
        setPreference("emailNotifications", "true", "Notifications par email", "notifications");
        setPreference("pushNotifications", "false", "Notifications push", "notifications");
        setPreference("analysisComplete", "true", "Notification analyse terminée", "notifications");
        setPreference("systemAlerts", "true", "Alertes système", "notifications");
        setPreference("weeklyReports", "false", "Rapports hebdomadaires", "notifications");
        
        // Sécurité
        setPreference("twoFactorAuth", "false", "Authentification à deux facteurs", "security");
        setPreference("sessionTimeout", "30", "Délai d'expiration de session (minutes)", "security");
        setPreference("passwordExpiry", "90", "Expiration du mot de passe (jours)", "security");
        setPreference("loginAttempts", "5", "Tentatives de connexion max", "security");
        
        // Interface
        setPreference("theme", "light", "Thème de l'interface", "interface");
        setPreference("language", "fr", "Langue de l'interface", "interface");
        setPreference("timezone", "Europe/Paris", "Fuseau horaire", "interface");
        setPreference("dateFormat", "DD/MM/YYYY", "Format de date", "interface");
        
        // Système
        setPreference("autoBackup", "true", "Sauvegarde automatique", "system");
        setPreference("backupFrequency", "daily", "Fréquence de sauvegarde", "system");
        setPreference("retentionPeriod", "30", "Période de rétention (jours)", "system");
        setPreference("debugMode", "false", "Mode debug", "system");
        
        // API et Intégrations
        setPreference("apiRateLimit", "1000", "Limite de taux API (req/min)", "api");
        setPreference("webhookEnabled", "false", "Webhooks activés", "api");
        setPreference("webhookUrl", "", "URL Webhook", "api");
        setPreference("mlServiceUrl", "http://localhost:8001", "URL du service ML", "api");
    }
    
    public Map<String, Object> getPreferencesStructure() {
        Map<String, Object> structure = new HashMap<>();
        
        // Notifications
        structure.put("notifications", Map.of(
            "emailNotifications", Map.of("type", "boolean", "default", "true"),
            "pushNotifications", Map.of("type", "boolean", "default", "false"),
            "analysisComplete", Map.of("type", "boolean", "default", "true"),
            "systemAlerts", Map.of("type", "boolean", "default", "true"),
            "weeklyReports", Map.of("type", "boolean", "default", "false")
        ));
        
        // Sécurité
        structure.put("security", Map.of(
            "twoFactorAuth", Map.of("type", "boolean", "default", "false"),
            "sessionTimeout", Map.of("type", "number", "default", "30"),
            "passwordExpiry", Map.of("type", "number", "default", "90"),
            "loginAttempts", Map.of("type", "number", "default", "5")
        ));
        
        // Interface
        structure.put("interface", Map.of(
            "theme", Map.of("type", "select", "options", List.of("light", "dark", "auto"), "default", "light"),
            "language", Map.of("type", "select", "options", List.of("fr", "en", "es"), "default", "fr"),
            "timezone", Map.of("type", "select", "options", List.of("Europe/Paris", "UTC", "America/New_York"), "default", "Europe/Paris"),
            "dateFormat", Map.of("type", "select", "options", List.of("DD/MM/YYYY", "MM/DD/YYYY", "YYYY-MM-DD"), "default", "DD/MM/YYYY")
        ));
        
        // Système
        structure.put("system", Map.of(
            "autoBackup", Map.of("type", "boolean", "default", "true"),
            "backupFrequency", Map.of("type", "select", "options", List.of("hourly", "daily", "weekly"), "default", "daily"),
            "retentionPeriod", Map.of("type", "number", "default", "30"),
            "debugMode", Map.of("type", "boolean", "default", "false")
        ));
        
        // API
        structure.put("api", Map.of(
            "apiRateLimit", Map.of("type", "number", "default", "1000"),
            "webhookEnabled", Map.of("type", "boolean", "default", "false"),
            "webhookUrl", Map.of("type", "url", "default", ""),
            "mlServiceUrl", Map.of("type", "url", "default", "http://localhost:8001")
        ));
        
        return structure;
    }
}

