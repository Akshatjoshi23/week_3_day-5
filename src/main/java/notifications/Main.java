package notifications;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // main is the composition root: it chooses which channel each service uses
        NotificationService email = new NotificationService(new EmailChannel());
        NotificationService sms = new NotificationService(new SmsChannel());

        email.notify(new Message("asha@x.com", "Your order has shipped"));
        sms.notify(new Message("98xxxxxx01", "Your order has shipped"));

        // The new notifyAll method: several messages through one channel
        email.notifyAll(List.of(
                new Message("riya@x.com", "Welcome!"),
                new Message("meera@x.com", "Welcome!")));
    }
}
