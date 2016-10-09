import java.rmi.RemoteException;

/**
 * Created by kamil on 08.10.16.
 */
public interface IGateOut extends IGate{

    double checkTicket(Ticket ticket) throws RemoteException;
}
