package com.pedro.patterns.factory;

public class EmailNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[EMAIL DISPATCH] -> To: " + recipient + " | Body: " + message);
    }

    @Override
    public String getChannelName() {
        return "EMAIL";
    }
}
