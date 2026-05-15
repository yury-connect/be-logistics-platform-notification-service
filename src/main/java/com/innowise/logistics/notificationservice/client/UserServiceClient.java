package com.innowise.logistics.notificationservice.client;

import com.innowise.logistics.notificationservice.dto.UserContacts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user.service.url:http://localhost:8081}")
public interface UserServiceClient {

    @GetMapping("/api/v1/users/{userId}/contacts")
    UserContacts getContacts(@PathVariable("userId") Long userId);
}