import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamil on 08.10.16.
 */
public class GateOut extends Gate implements IGateOut{

    private static final double PLN_PER_MINUTE = 1.0;
    static IControler stub;

    @Override
    public double checkTicket(Ticket ticket) {
        Calendar calendar  = Calendar.getInstance();
        System.out.println("Actual time:" + calendar.getTime());

        long minutes = TimeUnit.MILLISECONDS.toMinutes(calendar.getTime().getTime() - ticket.getCalendar().getTime().getTime());
        double fee = PLN_PER_MINUTE * minutes;
        System.out.println("Counted minutes: " + minutes );

        Bill bill = new Bill(ticket.getId(), fee);

        try {
            stub.add(bill);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return fee;
    }

    public static void main(String[] args) { //arg0 - port, arg1 - nazwa kontrolera, arg2 - adres
        final int PORT = Integer.parseInt(args[0]);
        final String CONTROLLER_NAME = args[1];
        final String ADDRESS = args[2];

        try{
            GateOut gateOut = new GateOut();
            UnicastRemoteObject.exportObject(gateOut, PORT);
            Registry registry = LocateRegistry.getRegistry(ADDRESS);
            stub = (IControler) registry.lookup(CONTROLLER_NAME);
            stub.register(gateOut);
            System.out.println("Gateway has now ID: " + gateOut.getID());
            gateOut.start();

            while (true){

                System.out.println(gateOut.statement);
               // System.in.read();
                if(gateOut.isActive){
                    System.out.println("Press enter to check ticket ");
                    System.in.read();
                    Scanner scanner = new Scanner(System.in);
                    clearScreen();
                    //scanner.nextLine();
                    System.out.println("Please enter ticket ID: ");

                    int localID = scanner.nextInt();
                    //scanner.nextLine();
                    //System.out.println("Please enter date from the ticket: dd/MM/yyy HH:mm ");
                    //String localDate = scanner.nextLine();

                    Date time1 = new SimpleDateFormat("MMddyyHHmm").parse(Integer.toString(localID));
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(time1);


                    Ticket ticket = new Ticket(localID, calendar1);
                    Double fee = gateOut.checkTicket(ticket);
                    System.out.println("Counted fee: " + fee + " PLN");
                    System.out.println("Please pay and press enter");
                    System.in.read();
                    //Thread.sleep(5000);
                    clearScreen();
                }
                else {
                    System.out.println("Sorry, gate is temporarily unavailable");
                }
            }
            //Thread.sleep(30*1000);
            //stub.unregister(gate);
            //System.out.println("Wyrejestrowano bramke !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
