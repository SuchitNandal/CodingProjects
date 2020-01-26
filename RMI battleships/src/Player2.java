import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Player2 {
    Integer[][] grid2;
    String[][] map = new String[10][10];
    public BattleshipLayout battle;
    static String name2;
    static int hitcount;
    static boolean flag=true;

    public Player2(BattleshipLayout battle){
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

    public void name_assignment() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1: Enter your name : ");
        name2 = sc.next();
    }


    public void asktoplayagain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDo you want to play again?");
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
            System.out.println("\n"+name2+ " enter the coordinates: ");
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
                System.out.println(name2+ " won the game!");
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
                BattleshipLayout player2 = new BattleshipLayout(10);
                Player2 playertwo = new Player2(player2);
                playertwo.name_assignment();
                playertwo.grid2 = playertwo.battle.makegrid();
                playertwo.grid2 = playertwo.battle.enter_location1(playertwo.grid2);
                playertwo.grid2 = playertwo.battle.enter_location2(playertwo.grid2);
                InetAddress a = InetAddress.getLocalHost();
                String host = a.getHostName();
                Registry reg = LocateRegistry.getRegistry(host, 9999);
                methods method = (methods) reg.lookup("server");
                int player = playertwo.detemine_player();
                method.grid_pass(player,playertwo.grid2);
                playertwo.map = method.setmap(playertwo.map);
                playertwo.playgame(player,method);
                playertwo.asktoplayagain();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
