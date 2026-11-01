package notifications;

import java.util.List;
import java.util.Objects;

public class NotificationService {
    private final NotificationChannel channel;

    public NotificationService(NotificationChannel channel) {
        this.channel = Objects.requireNonNull(channel, "channel is required");
    }

    public void notify(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("message is required");
        }
        channel.send(message);
    }

    public void notifyAll(List<Message> messages) {
        if (messages == null) {
            throw new IllegalArgumentException("messages is required");
        }
        for (Message message : messages) {
            notify(message);          // reuses the null check and the sending logic
        }
    }
}
