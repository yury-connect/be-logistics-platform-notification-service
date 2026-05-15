package com.innowise.logistics.notificationservice.dto;

import lombok.Data;
import java.util.Map;

@Data
public class NotificationEvent {
    private String eventId;
    private Long userId;
    private String trackingNumber;
    private String eventStatus;  // ORDER_CREATED, PAID, INCIDENT и т.д.
    private Map<String, Object> messagePayload;
    private String priority;     // LOW, HIGH
}