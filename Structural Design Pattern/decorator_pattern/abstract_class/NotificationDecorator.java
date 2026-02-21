package decorator_pattern.abstract_class;
import decorator_pattern.interfaces.Notification;


public abstract class NotificationDecorator implements Notification{

    protected Notification wrappedNotification;

    public NotificationDecorator(Notification Notification) {
        this.wrappedNotification = Notification;
    }

    @Override
    public void send(String message)
    {
        wrappedNotification.send(message);
    }
}