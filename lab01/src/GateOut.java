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

    private final double zlPerMinute = 1.0;
    static IControler stub;
    @Override
    public double checkTicket(Ticket ticket) {
        validateTicketId(ticket.id);



        Calendar calendar  = Calendar.getInstance();
        System.out.println("actual time :" + calendar.getTime());

        //String string1 = "06/10/2016 22:06";
        //String string1="22:03:13";
        //Date time1 = new SimpleDateFormat("dd/MM/yyy HH:mm").parse(string1);
        //Calendar calendar1 = Calendar.getInstance();
        //calendar1.setTime(time1);

        //Calendar cal =
        //System.out.println(calendar1.compareTo(calendar));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(calendar.getTime().getTime() - ticket.calendar.getTime().getTime());
        System.out.println(" minutes: " + minutes );

        Bill bill = new Bill(ticket.id, zlPerMinute * minutes);

        try {
            stub.add(bill); // na tym skonczylem
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return zlPerMinute * minutes;



        //ticket.calendar.getTime().getTime();
        //return 0;
    }

    public static void main(String[] args) {
        try{
            GateOut gateOut = new GateOut();
            UnicastRemoteObject.exportObject(gateOut, 0);
            Registry registry = LocateRegistry.getRegistry("localhost");
            stub = (IControler) registry.lookup("Server");
            stub.register(gateOut);
            System.out.println("Gateway has now ID: " + gateOut.getID());

            gateOut.start();


            while (true){

                System.out.println(gateOut.statement);
                System.in.read();
                if(gateOut.isActive){
                    System.out.println("Press enter to check ticket ");
                    System.in.read();
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please enter ID");

                    int localID = scanner.nextInt();
                    //scanner.nextLine();
                    //System.out.println("Please enter date from the ticket: dd/MM/yyy HH:mm ");
                    //String localDate = scanner.nextLine();

                    Date time1 = new SimpleDateFormat("MMddyyHHmm").parse(Integer.toString(localID));
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(time1);


                    Ticket ticket = new Ticket(localID, calendar1);
                    Double toPay = gateOut.checkTicket(ticket);
                    System.out.println("Please pay " + toPay + " zl and press enter");
                    //Thread.sleep(5000);
                    System.in.read();
                }
                else {
                    System.out.println("sorry !");
                }

            }

            //Thread.sleep(30*1000);
            //stub.unregister(gate);
            //System.out.println("Wyrejestrowano bramke !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateTicketId(int id){
        ///
    }
}
