package LW_1_chat;


import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Processing {

    ReadWriteFile rwf;
    Messages messages;

    public Processing() {
        messages = new Messages();
        rwf = new ReadWriteFile();
    }

    public void menu() {
        System.out.println("1.Загрузка сообщений из файла (Пока не работает)");
        System.out.println("2.Сохранение сообщения в файл");
        System.out.println("3.Добавление сообщения");
        System.out.println("4.Просмотр истории сообщений в хронологическом порядке");
        System.out.println("5.Удаление сообщения по идентификатору");
        System.out.println("6.Показать текущую историю сообщений");
        System.out.println("6.Выйти из приложения");
    }

    public void run() throws IOException, ParseException, IllegalArgumentException {
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        while (f) {
            System.out.println("Choose :");
            menu();
            switch(sc.nextInt()) {
                case(0) : {
                    rwf.log("Exit application");
                    f = false;
                    break;
                }
                case(1) : {
                    rwf.log("Downloading from file");
                    rwf.readJSONFile("Messages.json", messages);
                    break;
                }
                case(2) : {
                    rwf.log("Saving messages if file");
                    rwf.writeFile("Messages.json", messages.getMessagesList());
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
                    messages.deleteMessagesID(sc.next());
                    break;
                }
                case(6) : {
                    rwf.log("Showing current messages");
                    messages.printMsg();
                    break;
                }
            }
        }
    }
}
