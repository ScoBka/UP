package LW_1_chat;

import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Processing pr = new Processing();
        ReadWriteFile rwf = new ReadWriteFile();
        try {
            rwf.updateFile();
            rwf.log("Run Application");
            pr.run();
        } catch(IOException e) {
            rwf.log("thrown IOException");
            System.out.println("IO Error, check if file exists");
        } catch(ParseException f) {
            rwf.log("thrown ParseException");
            System.out.println("Error in parsing");
        } catch(IllegalArgumentException g) {
            rwf.log("thrown IllegalArgumentException");
            System.out.println("Illegal argument");
        } catch(InputMismatchException h) {
            rwf.log("thrown InputMismatchException");
            System.out.println("Illegal argument");
        } finally {
            rwf.log("Ending application");
        }
    }
}
