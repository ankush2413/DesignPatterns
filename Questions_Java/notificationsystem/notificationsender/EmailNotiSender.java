package notificationsystem.notificationsender;
import notificationsystem.interfaces.SchedulableNotification;
import notificationsystem.interfaces.Notification;
import java.time.LocalDateTime;

public class EmailNotiSender implements SchedulableNotification {

    @Override
    public void send(Notification notification) {
        System.out.println("Sending EMAIL to " + notification.getRecipient());
    }

    @Override
    public void schedule(Notification notification, LocalDateTime dateTime) {
        System.out.println("Scheduling EMAIL to " + notification.getRecipient() + " at " + dateTime);
    }
}