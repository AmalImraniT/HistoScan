package com.tissue.captioner.service;

import com.tissue.captioner.model.ReferenceImage;
import com.tissue.captioner.model.User;
import com.tissue.captioner.repository.ReferenceImageRepository;
import com.tissue.captioner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AdminImageService {
    
    @Autowired
    private ReferenceImageRepository referenceImageRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private final String uploadDir = "uploads/reference-images/";
    
    public List<ReferenceImage> getAllImages() {
        return referenceImageRepository.findAll();
    }
    
    public ReferenceImage getImageById(Long id) {
        return referenceImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image non trouvée"));
    }
    
    public ReferenceImage createImage(ReferenceImage image, MultipartFile file, Long uploadedById) {
        try {
            // Gérer l'upload du fichier
            String fileName = handleFileUpload(file);
            
            // Récupérer l'utilisateur qui upload
            User uploadedBy = userRepository.findById(uploadedById)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
            
            image.setImageUrl(fileName);
            image.setUploadedBy(uploadedBy);
            image.setUploadedAt(LocalDateTime.now());
            image.setLastModified(LocalDateTime.now());
            image.setFileSize(file.getSize());
            image.setFileType(file.getContentType());
            
            return referenceImageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'upload du fichier", e);
        }
    }
    
    public ReferenceImage updateImage(Long id, ReferenceImage imageDetails) {
        ReferenceImage image = referenceImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image non trouvée"));
        
        if (imageDetails.getTitle() != null) {
            image.setTitle(imageDetails.getTitle());
        }
        
        if (imageDetails.getDescription() != null) {
            image.setDescription(imageDetails.getDescription());
        }
        
        if (imageDetails.getCategory() != null) {
            image.setCategory(imageDetails.getCategory());
        }
        
        if (imageDetails.getOrganType() != null) {
            image.setOrganType(imageDetails.getOrganType());
        }
        
        if (imageDetails.getStatus() != null) {
            image.setStatus(imageDetails.getStatus());
        }
        
        if (imageDetails.getTags() != null) {
            image.setTags(imageDetails.getTags());
        }
        
        image.setLastModified(LocalDateTime.now());
        
        return referenceImageRepository.save(image);
    }
    
    public void deleteImage(Long id) {
        ReferenceImage image = referenceImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image non trouvée"));
        
        // Supprimer le fichier physique
        try {
            Path filePath = Paths.get(uploadDir, image.getImageUrl());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            // Log l'erreur mais continuer avec la suppression de la base
        }
        
        referenceImageRepository.delete(image);
    }
    
    public List<ReferenceImage> searchImages(String searchTerm) {
        return referenceImageRepository.findBySearchTerm(searchTerm);
    }
    
    public List<ReferenceImage> getImagesByStatus(ReferenceImage.ImageStatus status) {
        return referenceImageRepository.findByStatus(status);
    }
    
    public List<ReferenceImage> getImagesByCategory(String category) {
        return referenceImageRepository.findByCategory(category);
    }
    
    public List<ReferenceImage> getImagesByOrganType(String organType) {
        return referenceImageRepository.findByOrganType(organType);
    }
    
    public List<ReferenceImage> getImagesByFilters(ReferenceImage.ImageStatus status, String category, String organType) {
        return referenceImageRepository.findByFilters(status, category, organType);
    }
    
    public List<String> getAllCategories() {
        return referenceImageRepository.findAllCategories();
    }
    
    public List<String> getAllOrganTypes() {
        return referenceImageRepository.findAllOrganTypes();
    }
    
    public long getTotalImageCount() {
        return referenceImageRepository.count();
    }
    
    public long getImageCountByStatus(ReferenceImage.ImageStatus status) {
        return referenceImageRepository.countByStatus(status);
    }
    
    public List<ReferenceImage> getRecentUploads() {
        return referenceImageRepository.findRecentUploads();
    }
    
    public long getTotalStorageUsed() {
        Long totalSize = referenceImageRepository.getTotalStorageUsed();
        return totalSize != null ? totalSize : 0L;
    }
    
    private String handleFileUpload(MultipartFile file) throws IOException {
        // Créer le répertoire s'il n'existe pas
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Générer un nom de fichier unique
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName != null ? 
            originalFileName.substring(originalFileName.lastIndexOf(".")) : "";
        String fileName = UUID.randomUUID().toString() + fileExtension;
        
        // Sauvegarder le fichier
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        
        return fileName;
    }
}

