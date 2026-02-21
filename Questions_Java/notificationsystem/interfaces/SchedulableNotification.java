package notificationsystem.interfaces;
import java.time.LocalDateTime;


public interface SchedulableNotification extends  NotificationSender {
    void schedule(Notification notification, LocalDateTime dateTime);
}