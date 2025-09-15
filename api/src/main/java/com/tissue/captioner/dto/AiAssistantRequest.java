package com.tissue.captioner.dto;

import java.util.List;

public class AiAssistantRequest {
    private String prompt;
    private String system;
    private List<ChatMessage> history;
    private String model;
    private Double temperature;
    private Integer maxTokens;

    // Constructeurs
    public AiAssistantRequest() {}

    public AiAssistantRequest(String prompt, String system, List<ChatMessage> history, String model, Double temperature, Integer maxTokens) {
        this.prompt = prompt;
        this.system = system;
        this.history = history;
        this.model = model;
        this.temperature = temperature;
        this.maxTokens = maxTokens;
    }

    // Getters et Setters
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<ChatMessage> getHistory() {
        return history;
    }

    public void setHistory(List<ChatMessage> history) {
        this.history = history;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    // Classe interne pour les messages
    public static class ChatMessage {
        private String sender;
        private String text;

        public ChatMessage() {}

        public ChatMessage(String sender, String text) {
            this.sender = sender;
            this.text = text;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
