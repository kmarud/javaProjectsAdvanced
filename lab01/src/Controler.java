
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by kamil on 06.10.16.
 */
public class Controler implements IControler {

    private ArrayList<IGate> gates = new ArrayList<>();
    private ArrayList<Bill> bills = new ArrayList<>();
    private static int count = 0;

    @Override
    public ArrayList<IGate> getGates() throws RemoteException {
        return gates;
    }

    @Override
    public ArrayList<Bill> getBills() throws RemoteException {
        return bills;
    }

    @Override
    public boolean register(Object o) throws RemoteException {
        IGate g = (IGate)o;
        System.out.println("Connected gate with id: " +  count);
        g.setID(count);
        count++;
        return gates.add(g);
    }

    @Override
    public boolean unregister(Object o) throws RemoteException {
        IGate g = (IGate)o;
        System.out.println("Disconnected gate with id: : " +  g.getID());
        return gates.remove(g);
    }

    @Override
    public void add(Bill bill) throws RemoteException {
        bills.add(bill);
        System.out.println("Added bill with id " + bill.getId() + " with amount  " + bill.getAmount() + " PLN");
    }

    public static void main(String[] args) {
        final int PORT = Integer.parseInt(args[0]);
        final String CONTROLLER_NAME = args[1];

        Controler controler = new Controler();
        try {
            IControler stub = (IControler)UnicastRemoteObject.exportObject(controler, PORT);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(CONTROLLER_NAME, stub);
            System.out.println("Server ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
