import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Implementation extends UnicastRemoteObject implements methods {

    Integer[][] grid1;
    Integer[][] grid2;
    static boolean won1=false;
    static boolean won2=false;
    static boolean running1;
    static boolean running2;

    Implementation() throws RemoteException {
        super();
    }

    public void grid_pass(int number,Integer[][] grid) throws RemoteException{
        if (number==0){
            grid1 = grid;
        }
        else if (number==1){
            grid2 = grid;
        }
    }

    @Override
    public void won_the_game(int number) throws RemoteException{
        if (number==0){
            won1=true;
        }
        if (number==1){
            won2=true;
        }
    }

    @Override
    public void start(int number) throws RemoteException {
        if (number==0) running1=true;
        if (number==1) running2=true;
    }

    @Override
    public void done(int number) throws RemoteException {
        if (number==0) running1=false;
        if (number==1) running2=false;
    }

    @Override
    public void running(int number) throws RemoteException {
        try {
            if (number == 0) {
                while (running2) {
                    Thread.sleep(1000);
                    System.out.println("yes");
                }
            }
            if (number==1){
                while (running1){
                    Thread.sleep(1000);
                    System.out.println("yes");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int check_win(int number) throws RemoteException {
        if (number==0){
            if (won2) return 1;
        }
        if (number==1){
            if (won1) return 1;
        }
        return 0;
    }

    @Override
    public Integer checkhit(int number,int row, int column) throws RemoteException{
        if (number==0){
            if (grid2[row][column] == 1) {
                return 1;
            }
            return 0;
        }
        if (number==1){
            if (grid1[row][column] == 1) {
                return 1;
            }
            return 0;
        }
        return 0;
    }

    @Override
    public String[][] setmap(String[][] map) throws RemoteException{
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = "- ";
            }
        }
        return map;
    }
}
