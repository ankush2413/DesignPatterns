package notificationsystem.interfaces;


public interface Notification{
    Channel getChannel();
    String getRecipient();
    String getContent();

}