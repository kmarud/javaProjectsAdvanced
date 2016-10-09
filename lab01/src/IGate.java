import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by kamil on 06.10.16.
 */
public interface IGate extends Remote {

    boolean start() throws RemoteException;

    boolean stop() throws RemoteException;

    void setID(int id) throws RemoteException;          // metoda ktora wykona serwer

    int getID() throws RemoteException;

    boolean isActive() throws RemoteException;

}
