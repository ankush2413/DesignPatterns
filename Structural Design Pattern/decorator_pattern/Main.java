package decorator_pattern;
import decorator_pattern.interfaces.Notification;

public class Main{
    
    public static void main(String [] args)
    {
        Notification notification = new LoggingDecorator(new EmailNoti());

        notification.send("Hello World!");
    }
}