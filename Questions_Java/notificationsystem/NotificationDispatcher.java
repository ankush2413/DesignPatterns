package notificationsystem;
import notificationsystem.interfaces.NotificationSenderFactory;
import notificationsystem.interfaces.Notification;
import java.time.LocalDateTime;

public class NotificationDispatcher {
    private final NotificationSenderFactory senderFactory;

    public NotificationDispatcher(NotificationSenderFactory senderFactory) {
        this.senderFactory = senderFactory;
    }

    public void dispatch(Notification notification) {
        senderFactory.getSender(notification.getChannel())
            .ifPresentOrElse(
                sender -> sender.send(notification),
                () -> System.out.println("Unsupported notification channel.")
            );
    }

    public void schedule(Notification notification, LocalDateTime dateTime) {
        senderFactory.getSchedulableSender(notification.getChannel())
            .ifPresentOrElse(
                sender -> sender.schedule(notification, dateTime),
                () -> System.out.println("Scheduling not supported for channel: " + notification.getChannel())
            );
    }
}