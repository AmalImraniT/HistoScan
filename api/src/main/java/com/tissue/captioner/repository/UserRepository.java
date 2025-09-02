package com.tissue.captioner.repository;

import com.tissue.captioner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    
    // Methods for role-based queries
    long countByRole(User.Role role);
    List<User> findByRole(User.Role role);
    
    // Method for search functionality
    List<User> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String username, String email);
    
    // Method for finding top users by analysis count
    @Query("SELECT u FROM User u LEFT JOIN u.viewHistories v GROUP BY u ORDER BY COUNT(v) DESC")
    List<User> findTopUsersByAnalyses(@Param("limit") int limit);
}
