package notifications;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MessageTest {

    @Test
    void createsMessageWithValidData() {
        Message m = new Message("asha@x.com", "Hello");

        assertEquals("asha@x.com", m.getRecipient());
        assertEquals("Hello", m.getText());
    }

    @Test
    void blankRecipient_isRejected() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class, () -> new Message(" ", "Hello"));

        assertEquals("recipient is required", ex.getMessage());
    }

    @Test
    void nullText_isRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Message("asha@x.com", null));
    }
}
