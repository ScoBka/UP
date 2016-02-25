import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFile {

    public List<Message> readJSONFile(String fileName) throws IOException {
        ArrayList<Message> messages;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<Message>>() {}.getType();

        messages = gson.fromJson(br, collectionType);

        return messages;
    }

    public void writeFile(String fileName, List<Message> messages) throws IOException, ParseException {
        PrintStream ps = new PrintStream(new FileOutputStream(fileName));
        if (messages.size() != 0) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ps.println(gson.toJson(messages));
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


