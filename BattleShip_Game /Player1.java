import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Player1 {
    Integer[][] grid1;
    String[][] map = new String[10][10];
    static String name1;
    Socket s;
    public BattleshipLayout battle;
    String[] coord;
    static int hitcount;
    static boolean flag=true;

    public Player1(BattleshipLayout battle){
        this.battle = battle;
    }

    public void name_assignment(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1: Enter your name : ");
        name1 = sc.next();
    }

    public void clientserverdetermination(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to be a server?");
            String answer = sc.next();
            if (answer.equalsIgnoreCase("yes")){
                ServerSocket ss = new ServerSocket(9999);
                s = ss.accept();
                map=battle.setmap(map);
                this.playgame_receive(grid1);
            }
            if (answer.equalsIgnoreCase("no")){
                InetAddress host = InetAddress.getLocalHost();
                s = new Socket(host.getHostName(),9999);
                System.out.println(name1 + " lets begin the game: ");
                map=battle.setmap(map);
                this.playgame_send();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playgame_send(){
        try {
            ObjectOutputStream br = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream br1 = new ObjectInputStream(s.getInputStream());
            Scanner sc = new Scanner(System.in);
            map = battle.print_map(map);
            System.out.println("\n"+name1+ " enter the coordinates: ");
            String coordinates = sc.next();
            String[] coor = coordinates.split(",");
            br.writeObject(coordinates);
            Integer value = (Integer) br1.readObject();
            if (value==1){
                System.out.println("It was a hit!");
                hitcount++;
                map[Integer.parseInt(coor[0])][Integer.parseInt(coor[1])] = "H";
            }
            else{
                System.out.println("It was a miss!");
                map[Integer.parseInt(coor[0])][Integer.parseInt(coor[1])] = "M";
            }
            if (hitcount==6){
                br.writeObject(-1);
                System.out.println(name1+ " won the game!");
                br.close();
                br1.close();
            }
            else{
                br.writeObject(0);
                this.playgame_receive(grid1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playgame_receive(Integer[][] grid){
        try {
            ObjectInputStream br1 = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream br = new ObjectOutputStream(s.getOutputStream());
            String temp = (String) br1.readObject();
            coord = temp.split(",");
            int rec_row = Integer.parseInt(coord[0]);
            int rec_column = Integer.parseInt(coord[1]);
            Integer value = battle.checkhit(rec_row,rec_column,grid);
            if (value==1){
                grid1[rec_row][rec_column] = 0;
                br.writeObject(value);
            }
            else{
                br.writeObject(value);
            }
            int val = (Integer) br1.readObject();
            if (val==-1){
                System.out.println("Opponent has won the game!");
                return;
            }
            this.playgame_send();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void asktoplayagain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to play again?");
        String ans = sc.next();
        if (ans.equalsIgnoreCase("yes")) flag = true;
        if (ans.equalsIgnoreCase("no")) flag = false;
    }


    public static void main(String args[]) {
        while (flag) {
            BattleshipLayout player1 = new BattleshipLayout(10);
            Player1 playerone = new Player1(player1);
            playerone.name_assignment();
            playerone.grid1 = playerone.battle.makegrid();
            playerone.grid1 = playerone.battle.enter_location1(playerone.grid1);
            playerone.grid1 = playerone.battle.enter_location2(playerone.grid1);
            playerone.clientserverdetermination();
            playerone.asktoplayagain();
        }
    }
}
