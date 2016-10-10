import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by kamil on 06.10.16.
 */

public abstract class Gate implements IGate, Serializable{

    protected int id;
    protected boolean isActive;
    protected String statement;

    @Override
    public void setID(int id) throws RemoteException {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public boolean isActive() throws RemoteException {
        return isActive;
    }

    @Override
    public boolean stop() throws RemoteException {
        isActive = false;
        statement = "Gate is not available";
        System.out.println("Gate stopped");
        return !this.isActive;
    }

    @Override
    public boolean start() throws RemoteException {
        isActive = true;
        statement = "Gate is active";
        System.out.println("Gate started");
        return this.isActive;
    }

    protected static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
