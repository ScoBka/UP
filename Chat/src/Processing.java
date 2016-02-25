import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Processing {

    ReadWriteFile rwf;
    Messages messages;

    public Processing() {
        messages = new Messages();
        rwf = new ReadWriteFile();
    }

    public void menu() {
        System.out.println("1.Загрузка сообщений из файла");
        System.out.println("2.Сохранение сообщения в файл");
        System.out.println("3.Добавление сообщения");
        System.out.println("4.Просмотр истории сообщений в хронологическом порядке");
        System.out.println("5.Удаление сообщения по идентификатору");
        System.out.println("6.Показать текущую историю сообщений");
        System.out.println("7.Поиск сообщений по автору");
        System.out.println("8.Поиск сообщений по лексеме");
        System.out.println("9.Поиск сообщений по регулярному выражению");
        System.out.println("10.Поиск сообщений по дате");
        System.out.println("0.Выйти из приложения");
    }

    public void run() throws IOException, ParseException, IllegalArgumentException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean f = true;
        while (f) {
            System.out.println("Choose :");
            menu();
            switch(Integer.parseInt(br.readLine())) {
                case(0) : {
                    rwf.log("Exit application");
                    f = false;
                    break;
                }
                case(1) : {
                    rwf.log("Downloading from file");
                    System.out.println("Enter file name (or address), which contains your messages : ");
                    downloadMessages(br.readLine());
                    break;
                }
                case(2) : {
                    rwf.log("Saving messages in file");
                    System.out.println("Enter file name");
                    rwf.writeFile(br.readLine(), messages.getMessagesList());
                    break;
                }
                case(3) : {
                    rwf.log("Adding new message");
                    messages.addMsg();
                    break;
                }
                case(4) : {
                    rwf.log("Showing message history in chronology");
                    messages.showMessageChronology();
                    break;
                }
                case(5) : {
                    rwf.log("Deleting message by ID");
                    System.out.println("Enter ID : ");
                    messages.deleteMessagesID(br.readLine());
                    break;
                }
                case(6) : {
                    rwf.log("Showing current messages history");
                    messages.printMsg();
                    break;
                }
                case(7) : {
                    rwf.log("Searching by author");
                    System.out.println("Enter author : ");
                    messages.searchAuthor(br.readLine());
                    break;
                }
                case(8) : {
                    rwf.log("Searching by fragment");
                    System.out.println("Enter fragment : ");
                    messages.searchFragment(br.readLine());
                    break;
                }
                case(9) : {
                    rwf.log("Searching by regular expression");
                    System.out.println("Enter regular expression : ");
                    messages.searchPattern(br.readLine());
                    break;
                }
                case(10) : {
                    rwf.log("Searching by data");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    System.out.println("Enter minDate (Example : 12.02.2007) : ");
                    Date minDate = sdf.parse(br.readLine());
                    System.out.println("Enter maxDate : ");
                    Date maxDate = sdf.parse(br.readLine());
                    messages.searchDate(minDate, maxDate);
                    break;
                }
                default : {
                    System.out.println("Wrong menu number");
                    rwf.log("Wrong menu number");
                    return;
                }
            }
        }
    }

    public void downloadMessages(String fileName) throws IOException {
        List<Message> msg = rwf.readJSONFile(fileName);
        for (Message item : msg) {
            messages.addMessage(item);
        }
    }
}
