package com.tissue.captioner.dto;

public class AiAssistantResponse {
    private boolean success;
    private String model;
    private String outputText;
    private Object raw;

    // Constructeurs
    public AiAssistantResponse() {}

    public AiAssistantResponse(boolean success, String model, String outputText, Object raw) {
        this.success = success;
        this.model = model;
        this.outputText = outputText;
        this.raw = raw;
    }

    // Getters et Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public Object getRaw() {
        return raw;
    }

    public void setRaw(Object raw) {
        this.raw = raw;
    }
}
