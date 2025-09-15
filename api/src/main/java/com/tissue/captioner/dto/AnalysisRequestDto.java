package com.tissue.captioner.dto;

import java.util.List;

public class AnalysisRequestDto {
    
    private String text;
    private List<String> texts;
    private String model;
    private boolean returnMetadata;
    
    // Constructeur par défaut
    public AnalysisRequestDto() {}
    
    // Constructeur avec paramètres
    public AnalysisRequestDto(String text, List<String> texts, String model, boolean returnMetadata) {
        this.text = text;
        this.texts = texts;
        this.model = model;
        this.returnMetadata = returnMetadata;
    }
    
    // Getters et Setters
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public List<String> getTexts() {
        return texts;
    }
    
    public void setTexts(List<String> texts) {
        this.texts = texts;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public boolean isReturnMetadata() {
        return returnMetadata;
    }
    
    public void setReturnMetadata(boolean returnMetadata) {
        this.returnMetadata = returnMetadata;
    }
    
    @Override
    public String toString() {
        return "AnalysisRequestDto{" +
                "text='" + text + '\'' +
                ", texts=" + texts +
                ", model='" + model + '\'' +
                ", returnMetadata=" + returnMetadata +
                '}';
    }
}
