package com.pedro.patterns.factory;

public class SmsNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[SMS GATEWAY] -> To: " + recipient + " | Text: " + message);
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}
