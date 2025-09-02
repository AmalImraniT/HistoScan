package com.tissue.captioner.service;

import com.tissue.captioner.model.Organ;
import com.tissue.captioner.repository.OrganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminOrganService {
    
    @Autowired
    private OrganRepository organRepository;
    
    public List<Organ> getAllOrgans() {
        return organRepository.findAll();
    }
    
    public Organ getOrganById(Long id) {
        return organRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organe non trouvé"));
    }
    
    public Organ createOrgan(Organ organ) {
        organ.setCreatedAt(LocalDateTime.now());
        organ.setLastModified(LocalDateTime.now());
        return organRepository.save(organ);
    }
    
    public Organ updateOrgan(Long id, Organ organDetails) {
        Organ organ = organRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organe non trouvé"));
        
        if (organDetails.getName() != null) {
            organ.setName(organDetails.getName());
        }
        
        if (organDetails.getDescription() != null) {
            organ.setDescription(organDetails.getDescription());
        }
        
        if (organDetails.getImageUrl() != null) {
            organ.setImageUrl(organDetails.getImageUrl());
        }
        
        if (organDetails.getStatus() != null) {
            organ.setStatus(organDetails.getStatus());
        }
        
        if (organDetails.getCategory() != null) {
            organ.setCategory(organDetails.getCategory());
        }
        
        if (organDetails.getPriority() != null) {
            organ.setPriority(organDetails.getPriority());
        }
        
        organ.setLastModified(LocalDateTime.now());
        
        return organRepository.save(organ);
    }
    
    public void deleteOrgan(Long id) {
        Organ organ = organRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organe non trouvé"));
        organRepository.delete(organ);
    }
    
    public List<Organ> searchOrgans(String searchTerm) {
        return organRepository.findBySearchTerm(searchTerm);
    }
    
    public List<Organ> getOrgansByStatus(Organ.Status status) {
        return organRepository.findByStatus(status);
    }
    
    public List<Organ> getOrgansByCategory(String category) {
        return organRepository.findByCategory(category);
    }
    
    public List<Organ> getOrgansByPriority(Organ.Priority priority) {
        return organRepository.findByPriority(priority);
    }
    
    public List<Organ> getOrgansByFilters(Organ.Status status, String category, Organ.Priority priority) {
        return organRepository.findByFilters(status, category, priority);
    }
    
    public List<String> getAllCategories() {
        return organRepository.findAllCategories();
    }
    
    public long getTotalOrganCount() {
        return organRepository.count();
    }
    
    public long getOrganCountByStatus(Organ.Status status) {
        return organRepository.countByStatus(status);
    }
    
    public List<Organ> getRecentModifiedOrgans() {
        return organRepository.findRecentModified();
    }
}

