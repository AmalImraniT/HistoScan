package com.tissue.captioner.repository;

import com.tissue.captioner.model.TissueSample;
import com.tissue.captioner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TissueSampleRepository extends JpaRepository<TissueSample, Long> {
    List<TissueSample> findByUploadedBy(User user);
}
