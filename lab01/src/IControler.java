import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by kamil on 06.10.16.
 */
public interface IControler extends Remote {

    ArrayList<IGate> getGates() throws RemoteException;

    ArrayList<Bill> getBills() throws RemoteException;

    boolean register(Object o) throws RemoteException;

    boolean unregister(Object o) throws RemoteException;

    void add(Bill bill) throws RemoteException;
}
