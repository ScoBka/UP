import java.util.Scanner;
import java.util.UUID;

public class Message {

    private String id;
    private String message;
    private String author;
    private long timestamp;

    public Message(String id, String message, String author, long timestamp) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.timestamp = timestamp;
    }

    public Message() {
        this.id = "";
        this.message = "";
        this.author = "";
        this.timestamp = 0;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }

    public String enterAuthor() {
        System.out.println("Enter author name : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String enterTextMessage() {
        System.out.println("Enter text message : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public long receiveTimestamp() {
        return System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "id : " + getId() + " Message : " + getMessage() + " Author : " + getAuthor() + " Timestamp : " + getTimestamp();
    }
}
