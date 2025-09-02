package com.tissue.captioner.repository;

import com.tissue.captioner.model.SystemPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemPreferencesRepository extends JpaRepository<SystemPreferences, Long> {
    
    Optional<SystemPreferences> findByPreferenceKey(String preferenceKey);
    
    List<SystemPreferences> findByCategory(String category);
    
    List<SystemPreferences> findByPreferenceKeyIn(List<String> preferenceKeys);
    
    boolean existsByPreferenceKey(String preferenceKey);
}

