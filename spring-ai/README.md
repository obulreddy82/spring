# Spring AI Demo

A minimal, runnable Spring Boot project demonstrating the core Spring AI 1.x concepts:

- **ChatClient** – basic chat calls (`/ask`)
- **Structured output** – mapping model responses to a POJO (`/review`)
- **Tool calling** – letting the model invoke your Java methods (`/weather`)
- **Chat memory** – multi-turn conversations (`/chat`)
- **RAG** – retrieval-augmented generation with an in-memory vector store (`/rag`)

No external database or vector DB is required — memory and RAG both use
in-memory implementations so you can run this immediately.

## Prerequisites

- Java 17+
- Maven 3.9+
- An OpenAI API key (or point `spring.ai.openai.base-url` at any
  OpenAI-compatible endpoint, e.g. a local Ollama proxy)

## Setup

```bash
export OPENAI_API_KEY=sk-...
```

## Run

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`.

## Try it

```bash
# Basic chat
curl "http://localhost:8080/ask?question=Why+is+the+sky+blue"

# Structured output -> JSON mapped from MovieReview record
curl "http://localhost:8080/review?movie=Inception"

# Tool calling -> model calls WeatherTools.getTemperature(...)
curl "http://localhost:8080/weather?city=Antwerp"

# Multi-turn memory -> use the same conversationId across calls
curl "http://localhost:8080/chat?conversationId=obul&message=My+name+is+Obul"
curl "http://localhost:8080/chat?conversationId=obul&message=What+is+my+name"

# RAG -> retrieves from the in-memory VectorStore seeded in RagConfig
curl "http://localhost:8080/rag?question=What+is+Spring+AI+good+for"
```

## Project layout

```
spring-ai-demo/
├── pom.xml
└── src/main/
    ├── java/com/example/springaidemo/
    │   ├── SpringAiDemoApplication.java
    │   ├── controller/
    │   │   ├── ChatController.java      # ask / review / weather
    │   │   ├── MemoryController.java    # chat with memory
    │   │   └── RagController.java       # rag
    │   ├── tools/WeatherTools.java      # @Tool-annotated methods
    │   ├── config/
    │   │   ├── MemoryConfig.java        # ChatMemory bean
    │   │   └── RagConfig.java           # VectorStore bean, seeds docs
    │   └── dto/MovieReview.java         # structured output target
    └── resources/application.yml
```

## Notes

- This targets **Spring AI 1.0.1** on **Spring Boot 3.3.5** — the stable GA
  line. Spring AI 2.0.0 GA (released June 2026) targets Spring Boot 4 /
  Spring Framework 7 and renames a few APIs (e.g. `ToolCallAdvisor` ->
  `ToolCallingAdvisor`). If your target job/project is on Boot 4, bump the
  parent version and check the Spring AI upgrade notes for the renamed
  classes.
- Swap `spring-ai-starter-model-openai` for `spring-ai-starter-model-anthropic`,
  `spring-ai-starter-model-ollama`, etc. to use a different provider — the
  `ChatClient` code doesn't change.
- For production, replace `InMemoryChatMemoryRepository` and
  `SimpleVectorStore` with persistent backends (JDBC/Redis for memory,
  pgvector/Elasticsearch/Redis for vectors).
