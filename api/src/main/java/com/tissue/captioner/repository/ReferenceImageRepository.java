package com.tissue.captioner.repository;

import com.tissue.captioner.model.ReferenceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenceImageRepository extends JpaRepository<ReferenceImage, Long> {
    
    List<ReferenceImage> findByStatus(ReferenceImage.ImageStatus status);
    
    List<ReferenceImage> findByCategory(String category);
    
    List<ReferenceImage> findByOrganType(String organType);
    
    @Query("SELECT ri FROM ReferenceImage ri WHERE " +
           "LOWER(ri.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(ri.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(ri.tags) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<ReferenceImage> findBySearchTerm(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT ri FROM ReferenceImage ri WHERE " +
           "(:status IS NULL OR ri.status = :status) AND " +
           "(:category IS NULL OR ri.category = :category) AND " +
           "(:organType IS NULL OR ri.organType = :organType)")
    List<ReferenceImage> findByFilters(@Param("status") ReferenceImage.ImageStatus status,
                                      @Param("category") String category,
                                      @Param("organType") String organType);
    
    @Query("SELECT DISTINCT ri.category FROM ReferenceImage ri")
    List<String> findAllCategories();
    
    @Query("SELECT DISTINCT ri.organType FROM ReferenceImage ri WHERE ri.organType IS NOT NULL")
    List<String> findAllOrganTypes();
    
    @Query("SELECT COUNT(ri) FROM ReferenceImage ri WHERE ri.status = :status")
    Long countByStatus(@Param("status") ReferenceImage.ImageStatus status);
    
    @Query("SELECT ri FROM ReferenceImage ri ORDER BY ri.uploadedAt DESC")
    List<ReferenceImage> findRecentUploads();
    
    @Query("SELECT SUM(ri.fileSize) FROM ReferenceImage ri")
    Long getTotalStorageUsed();
}

