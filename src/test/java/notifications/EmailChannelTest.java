package notifications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class EmailChannelTest {

    @Test
    void send_printsRecipientAndText() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try (PrintStream capturedOut = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(capturedOut);
            new EmailChannel().send(new Message("recipient@example.com", "Delivery update"));
        } finally {
            System.setOut(originalOut);
        }

        assertEquals("Email to recipient@example.com: Delivery update" + System.lineSeparator(),
                output.toString(StandardCharsets.UTF_8));
    }
}