package se.alex.lexicon.config;

import org.springframework.ai.openai.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url:https://api.openai.com/v1}")
    private String baseUrl;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(apiKey, baseUrl);
    }
}
