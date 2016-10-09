import javax.swing.*;
import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Created by kamil on 06.10.16.
 */

public class CView implements Remote{

    IControler stub;
    IGate gate;
    static CView c;
    public static void main(String[] args) {
        try {
            c = new CView();
            //Gate gate = new Gate();
            //UnicastRemoteObject.exportObject(gate, 0);
            Registry registry = LocateRegistry.getRegistry("localhost");
            c.stub = (IControler) registry.lookup("Server");
            Scanner scanner = new Scanner(System.in);
            //stub.register(gate);
            while(true) {
                //c.area.setText("");
                for(IGate g: c.stub.getGates())
                {
                    System.out.println(g.isActive());
                    if(g instanceof IGateIn)
                        System.out.println("Aktywna bramka IN o id " + g.getID());
                    else if (g instanceof IGateOut)
                        System.out.println("Akytwa bramka OUT o id" + g.getID());
                    //  c.area.setText(String.valueOf(g.getID()));
                    //c.f.add(c.area);
                }
                System.out.println("Nacisnij f zeby odswiezyc, s zeby zatrzymac, r zeby uruchomic, b zeby wyswietlic rachunki");
                String choice = scanner.next();
                switch (choice){
                    case "s" :
                        System.out.println("Podaj id bramki");

                        c.switchOffGate(scanner.nextInt());
                        break;
                    case "r" :
                        System.out.println("Podaj id bramki");
                        c.switchOnGate(scanner.nextInt());
                        break;
                    case "b" :
                        for(Bill b: c.stub.getBills())
                        {
                            clearScreen();
                            System.out.println("Rachunek o id " + b.getId() + " na kwote " + b.getAmount());
                            scanner.next();
                            //  c.area.setText(String.valueOf(g.getID()));
                            //c.f.add(c.area);
                        }
                }
                clearScreen();
                //Thread.sleep(1000);
                //new CView(stub.getGates().get(0).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void switchOffGate(int id) throws RemoteException {
        for(IGate g: c.stub.getGates())
        {
            if(g.getID() == id){
                g.stop();
                System.out.println("Bramka o id " + g.getID() + " zostala zatrzymana");
                return;
            }
        }
    }

    void switchOnGate(int id) throws RemoteException {
        for(IGate g: c.stub.getGates())
        {
            if(g.getID() == id){
                g.start();
                System.out.println("Bramka o id " + g.getID() + " zostala uruchomiona");
                return;
            }
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
