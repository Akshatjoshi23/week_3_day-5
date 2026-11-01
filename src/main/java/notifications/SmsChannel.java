package notifications;

public class SmsChannel implements NotificationChannel {
    @Override
    public void send(Message message) {
        System.out.println("SMS to " + message.getRecipient() + ": " + message.getText());
    }
}
