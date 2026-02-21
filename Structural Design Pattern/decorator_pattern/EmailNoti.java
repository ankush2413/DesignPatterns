package decorator_pattern;

import decorator_pattern.interfaces.Notification;

public class EmailNoti implements Notification {
    
    @Override
    public void send(String message) {
        System.out.println("Sending Email Notification: " + message);
    }
}