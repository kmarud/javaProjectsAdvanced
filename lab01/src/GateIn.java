import java.io.IOException;
import java.rmi.RemoteException;
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
    static IControler stub;

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
            stub = (IControler) registry.lookup(args[1]);
            stub.register(gateIn);
            System.out.println("Gateway has now ID: " + gateIn.getID());
            gateIn.start();
            while (true){
                System.out.println(gateIn.statement);
                if(gateIn.isActive()){
                    System.out.println("Press enter to get ticket ");
                    int inChar = System.in.read();
                    if(inChar == 113){
                        gateIn.switchOfGate();
                        return;
                    }
                    if(gateIn.isActive())
                        gateIn.getTicketProcedure();
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

    private void getTicketProcedure() throws IOException {
        clearScreen();
        Ticket ticket = this.getTicket();
        System.out.println("Your ticket has ID: " + ticket.getId());
        System.out.println("Please press enter");
        System.in.read();
        clearScreen();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void switchOfGate(){
        try {
            stop();
            stub.unregister(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
