package com.tissue.captioner.repository;

import com.tissue.captioner.model.Organ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganRepository extends JpaRepository<Organ, Long> {
    
    List<Organ> findByStatus(Organ.Status status);
    
    List<Organ> findByCategory(String category);
    
    List<Organ> findByPriority(Organ.Priority priority);
    
    @Query("SELECT o FROM Organ o WHERE " +
           "LOWER(o.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(o.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Organ> findBySearchTerm(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT o FROM Organ o WHERE " +
           "(:status IS NULL OR o.status = :status) AND " +
           "(:category IS NULL OR o.category = :category) AND " +
           "(:priority IS NULL OR o.priority = :priority)")
    List<Organ> findByFilters(@Param("status") Organ.Status status,
                              @Param("category") String category,
                              @Param("priority") Organ.Priority priority);
    
    @Query("SELECT DISTINCT o.category FROM Organ o")
    List<String> findAllCategories();
    
    @Query("SELECT COUNT(o) FROM Organ o WHERE o.status = :status")
    Long countByStatus(@Param("status") Organ.Status status);
    
    @Query("SELECT o FROM Organ o ORDER BY o.lastModified DESC")
    List<Organ> findRecentModified();
}

