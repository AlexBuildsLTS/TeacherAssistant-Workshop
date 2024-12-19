# PromptEngineering

**PromptEngineering** is a full-stack application designed to integrate modern AI capabilities into a user-friendly assistant for answering queries, solving problems, and enhancing productivity. Built with Spring Boot for the backend and React for the frontend, this project aims to provide a seamless and scalable platform for AI-driven interactions.

## Features

### Backend
- **Spring Boot**: REST API with endpoints for AI interactions.
- **AI Integration**: Connects to OpenAI's API for generating responses.
- **MariaDB Support**: Stores user queries and responses for persistence.
- **Logging and Debugging**: Configurable logging levels for better monitoring.

### Frontend
- **React Framework**: A clean and responsive user interface.
- **Axios Integration**: Handles HTTP requests to the backend API.
- **Live Chat Interface**: Engage with the AI assistant directly.

### Dockerized Deployment
- **Docker Support**: Both backend and frontend are containerized for easy deployment.
- **Environment Variables**: Secure and configurable API keys and database credentials.

---

## Getting Started

### Prerequisites
Ensure you have the following installed on your system:
- **Java 21**
- **Maven**
- **Node.js (v16 or later)**
- **Docker**

---

## Installation

### Backend
1. Clone the repository:
   ```bash
   git clone https://github.com/AlexBuildsLTS/TeacherAssistant-Workshop.git
   cd TeacherAssistant-Workshop/backend
   ```

2. Build the project:
   ```bash
   mvn clean package
   ```

3. Configure the backend:
   Update the `application.properties` file with your OpenAI API key:
   ```properties
   spring.ai.openai.api-key=your-openai-api-key
   spring.ai.openai.base-url=https://api.openai.com/v1
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Frontend
1. Navigate to the frontend directory:
   ```bash
   cd ../frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the frontend:
   ```bash
   npm start
   ```

---

## Docker Deployment

### Backend Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/promptengineering-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Frontend Dockerfile
```dockerfile
FROM node:16
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm", "start"]
```

### Build and Run
1. Build the backend image:
   ```bash
   docker build -t promptengineering-backend ./backend
   ```
2. Build the frontend image:
   ```bash
   docker build -t promptengineering-frontend ./frontend
   ```
3. Run the containers:
   ```bash
   docker run -p 8080:8080 promptengineering-backend
   docker run -p 3000:3000 promptengineering-frontend
   ```

---

## Usage

### Backend API
- **Endpoint**: `/api/chat`
- **Method**: POST
- **Request Body**: Plain text prompt.
- **Response**: AI-generated text response.

### Frontend
1. Open your browser and navigate to `http://localhost:3000`.
2. Enter a prompt in the chat interface and submit to receive an AI-generated response.

---

## Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch (`feature/my-new-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/my-new-feature`).
5. Create a new Pull Request.

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.

---

## Acknowledgements
- **Spring Boot** for the robust backend framework.
- **React** for the intuitive frontend.
- **OpenAI** for the AI capabilities.
- **Docker** for containerized deployment.

