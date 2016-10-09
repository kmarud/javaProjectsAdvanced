
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by kamil on 06.10.16.
 */
public class Controler implements IControler {

    ArrayList<IGate> gates = new ArrayList<>();
    ArrayList<Bill> bills = new ArrayList<>();

    static int count = 0;

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
        IGate g = (IGate )o;
        System.out.println("Nadano id: " +  count);
        g.setID(count);
        gates.add(g);
        count++;
        return true;
    }

    @Override
    public boolean unregister(Object o) throws RemoteException {
        IGate g = (IGate)o;
        System.out.println("Wyrejestrowano bramke o id : " +  g.getID());
        gates.remove(g);
        return false;
    }

    @Override
    public void add(Bill bill) throws RemoteException {
        bills.add(bill);
        System.out.println("Dodano racunek o id " + bill.getId() + " na kwote " + bill.getAmount());
    }

    public static void main(String[] args) {        //arg0 port, arg 1 - controller name
        Controler controler = new Controler();
        try {
            IControler stub = (IControler)UnicastRemoteObject.exportObject(controler, Integer.parseInt(args[0]));
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(args[1], stub);
            System.out.println("Server waiting");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
