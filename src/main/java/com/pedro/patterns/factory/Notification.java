package com.pedro.patterns.factory;

/**
 * Common interface for all notifications created by the factory.
 */
public interface Notification {
    void send(String recipient, String message);
    String getChannelName();
}
