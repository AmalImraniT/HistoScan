package com.tissue.captioner.repository;

import com.tissue.captioner.model.SystemStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SystemStatisticsRepository extends JpaRepository<SystemStatistics, Long> {
    
    List<SystemStatistics> findByRecordedAtBetween(LocalDateTime start, LocalDateTime end);
    
    SystemStatistics findTopByOrderByRecordedAtDesc();
    
    List<SystemStatistics> findTop10ByOrderByRecordedAtDesc();
}

