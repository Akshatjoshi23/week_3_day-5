package notifications;

public class EmailChannel implements NotificationChannel {
    @Override
    public void send(Message message) {
        System.out.println("Email to " + message.getRecipient() + ": " + message.getText());
    }
}
