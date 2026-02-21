package notificationsystem.interfaces;
import java.util.Optional;

public interface NotificationSenderFactory {
    Optional<NotificationSender> getSender(Channel channel);
    Optional<SchedulableNotification> getSchedulableSender(Channel channel);
}