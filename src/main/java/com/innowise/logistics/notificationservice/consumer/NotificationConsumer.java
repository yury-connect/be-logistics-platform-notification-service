package com.innowise.logistics.notificationservice.consumer;

import com.innowise.logistics.notificationservice.client.UserServiceClient;  // 👈 короткий импорт
import com.innowise.logistics.notificationservice.dto.NotificationEvent;
import com.innowise.logistics.notificationservice.dto.UserContacts;
import com.innowise.logistics.notificationservice.entity.Notification;
import com.innowise.logistics.notificationservice.utils.NotificationTemplate;
import com.innowise.logistics.notificationservice.repository.NotificationRepository;
import com.innowise.logistics.notificationservice.service.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class NotificationConsumer {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserServiceClient userClient;

    @Autowired
    private NotificationSender sender;

    @KafkaListener(topics = "delivery.notify-event")
    public void consume(NotificationEvent event) {

        // 1. Защита от дублей
        if (repository.existsByKafkaEventId(event.getEventId())) {
            log.info("Duplicate event: {}", event.getEventId());
            return;
        }

        // 2. Сохраняем
        Notification notification = new Notification();
        notification.setKafkaEventId(event.getEventId());
        notification.setUserId(event.getUserId());
        notification.setPayload(event.getMessagePayload());
        notification = repository.save(notification);

        // 3. Получаем контакты пользователя
        UserContacts contacts = userClient.getContacts(event.getUserId());

        // 4. Генерируем текст
        String content = NotificationTemplate.valueOf(event.getEventStatus())
                .generate(event.getMessagePayload());
        notification.setContent(content);

        // 5. Отправляем во все каналы
        boolean anySuccess = false;

        if (contacts.getEmail() != null) {
            if (sender.sendEmail(contacts.getEmail(), content)) {
                anySuccess = true;
            }
        }

        if (contacts.getTelegramId() != null) {
            if (sender.sendTelegram(contacts.getTelegramId(), content)) {
                anySuccess = true;
            }
        }

        if (contacts.getPhone() != null) {
            if (sender.sendSms(contacts.getPhone(), content)) {
                anySuccess = true;
            }
        }

        // 6. Если хоть что-то отправилось — считаем успехом
        if (anySuccess) {
            notification.setSentAt(Instant.now());
            repository.save(notification);
        }
    }
}
