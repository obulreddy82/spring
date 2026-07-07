package com.rajtechnologies.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RagConfig {

    // SimpleVectorStore keeps everything in memory - perfect for demos and tests.
    // Swap for PgVectorStore / RedisVectorStore / ElasticsearchVectorStore etc. in production.
    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore vectorStore = SimpleVectorStore.builder(embeddingModel).build();

        vectorStore.add(List.of(
                new Document("Spring AI is an application framework for AI engineering "
                        + "that provides a Spring-idiomatic way to build AI applications."),
                new Document("The ChatClient API is a fluent API for talking to chat models, "
                        + "similar in style to WebClient and RestClient."),
                new Document("Spring AI supports Retrieval-Augmented Generation (RAG) through "
                        + "the VectorStore abstraction and advisors like QuestionAnswerAdvisor."),
                new Document("Obul is preparing for senior Java roles in the Belgian market, "
                        + "focusing on Spring, PKI, and cloud-native architectures.")
        ));

        return vectorStore;
    }
}
