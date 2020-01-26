import java.rmi.Remote;
import java.rmi.RemoteException;

public interface methods extends Remote {
    Integer checkhit(int number,int row, int column) throws RemoteException;
    String[][] setmap(String[][] map) throws RemoteException;
    void grid_pass(int number,Integer[][] grid) throws RemoteException;
    void won_the_game(int number) throws RemoteException;
    void start(int number) throws RemoteException;
    void done(int number) throws RemoteException;
    void running(int number) throws RemoteException;
    int check_win(int number) throws RemoteException;
}
