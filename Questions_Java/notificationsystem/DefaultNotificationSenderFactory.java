package notificationsystem;
import notificationsystem.interfaces.Channel;
import java.util.*;
import notificationsystem.interfaces.NotificationSender;
import notificationsystem.interfaces.SchedulableNotification;
import notificationsystem.notification.EmailNoti;
import notificationsystem.interfaces.NotificationSenderFactory;
import notificationsystem.notificationsender.EmailNotiSender;

public class DefaultNotificationSenderFactory implements NotificationSenderFactory {
    private final Map<Channel, NotificationSender> senderMap;
    private final Map<Channel, SchedulableNotification> schedulableSenderMap;

    public DefaultNotificationSenderFactory() {
        this.senderMap = new HashMap<>();
        this.schedulableSenderMap = new HashMap<>();

        // Register senders externally
        EmailNotiSender emailSender = new EmailNotiSender();
       // PushNotificationSender pushSender = new PushNotificationSender();
        //SMSNotificationSender smsSender = new SMSNotificationSender();

        senderMap.put(Channel.EMAIL, emailSender);
       // senderMap.put(Channel.PUSH, pushSender);
        //senderMap.put(Channel.SMS, smsSender);

        schedulableSenderMap.put(Channel.EMAIL, emailSender);
       // schedulableSenderMap.put(Channel.PUSH, pushSender);
        //schedulableSenderMap.put(Channel.SMS, smsSender);
    }

    @Override
    public Optional<NotificationSender> getSender(Channel channel) {
        return Optional.ofNullable(senderMap.get(channel));
    }

    @Override
    public Optional<SchedulableNotification> getSchedulableSender(Channel channel) {
        return Optional.ofNullable(schedulableSenderMap.get(channel));
    }
}