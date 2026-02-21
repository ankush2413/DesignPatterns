package package notificationsystem.notification;

import  notificationsystem.interfaces.Notification;
import notificationsystem.interfaces.Channel;

public class PushNoti implements Notification{

    private final String toDeviceId;
    private final String title;
    private final String payload;

    public PushNoti(String toDeviceId, String title, String payload) {
        this.toDeviceId = toDeviceId;
        this.title = title;
        this.payload = payload;
    }

    @Override
    public Channel getChannel() {
        return Channel.PUSH;
    }

    @Override
    public String getRecipient() {
        return toDeviceId;
    }

    @Override
    public String getContent() {
        return payload;
    }

    public String getTitle() {
        return title;
    }
}