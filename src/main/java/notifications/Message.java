package notifications;

public final class Message {
    private final String recipient;
    private final String text;

    public Message(String recipient, String text) {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException("recipient is required");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("text is required");
        }
        this.recipient = recipient;
        this.text = text;
    }

    public String getRecipient() { return recipient; }
    public String getText()      { return text; }
}
