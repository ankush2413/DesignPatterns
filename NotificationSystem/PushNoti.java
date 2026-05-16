package NotificationSystem;

import NotificationSystem.enums.Urgency;
import NotificationSystem.interfaces.NotificationStrategy;

public class PushNoti implements NotificationStrategy {
    
    @Override
    public void send(String recipient, String content, Urgency urgency) {
        System.out.println("[PUSH] Sending to Device Token: " + recipient + " | Urgency: " + urgency + " | Message: " + content);
    }
}
