package NotificationSystem.interfaces;

import NotificationSystem.enums.Urgency;

public interface NotificationStrategy {
    void send(String recipient, String content, Urgency urgency);
}
