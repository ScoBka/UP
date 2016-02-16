package LW_1_chat;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class ReadWriteFile {

    public void readTXTFile(String fileName, Messages messages) throws IOException, ParseException {
        Scanner sc = new Scanner(new FileInputStream(new File(fileName)));
        sc.useDelimiter("[\\r\\n,]+");

        while(sc.hasNext()) {
            Scanner scanner = new Scanner(sc.next());
            scanner.useDelimiter(",");
            Message message = new Message(scanner.next(), scanner.next(), scanner.next(), scanner.nextLong());
            messages.addMessage(message);
        }
        sc.close();
    }

    public void readJSONFile(String fileName, Messages messages) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Gson gson = new Gson();
        while(br.readLine() != null) {
            messages.getMessagesList().add(gson.fromJson(br, Message.class));
        }
        br.close();
    }

    public void writeFile(String fileName, List<Message> messages) throws IOException, ParseException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintStream ps = new PrintStream(new FileOutputStream(fileName));
        if (messages.size() != 0) {
            for (Message item : messages) {
                gson.toJson(item, ps);
            }
        } else {
            System.out.println("No messages!");
            log("Can't find messages");
        }
        ps.close();
    }

    public void log(String text) {
        try (
            PrintStream ps = new PrintStream(new FileOutputStream("Log.txt", true))
        ) {
            ps.println(text);
        } catch (IOException e) {
            System.out.println("Error in logging");
        }
    }

    public void updateFile(){
        File file = new File("Log.txt");
        file.delete();
    }
}


