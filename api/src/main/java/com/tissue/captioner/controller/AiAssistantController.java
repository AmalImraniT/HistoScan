package com.tissue.captioner.controller;

import com.tissue.captioner.dto.AiAssistantRequest;
import com.tissue.captioner.dto.AiAssistantResponse;
import com.tissue.captioner.service.AiAssistantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(
  origins = {"http://localhost:5173", "http://192.168.11.123:8080"},
  allowedHeaders = "*",
  methods = {RequestMethod.POST, RequestMethod.OPTIONS},
  allowCredentials = "true"
)
@RestController
@RequestMapping("/api/ai")
public class AiAssistantController {

    private final AiAssistantService aiAssistantService;

    public AiAssistantController(AiAssistantService aiAssistantService) {
        this.aiAssistantService = aiAssistantService;
    }

    @PostMapping("/generate")
    public ResponseEntity<AiAssistantResponse> generate(@RequestBody AiAssistantRequest req) {
        AiAssistantResponse res = aiAssistantService.generateResponse(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "AI Assistant is running",
            "timestamp", String.valueOf(System.currentTimeMillis())
        ));
    }
}
