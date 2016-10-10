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

    //private static int ticketCounter=0;
    private IControler stub;

    @Override
    public Ticket getTicket() {
        DateFormat df = new SimpleDateFormat("MMddyyHHmm");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        return new Ticket(Integer.parseInt(reportDate), Calendar.getInstance());
    }

    public static void main(String[] args) {
        final int PORT = Integer.parseInt(args[0]);
        final String CONTROLLER_NAME = args[1];
        final String ADDRESS = args[2];

        try{
            System.setSecurityManager(new SecurityManager());
            GateIn gateIn = new GateIn();
            UnicastRemoteObject.exportObject(gateIn, PORT);
            Registry registry = LocateRegistry.getRegistry(ADDRESS);
            gateIn.stub = (IControler) registry.lookup(CONTROLLER_NAME);
            gateIn.stub.register(gateIn);
            System.out.println("Gate has now ID: " + gateIn.getID());
            gateIn.start();
            while (true){
                System.out.println("Gate out ID: " + gateIn.getID());
                System.out.println(gateIn.statement);
                if(gateIn.isActive()){
                    System.out.println("Press enter to get ticket ");
                    int inChar = System.in.read();
                    if(inChar == 113){
                        gateIn.unregisterGate();
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

    private void unregisterGate(){
        try {
            stop();
            stub.unregister(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
