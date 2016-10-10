import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
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
    private IControler stub;
    private Scanner scanner = new Scanner(System.in);

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

    public static void main(String[] args) {
        final int PORT = Integer.parseInt(args[0]);
        final String CONTROLLER_NAME = args[1];
        final String ADDRESS = args[2];

        try{
            GateOut gateOut = new GateOut();
            UnicastRemoteObject.exportObject(gateOut, PORT);
            Registry registry = LocateRegistry.getRegistry(ADDRESS);
            gateOut.stub = (IControler) registry.lookup(CONTROLLER_NAME);
            gateOut.stub.register(gateOut);
            System.out.println("Gate has now ID: " + gateOut.getID());
            gateOut.start();
            while (true){
                System.out.println("Gate IN ID: " + gateOut.getID());
                System.out.println(gateOut.statement);
                if(gateOut.isActive()){
                    System.out.println("Press enter to check ticket ");
                    int inChar = System.in.read();
                    if(inChar == 113){
                        gateOut.unregisterGate();
                        return;
                    }
                    if(gateOut.isActive())
                        gateOut.payTicketProcedure();
                }
                else {
                    System.out.println("Sorry, gate is temporarily unavailable");
                    System.in.read();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void payTicketProcedure() throws IOException, ParseException {
        clearScreen();
        System.out.println("Please enter ticket ID: ");

        int localID = scanner.nextInt();

        Date time1 = new SimpleDateFormat("MMddyyHHmm").parse(Integer.toString(localID));
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);

        Ticket ticket = new Ticket(localID, calendar1);
        Double fee = this.checkTicket(ticket);
        System.out.println("Counted fee: " + fee + " PLN");
        System.out.println("Please pay and press enter");
        System.in.read();
        clearScreen();
    }

    private void unregisterGate(){
        try {
            stop();
            stub.unregister(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
