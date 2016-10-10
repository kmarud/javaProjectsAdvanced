import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by kamil on 06.10.16.
 */

public class CView implements Remote{

    IControler stub;
    static CView view;
    public static void main(String[] args) {
        try {
            view = new CView();
            Registry registry = LocateRegistry.getRegistry("localhost");
            view.stub = (IControler) registry.lookup("Server");
            Scanner scanner = new Scanner(System.in);
            //stub.register(gate);
            while(true) {
                view.showGates();

                System.out.println("f - refresh");
                System.out.println("s - stop gate");
                System.out.println("r - run gate");
                System.out.println("b - show bills");

                String choice = scanner.next();
                switch (choice){
                    case "f" :
                        clearScreen();
                        break;
                    case "s" :
                        System.out.println("Gate ID: ");
                        view.switchOffGate(scanner.nextInt());
                        break;
                    case "r" :
                        System.out.println("Gate ID: ");
                        view.switchOnGate(scanner.nextInt());
                        break;
                    case "b" :
                        clearScreen();
                        view.showBills();
                        System.out.println("Press enter");
                        System.in.read();
                        clearScreen();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void switchOffGate(int id) throws RemoteException {
        for(IGate g: view.stub.getGates()) {
            if(g.getID() == id){
                g.stop();
                System.out.println("Gate with ID " + g.getID() + " is now inactive");
                return;
            }
        }
    }

    void switchOnGate(int id) throws RemoteException {
        for(IGate g: view.stub.getGates()) {
            if(g.getID() == id){
                g.start();
                System.out.println("Gate with ID " + g.getID() + " is now active");
                return;
            }
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void showGates() throws RemoteException {
        for(IGate g: view.stub.getGates()) {
            String act,inOrOut="unknown";
            if (g.isActive())
                act="Active";
            else
                act="Inactive";

            if (g instanceof IGateIn)
                inOrOut ="IN";
            else if (g instanceof IGateOut)
                inOrOut ="OUT";

            System.out.println(act + " gate " + inOrOut + " with ID: " + g.getID());
        }
    }

    private void showBills() throws RemoteException {
        double amount, sumOfAmount=0;
        for(Bill b: view.stub.getBills()) {
            amount = b.getAmount();
            sumOfAmount += amount;
            System.out.println("Bill wih id " + b.getId() + ", amount " + amount + " PLN");
        }
        System.out.println("### Sum of all bills:" + sumOfAmount + "PLN");
    }
}
