import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Player1 {
    Integer[][] grid1;
    String[][] map = new String[10][10];
    static String name1;
    public BattleshipLayout battle;
    static int hitcount;
    static boolean flag=true;
    static int player;

    public Player1(BattleshipLayout battle){
        this.battle = battle;
    }

    public int detemine_player() {
        System.out.println("Do you want to be player 1?");
        Scanner sc = new Scanner(System.in);
        String ans = sc.next();
        if (ans.equalsIgnoreCase("yes")){
            return 0;
        }
        else if (ans.equalsIgnoreCase("no")) {
            return 1;
        }
        else return 2;
    }

    public void name_assignment(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1: Enter your name : ");
        name1 = sc.next();
    }

    public void asktoplayagain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to play again?");
        String ans = sc.next();
        if (ans.equalsIgnoreCase("yes")) flag = true;
        if (ans.equalsIgnoreCase("no")) flag = false;
    }

    public void playgame(int number,methods method){
        if (number==0){
            playgame_send(number,method);
        }
        else{
            playgame_receive(number,method);
        }
    }

    public void playgame_send(int number,methods method){
        try {
            method.start(number);
            Scanner sc = new Scanner(System.in);
            map = battle.print_map(map);
            System.out.println("\n"+name1+ " enter the coordinates: ");
            String coordinates = sc.next();
            String[] coor = coordinates.split(",");
            int row = Integer.parseInt(coor[0]);
            int column = Integer.parseInt(coor[1]);
            int value = method.checkhit(number,row,column);
            if (value==1){
                System.out.println("It was a hit!");
                hitcount++;
                map[row][column] = "H";
            }
            else{
                System.out.println("It was a miss!");
                map[row][column] = "M";
            }
            if (hitcount==6){
                method.won_the_game(number);
                method.done(number);
                System.out.println(name1+ " won the game!");
            }
            else{
                method.done(number);
                Thread.sleep(1000);
                this.playgame_receive(number,method);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playgame_receive(int number,methods method){
        try {
            method.running(number);
            int value = method.check_win(number);
            if (value==1){
                System.out.println("Opponent won the game.");
                return;
            }
            else {
                this.playgame_send(number,method);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }




    public static void main(String args[]) {
        while (flag) {
            try {
                BattleshipLayout player1 = new BattleshipLayout(10);
                Player1 playerone = new Player1(player1);
                playerone.name_assignment();
                playerone.grid1 = playerone.battle.makegrid();
                playerone.grid1 = playerone.battle.enter_location1(playerone.grid1);
                playerone.grid1 = playerone.battle.enter_location2(playerone.grid1);
                InetAddress a = InetAddress.getLocalHost();
                String host = a.getHostName();
                Registry reg = LocateRegistry.getRegistry(host, 9999);
                methods method = (methods) reg.lookup("server");
                player = playerone.detemine_player();
                method.grid_pass(player,playerone.grid1);
                playerone.map = method.setmap(playerone.map);
                playerone.playgame(player,method);
                playerone.asktoplayagain();

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
