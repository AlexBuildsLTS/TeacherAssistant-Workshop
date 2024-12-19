# Prompt Engineering Application

This project is **AI-assistant for fullstack ** application built using Spring Boot for the backend and React for the frontend. The AI Assistant processes user prompts and provides responses using the Spring AI framework for OpenAI integration.

---

## Features
- **Backend**: Built with Spring Boot and Spring AI to handle AI prompt processing.
- **Frontend**: React-based UI to interact with the assistant.
- **Docker**: Fully containerized backend and frontend for easy deployment.

---

## Backend: Spring Boot with Spring AI

### **Main Class**
The entry point for the Spring Boot application:
```java
package se.alex.lexicon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PromptEngineeringApplication {
    public static void main(String[] args) {
        SpringApplication.run(PromptEngineeringApplication.class, args);
    }
}
```

### **Service Layer**
Service to handle AI prompt processing using Spring AI:
```java
import org.springframework.ai.openai.OpenAiService;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final OpenAiService openAiService;

    public AiService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public String getResponse(String prompt) {
        return openAiService.completion(prompt).block().getChoices().get(0).getText();
    }
}
```

### **Controller**
Controller to expose the API endpoints:
```java
import org.springframework.web.bind.annotation.*;

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
```

### **Application Properties**
Configuration file:
```properties
server.port=8080
spring.application.name=PromptEngineering
spring.ai.openai.api-key=your-openai-api-key
spring.ai.openai.base-url=https://api.openai.com/v1
```

---

## Frontend: React

### **Setup**
1. Initialize a React project:
   ```bash
   npx create-react-app promptengineering-frontend
   cd promptengineering-frontend
   npm install axios
   ```

2. **Chat Component**:
```jsx
import React, { useState } from "react";
import axios from "axios";

const Chat = () => {
    const [prompt, setPrompt] = useState("");
    const [response, setResponse] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.post("http://localhost:8080/api/chat", prompt, {
                headers: { "Content-Type": "text/plain" },
            });
            setResponse(res.data);
        } catch (err) {
            console.error(err);
            setResponse("Error connecting to backend");
        }
    };

    return (
        <div>
            <h1>Prompt Engineering Assistant</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={prompt}
                    onChange={(e) => setPrompt(e.target.value)}
                    placeholder="Ask me anything..."
                />
                <button type="submit">Send</button>
            </form>
            <div>
                <h2>Response:</h2>
                <p>{response}</p>
            </div>
        </div>
    );
};

export default Chat;
```

---

## Dockerization

### **Backend Dockerfile**
Create a `Dockerfile` in the Spring Boot project:
```dockerfile
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/promptengineering-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Build and run the backend Docker container:
```bash
mvn clean package
docker build -t promptengineering-backend .
docker run -p 8080:8080 promptengineering-backend
```

### **Frontend Dockerfile**
Create a `Dockerfile` in the React project:
```dockerfile
FROM node:16
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm", "start"]
```

Build and run the frontend Docker container:
```bash
docker build -t promptengineering-frontend .
docker run -p 3000:3000 promptengineering-frontend
```

---

## Running the Application
1. Start the backend:
   ```bash
   mvn spring-boot:run
   ```
2. Start the frontend:
   ```bash
   npm start
   ```
3. Access the application:
   - Frontend: `http://localhost:3000`
   - Backend: `http://localhost:8080`

---

## Future Improvements
- Extend AI capabilities by configuring additional models using Spring AI.
- Add a database to persist chat history.
- Deploy the app using Docker Compose or Kubernetes.

---