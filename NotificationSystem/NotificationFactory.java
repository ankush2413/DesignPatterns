package NotificationSystem;

import NotificationSystem.interfaces.NotificationStrategy;
import NotificationSystem.enums.Type;

public class NotificationFactory {
    public static NotificationStrategy getStrategy(Type type) {
        switch (type) {
            case EMAIL:
                return new EmailNoti();
            case SMS:
                return new SMSNoti();
            case PUSH:
                return new PushNoti();
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}
