package notificationsystem;
import notificationsystem.interfaces.NotificationSenderFactory;
import notificationsystem.interfaces.Channel;
import notificationsystem.notification.EmailNoti;
import notificationsystem.interfaces.Notification;
import java.time.LocalDateTime;

public class Main {

    public void main(String []args)
    { 
        //Notification email = new EmailNoti("user@example.com", "Hello!", "Welcome");
        
        NotificationSenderFactory factory = new DefaultNotificationSenderFactory();
        NotificationDispatcher dispatcher = new NotificationDispatcher(factory);

        Notification email = new EmailNoti("user@example.com", "Hello!", "Welcome");
        dispatcher.dispatch(email);
        dispatcher.schedule(email, LocalDateTime.now().plusHours(2));

      //  Notification sms = new SMS("1234567890", "Hi there");
       // dispatcher.dispatch(sms);
        //dispatcher.schedule(sms, LocalDateTime.now().plusMinutes(30));  // Will print unsupp
    }
}