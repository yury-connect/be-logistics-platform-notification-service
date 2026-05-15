package com.innowise.logistics.notificationservice.repository;

import com.innowise.logistics.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    boolean existsByKafkaEventId(String kafkaEventId);
}