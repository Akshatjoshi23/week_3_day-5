package notifications;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationChannel channel;          // Mockito builds this fake for us

    @Test
    void notify_sendsMessageThroughChannelExactlyOnce() {
        NotificationService service = new NotificationService(channel);
        Message message = new Message("asha@x.com", "Your order has shipped");

        service.notify(message);

        verify(channel, times(1)).send(message);
    }

    @Test
    void notify_nullMessage_throwsAndSendsNothing() {
        NotificationService service = new NotificationService(channel);

        assertThrows(IllegalArgumentException.class, () -> service.notify(null));

        verifyNoInteractions(channel);
    }

    @Test
    void notify_whenChannelFails_exceptionPropagates() {
        Message message = new Message("98xxxxxx01", "Hi");
        doThrow(new IllegalStateException("gateway down")).when(channel).send(message);
        NotificationService service = new NotificationService(channel);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class, () -> service.notify(message));

        assertEquals("gateway down", ex.getMessage());
    }

    @Test
    void constructor_nullChannel_isRejected() {
        assertThrows(NullPointerException.class, () -> new NotificationService(null));
    }

    @Test
    void notifyAll_sendsEveryMessage() {
        NotificationService service = new NotificationService(channel);
        List<Message> messages = List.of(
                new Message("a@x.com", "One"),
                new Message("b@x.com", "Two"),
                new Message("c@x.com", "Three"));

        service.notifyAll(messages);

        verify(channel, times(3)).send(any(Message.class));
    }

    @Test
    void notifyAll_nullList_throwsAndSendsNothing() {
        NotificationService service = new NotificationService(channel);

        assertThrows(IllegalArgumentException.class, () -> service.notifyAll(null));

        verifyNoInteractions(channel);
    }
}
