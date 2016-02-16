package LW_1_chat;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Messages {

    private List<Message> messages;
    private Message message;

    public Messages() {
        messages = new ArrayList<>();
        message = new Message();
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
        for (Message m : messages) {
            System.out.println(m);
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
        Collections.sort(messages, comp);
        for (Message message : messages) {
            System.out.println(message);
        }
    }

    public void searchAuthor(String author) {
        for (Message message : messages) {
            if (message.getAuthor().equals(author)) {
                System.out.println(message);
            }
        }
    }

    public void searchMessageFragment(String fragment) {

    }

    public List<Message> getMessagesList() {
        return messages;
    }
}





















































