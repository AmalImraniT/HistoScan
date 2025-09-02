package com.tissue.captioner.repository;

import com.tissue.captioner.model.ViewHistory;
import com.tissue.captioner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {
    List<ViewHistory> findByUser(User user);
    
    // Method for counting analyses after a specific date
    long countByViewedAtAfter(LocalDateTime date);
}
