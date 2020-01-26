import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Player2 {
    Integer[][] grid2;
    String[][] map = new String[10][10];
    public BattleshipLayout battle;
    static String name2;
    Socket socket;
    String[] coord;
    static int hitcount;
    static boolean flag=true;

    public Player2(BattleshipLayout battle){
        this.battle = battle;
    }

    public void name_assignment() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1: Enter your name : ");
        name2 = sc.next();
    }

    public void clientserverdetermination() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to be a server?");
            String answer = sc.next();
            if (answer.equalsIgnoreCase("yes")){
                ServerSocket ss = new ServerSocket(9999);
                socket = ss.accept();
                map=battle.setmap(map);
                this.playgame_receive(grid2);
            }
            if (answer.equalsIgnoreCase("no")){
                InetAddress host = InetAddress.getLocalHost();
                socket = new Socket(host.getHostName(),9999);
                System.out.println(name2 + " lets begin the game: ");
                map=battle.setmap(map);
                this.playgame_send();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playgame_send(){
        try {
            ObjectOutputStream br = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream br1 = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            map = battle.print_map(map);
            System.out.println("\n"+name2+ " enter the coordinates: ");
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
                System.out.println(name2 +" won the game!");
                br.close();
                br1.close();
            }
            else{
                br.writeObject(0);
                this.playgame_receive(grid2);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playgame_receive(Integer[][] grid){
        try {
            ObjectInputStream br1 = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream br = new ObjectOutputStream(socket.getOutputStream());
            String temp = (String) br1.readObject();
            coord = temp.split(",");
            int rec_row = Integer.parseInt(coord[0]);
            int rec_column = Integer.parseInt(coord[1]);
            Integer value = battle.checkhit(rec_row,rec_column,grid);
            if (value==1){
                grid[rec_row][rec_column] = 0;
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
        System.out.println("\nDo you want to play again?");
        String ans = sc.next();
        if (ans.equalsIgnoreCase("yes")) flag = true;
        if (ans.equalsIgnoreCase("no")) flag = false;
    }


    public static void main(String args[]) {
        while (flag) {
            BattleshipLayout player2 = new BattleshipLayout(10);
            Player2 playertwo = new Player2(player2);
            playertwo.name_assignment();
            playertwo.grid2 = playertwo.battle.makegrid();
            playertwo.grid2 = playertwo.battle.enter_location1(playertwo.grid2);
            playertwo.grid2 = playertwo.battle.enter_location2(playertwo.grid2);
            playertwo.clientserverdetermination();
            playertwo.asktoplayagain();
        }
    }
}
