package com.innowise.logistics.notificationservice.entity;

import com.innowise.logistics.notificationservice.config.JsonbConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String kafkaEventId;

    private Long userId;

    @Convert(converter = JsonbConverter.class)
    private Map<String, Object> payload;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Instant createdAt = Instant.now();
    private Instant sentAt;   // null = не отправлено или ошибка
}
