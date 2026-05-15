package com.innowise.logistics.notificationservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "notifications")
@Data
@NoArgsConstructor
public class Notification {

    @Id
    private String id;  // MongoDB использует String, не Long!

    @Indexed(unique = true)  // Уникальный индекс для защиты от дублей
    private String kafkaEventId;

    private Long userId;

    private Map<String, Object> payload;

    private String content;

    private Instant createdAt = Instant.now();

    private Instant sentAt;
}