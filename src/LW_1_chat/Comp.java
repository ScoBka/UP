package LW_1_chat;

import java.util.Comparator;

public class Comp implements Comparator<Message> {
    @Override
    public int compare (Message a, Message b) {
        if (a.getTimestamp() >= b.getTimestamp()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
