package com.tissue.captioner.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tissue.captioner.dto.AiAssistantRequest;
import com.tissue.captioner.dto.AiAssistantResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AiAssistantService {

    private static final Logger logger = LoggerFactory.getLogger(AiAssistantService.class);

    @Value("${ai.gemini.api-key}")
    private String apiKey;

    @Value("${ai.gemini.base-url:https://generativelanguage.googleapis.com}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AiAssistantService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public AiAssistantResponse generateResponse(AiAssistantRequest request) {
        try {
            logger.info("Début de la génération de réponse pour le prompt: {}", request.getPrompt());
            
            // Validation
            if (request.getPrompt() == null || request.getPrompt().trim().isEmpty()) {
                throw new IllegalArgumentException("Missing prompt");
            }

            // Valeurs par défaut
            String prompt = request.getPrompt().trim();
            String system = request.getSystem() != null ? request.getSystem().trim() : "You are a helpful assistant.";
            List<AiAssistantRequest.ChatMessage> history = request.getHistory() != null ? request.getHistory() : new ArrayList<>();
            String model = request.getModel() != null ? request.getModel() : "gemini-1.5-flash";
            Double temperature = request.getTemperature() != null ? request.getTemperature() : 0.7;
            Integer maxTokens = request.getMaxTokens() != null ? request.getMaxTokens() : 1024;

            // Construction du payload
            Map<String, Object> payload = buildPayload(prompt, system, history, temperature, maxTokens);

            // Appel à l'API Gemini
            String url = String.format("%s/v1beta/models/%s:generateContent?key=%s", 
                baseUrl, model, apiKey);
            
            logger.info("Appel à l'API Gemini: {}", url);
            logger.info("Payload envoyé: {}", payload);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

            ResponseEntity<JsonNode> response = restTemplate.exchange(
                url, 
                HttpMethod.POST, 
                entity, 
                JsonNode.class
            );
            
            logger.info("Réponse reçue de Gemini: {}", response.getStatusCode());

            // Traitement de la réponse
            return processResponse(response.getBody(), model);

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Upstream error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            throw new RuntimeException("Server error: " + e.getMessage());
        }
    }

    private Map<String, Object> buildPayload(String prompt, String system, 
                                           List<AiAssistantRequest.ChatMessage> history, 
                                           Double temperature, Integer maxTokens) {
        Map<String, Object> payload = new HashMap<>();
        List<Map<String, Object>> contents = new ArrayList<>();

        // Message système (compatible Gemini via system_instruction)
        if (!system.isEmpty()) {
            payload.put("system_instruction", Map.of(
                "role", "user",
                "parts", List.of(Map.of("text", system))
            ));
        }

        // Historique des messages
        for (AiAssistantRequest.ChatMessage msg : history) {
            if (msg.getText() != null && !msg.getText().trim().isEmpty()) {
                Map<String, Object> content = new HashMap<>();
                String role = "bot".equals(msg.getSender()) ? "model" : "user";
                content.put("role", role);
                
                List<Map<String, String>> parts = new ArrayList<>();
                parts.add(Map.of("text", msg.getText().trim()));
                content.put("parts", parts);
                
                contents.add(content);
            }
        }

        // Message actuel de l'utilisateur
        Map<String, Object> userContent = new HashMap<>();
        userContent.put("role", "user");
        List<Map<String, String>> parts = new ArrayList<>();
        parts.add(Map.of("text", prompt));
        userContent.put("parts", parts);
        contents.add(userContent);

        payload.put("contents", contents);

        // Configuration de génération
        Map<String, Object> generationConfig = new HashMap<>();
        generationConfig.put("temperature", temperature);
        generationConfig.put("maxOutputTokens", maxTokens);
        payload.put("generationConfig", generationConfig);

        return payload;
    }

    private AiAssistantResponse processResponse(JsonNode response, String model) {
        StringBuilder out = new StringBuilder();

        if (response != null && response.has("candidates") && response.get("candidates").isArray()) {
            for (JsonNode cand : response.get("candidates")) {
                if (cand.has("content") && cand.get("content").has("parts") && cand.get("content").get("parts").isArray()) {
                    for (JsonNode part : cand.get("content").get("parts")) {
                        if (part.has("text")) {
                            out.append(part.get("text").asText()).append("\n");
                        }
                    }
                } else if (cand.has("output")) {
                    out.append(cand.get("output").asText()).append("\n");
                }
            }
        }

        String outputText = out.toString().trim();
        if (outputText.isEmpty()) outputText = "Désolé, je n'ai pas pu générer de réponse.";
        return new AiAssistantResponse(true, model, outputText, response);
    }
}
