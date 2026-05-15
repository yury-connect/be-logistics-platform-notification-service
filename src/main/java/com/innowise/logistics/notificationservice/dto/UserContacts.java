package com.innowise.logistics.notificationservice.dto;

import lombok.Data;

@Data
public class UserContacts {
    private Long userId;
    private String email;
    private String telegramId;
    private String phone;
}