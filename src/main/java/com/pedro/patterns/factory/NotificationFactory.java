package com.pedro.patterns.factory;

/**
 * Factory class responsible for creating Notification instances based on channel type.
 * Decouples client code from concrete implementations.
 */
public class NotificationFactory {
    public static Notification createNotification(String channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel type cannot be null.");
        }
        return switch (channel.trim().toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "SMS" -> new SmsNotification();
            default -> throw new IllegalArgumentException("Unsupported notification channel: " + channel);
        };
    }
}
