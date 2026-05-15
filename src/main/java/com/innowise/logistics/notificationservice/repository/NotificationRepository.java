package com.innowise.logistics.notificationservice.repository;

import com.innowise.logistics.notificationservice.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    boolean existsByKafkaEventId(String kafkaEventId);
}