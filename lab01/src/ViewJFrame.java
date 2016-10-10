import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ViewJFrame{
    public static void main(String[] args) throws Exception {
            int inChar;
            System.out.println("Enter a Character:");
            try {
                inChar = System.in.read();
                System.out.print("You entered ");
                System.out.println(inChar);
            }
            catch (IOException e){
                System.out.println("Error reading from user");
            }
        }
}