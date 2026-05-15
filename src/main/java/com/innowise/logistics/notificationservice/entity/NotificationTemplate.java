package com.innowise.logistics.notificationservice.entity;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public enum NotificationTemplate {

    ORDER_CREATED("Заказ {orderId} успешно создан. Ожидайте выставления счета."),
    ORDER_PAID("Оплата получена. Заказ {orderId} передан на склад."),
    INCIDENT_CRITICAL("Критическое ЧП: доставка {deliveryId} отменена."),
    DELIVERED("Заказ {orderId} доставлен. Спасибо!");

    private final String template;

    public String generate(Map<String, Object> payload) {
        String result = template;
        for (var entry : payload.entrySet()) {
            result = result.replace("{" + entry.getKey() + "}",
                    String.valueOf(entry.getValue()));
        }
        return result;
    }
}
