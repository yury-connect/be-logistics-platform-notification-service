package com.innowise.logistics.notificationservice.utils;

import java.util.Map;

public enum NotificationTemplate {

    ORDER_CREATED("Заказ {orderId} успешно создан. Ожидайте выставления счета."),
    ORDER_PAID("Оплата получена. Заказ {orderId} передан на склад."),
    INCIDENT_CRITICAL("Критическое ЧП: доставка {deliveryId} отменена. Средства будут возвращены."),
    DELIVERED("Заказ {orderId} доставлен. Спасибо, что вы с нами!");

    private final String template;

    NotificationTemplate(String template) {
        this.template = template;
    }

    public String generate(Map<String, Object> payload) {
        String result = template;
        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            result = result.replace("{" + entry.getKey() + "}",
                    String.valueOf(entry.getValue()));
        }
        return result;
    }
}