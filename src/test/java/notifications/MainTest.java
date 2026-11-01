package notifications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void main_sendsMessagesThroughEmailAndSmsChannels() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try (PrintStream capturedOut = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(capturedOut);
            Main.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        String expectedOutput = String.join(System.lineSeparator(),
                "Email to asha@x.com: Your order has shipped",
                "SMS to 98xxxxxx01: Your order has shipped",
                "Email to riya@x.com: Welcome!",
                "Email to meera@x.com: Welcome!") + System.lineSeparator();
        assertEquals(expectedOutput, output.toString(StandardCharsets.UTF_8));
    }
}