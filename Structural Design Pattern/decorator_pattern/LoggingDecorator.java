package decorator_pattern;
import decorator_pattern.abstract_class.NotificationDecorator;
import decorator_pattern.interfaces.Notification;

public class LoggingDecorator extends NotificationDecorator {

    public LoggingDecorator(Notification Notification) {
        super(Notification);
    }

    @Override
    public void send(String message) {
        System.out.println("Logging: Sending Message");
        super.send(message);
    }
}