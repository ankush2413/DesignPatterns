package NotificationSystem;

import NotificationSystem.enums.Urgency;
import NotificationSystem.interfaces.NotificationStrategy;

public class EmailNoti implements NotificationStrategy{
    
    @Override
    public void send(String recipient, String content, Urgency urgency) {
        System.out.println("[EMAIL] Sending to: " + recipient + " | Urgency: " + urgency + " | Message: " + content);
    }
}
