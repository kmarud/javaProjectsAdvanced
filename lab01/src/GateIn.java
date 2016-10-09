import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kamil on 08.10.16.
 */
public class GateIn extends Gate implements IGateIn {
    private static int ticketCounter=0;

    @Override
    public Ticket getTicket() {
        DateFormat df = new SimpleDateFormat("MMddyyHHmm");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        return new Ticket(Integer.parseInt(reportDate), Calendar.getInstance());
    }

    public static void main(String[] args) {        //arg0 - port, arg1 - nazwa kontrolera, arg2 - adres

        try{
            System.setSecurityManager(new SecurityManager());
            GateIn gateIn = new GateIn();
            UnicastRemoteObject.exportObject(gateIn, Integer.parseInt(args[0]));
            Registry registry = LocateRegistry.getRegistry(args[2]);
            IControler stub = (IControler) registry.lookup(args[1]);
            stub.register(gateIn);
            System.out.println("Gateway has now ID: " + gateIn.getID());

            gateIn.start();

            while (true){

                System.in.read();
                System.out.println(gateIn.statement);
                if(gateIn.isActive){
                    System.out.println("Press enter to get ticket ");
                    System.in.read();
                    Ticket ticket = gateIn.getTicket();
                    System.out.println("Your ticket has ID: " + ticket.getId());
                    Thread.sleep(5000);
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

    private void get(){

    }
}
