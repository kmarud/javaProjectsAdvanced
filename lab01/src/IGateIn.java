import java.rmi.RemoteException;

/**
 * Created by kamil on 08.10.16.
 */
public interface IGateIn extends IGate {

    Ticket getTicket() throws RemoteException;
}
