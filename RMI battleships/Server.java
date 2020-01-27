import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String args[]) {
        try {
            Registry reg = LocateRegistry.createRegistry(9999);
            reg.rebind("server", new Implementation());
            System.out.println("Server has started.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
