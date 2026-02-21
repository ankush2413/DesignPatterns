package notificationsystem.notification;

import  notificationsystem.interfaces.Notification;
import notificationsystem.interfaces.Channel;


public final class EmailNoti implements Notification{

    private final String body;
    private final String toEmail;
    private final String subject;

    public EmailNoti(String toEmail,String body,String subject)
    {
        this.body = body;
        this.toEmail =toEmail;
        this.subject = subject;
    }

    @Override
    public Channel getChannel()
    {
        return Channel.EMAIL;
    }

    @Override
    public String getRecipient()
    {
        return toEmail;
    }

    @Override
    public String getContent()
    {
        return body;
    }

    public String getSubject()
    {
        return subject;
    }

}