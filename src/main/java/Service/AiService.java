package se.alex.lexicon.service;

import org.springframework.ai.openai.OpenAiService;
import org.springframework.ai.openai.model.completion.CompletionResponse;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final OpenAiService openAiService;

    public AiService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public String getResponse(String prompt) {
        CompletionResponse response = openAiService
                .completion(prompt)
                .block();

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No key received yet";
        }

        return response.getChoices().get(0).getText();
    }
}
