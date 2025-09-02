package com.tissue.captioner.service;

import com.tissue.captioner.dto.UserDto;
import com.tissue.captioner.model.SystemStatistics;
import com.tissue.captioner.model.User;
import com.tissue.captioner.repository.SystemStatisticsRepository;
import com.tissue.captioner.repository.UserRepository;
import com.tissue.captioner.repository.ViewHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminStatisticsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ViewHistoryRepository viewHistoryRepository;
    
    @Autowired
    private SystemStatisticsRepository systemStatisticsRepository;
    
    public Map<String, Object> getDashboardStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        // Statistiques utilisateurs
        long totalUsers = userRepository.count();
        long activeUsers = userRepository.count(); // Pour l'instant, tous les utilisateurs sont actifs
        long adminCount = userRepository.countByRole(User.Role.ADMIN);
        long userCount = userRepository.countByRole(User.Role.USER);
        
        // Statistiques analyses
        long totalAnalyses = viewHistoryRepository.count();
        long todayAnalyses = viewHistoryRepository.countByViewedAtAfter(LocalDate.now().atStartOfDay());
        
        // Statistiques système
        double systemUptime = 99.8; // À calculer dynamiquement
        double avgResponseTime = 2.3; // À calculer dynamiquement
        double successRate = 94.2; // À calculer dynamiquement
        
        stats.put("totalUsers", totalUsers);
        stats.put("activeUsers", activeUsers);
        stats.put("adminCount", adminCount);
        stats.put("userCount", userCount);
        stats.put("totalAnalyses", totalAnalyses);
        stats.put("todayAnalyses", todayAnalyses);
        stats.put("systemUptime", systemUptime);
        stats.put("avgResponseTime", avgResponseTime);
        stats.put("successRate", successRate);
        
        return stats;
    }
    
    public List<UserDto> getTopUsers(int limit) {
        return userRepository.findTopUsersByAnalyses(limit)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
    
    public Map<String, Object> getMonthlyTrends() {
        Map<String, Object> trends = new HashMap<>();
        
        // Récupérer les données des 12 derniers mois
        LocalDateTime startDate = LocalDateTime.now().minusMonths(12);
        
        // Statistiques mensuelles (simulées pour l'instant)
        trends.put("monthlyData", generateMonthlyData());
        
        return trends;
    }
    
    public Map<String, Object> getSystemMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        
        // Métriques système
        metrics.put("cpuUsage", getCpuUsage());
        metrics.put("memoryUsage", getMemoryUsage());
        metrics.put("diskUsage", getDiskUsage());
        metrics.put("networkTraffic", getNetworkTraffic());
        
        return metrics;
    }
    
    public void recordSystemStatistics() {
        Map<String, Object> currentStats = getDashboardStatistics();
        
        SystemStatistics stats = new SystemStatistics();
        stats.setTotalUsers((Long) currentStats.get("totalUsers"));
        stats.setActiveUsers((Long) currentStats.get("activeUsers"));
        stats.setTotalAnalyses((Long) currentStats.get("totalAnalyses"));
        stats.setTodayAnalyses((Long) currentStats.get("todayAnalyses"));
        stats.setSystemUptime((Double) currentStats.get("systemUptime"));
        stats.setAvgResponseTime((Double) currentStats.get("avgResponseTime"));
        stats.setSuccessRate((Double) currentStats.get("successRate"));
        stats.setVersion("1.0.0");
        
        systemStatisticsRepository.save(stats);
    }
    
    private List<Map<String, Object>> generateMonthlyData() {
        // Générer des données simulées pour les 12 derniers mois
        // En production, cela viendrait de la base de données
        return List.of(
            Map.of("month", "Jan", "users", 120, "analyses", 180, "images", 45),
            Map.of("month", "Fév", "users", 135, "analyses", 220, "images", 52),
            Map.of("month", "Mar", "users", 142, "analyses", 280, "images", 58),
            Map.of("month", "Avr", "users", 148, "analyses", 320, "images", 62),
            Map.of("month", "Mai", "users", 152, "analyses", 380, "images", 68),
            Map.of("month", "Juin", "users", 156, "analyses", 420, "images", 75),
            Map.of("month", "Juil", "users", 158, "analyses", 450, "images", 78),
            Map.of("month", "Août", "users", 160, "analyses", 480, "images", 82),
            Map.of("month", "Sep", "users", 162, "analyses", 520, "images", 85),
            Map.of("month", "Oct", "users", 165, "analyses", 580, "images", 88),
            Map.of("month", "Nov", "users", 168, "analyses", 620, "images", 90),
            Map.of("month", "Déc", "users", 156, "analyses", 1247, "images", 89)
        );
    }
    
    private double getCpuUsage() {
        // Simuler l'utilisation CPU
        return Math.random() * 30 + 20; // Entre 20% et 50%
    }
    
    private double getMemoryUsage() {
        // Simuler l'utilisation mémoire
        return Math.random() * 40 + 30; // Entre 30% et 70%
    }
    
    private double getDiskUsage() {
        // Simuler l'utilisation disque
        return Math.random() * 20 + 60; // Entre 60% et 80%
    }
    
    private double getNetworkTraffic() {
        // Simuler le trafic réseau
        return Math.random() * 100 + 50; // Entre 50 et 150 MB/s
    }
}

