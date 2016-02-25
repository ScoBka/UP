import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Messages {

    private List<Message> messages;
    private Message message;
    private ReadWriteFile rwf;

    public Messages() {
        messages = new ArrayList<>();
        message = new Message();
        rwf = new ReadWriteFile();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addMsg() {
        String id = message.generateId();
        String msg = message.enterTextMessage();
        String author = message.enterAuthor();
        long timestamp = message.receiveTimestamp();
        Message mes = new Message(id, msg, author, timestamp);
        addMessage(mes);
    }

    public void printMsg() {
        if (!messages.isEmpty()) {
            for (Message m : messages) {
                System.out.println(m);
            }
        } else {
            System.out.println("Empty history");
            rwf.log("History is empty");
        }
    }

    public void deleteMessagesID(String id) {
        Iterator it = messages.iterator();
        while (it.hasNext()) {
            Message next = (Message)it.next();
            if (next.getId().equals(id)) {
                it.remove();
            }
        }
    }

    public void showMessageChronology() {
        Comp comp = new Comp();
        if (!messages.isEmpty()) {
            Collections.sort(messages, comp);
            for (Message message : messages) {
                System.out.println(message);
            }
        } else {
            rwf.log("There's no message history");
        }
    }

    public void searchAuthor(String author) {
        for (Message message : messages) {
            if (message.getAuthor().equals(author)) {
                System.out.println(message);
            }
        }
    }

    public void searchFragment(String fragment) {
        for (Message message : messages) {
            if (message.getMessage().contains(fragment)) {
                System.out.println(message);
            }
        }
    }

    public void searchPattern(String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(message.getMessage());
        for (Message item : messages) {
            if (m.find()) {
                System.out.println(item);
            }
            else {
                System.out.println("Message not found");
            }
        }
    }

    public void searchDate(Date minDate, Date maxDate) {
        int i = 0;
        for (Message item : messages) {
            if(minDate.getTime() < item.getTimestamp() && maxDate.getTime() > item.getTimestamp()) {
                System.out.println(item);
                i++;
            }
        }
        if (i == 0) {
            System.out.println("No messages in this time period");
            rwf.log("Messages didn't find");
        }
    }

    public List<Message> getMessagesList() {
        return messages;
    }
}





















































