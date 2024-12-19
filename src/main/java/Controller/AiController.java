package se.alex.lexicon.controller;

import org.springframework.web.bind.annotation.*;
import se.alex.lexicon.service.AiService;

@RestController
@RequestMapping("/api/chat")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public String chat(@RequestBody String prompt) {
        return aiService.getResponse(prompt);
    }
}
