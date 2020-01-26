import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectFourGame implements ConnectFourGameInterface {
    ConnectFourPlayerInterface player1;
    ConnectFourPlayerInterface player2;


    public ConnectFourGame(ConnectFourPlayerInterface player1, ConnectFourPlayerInterface player2) {
        this.player1=player1;
        this.player2=player2;
    }

    @Override
    public void getStats() {
        System.out.println("<----------GAME OVER---------->");
        System.out.println(player1.getName() + " (Player " + player1.getPlayerNumber()+") has " +
                 player1.getNumberOfWins() + " Wins and " +player2.getName() + " (Player "+player2.getPlayerNumber()
        +") has " + player2.getNumberOfWins() + " Wins.\n" );

    }

    @Override
    public void playGame() {

        System.out.println("Welcome to Connect Four!");
        String asterik = "*";
        char[][] maze = new char[7][7];
        for(int i=0;i<maze.length;i++){
            for (int j=0;j<maze.length;j++){
                maze[i][j] = asterik.charAt(0);
            }
        }
        printboard(maze);
        System.out.println();

        while (!(findpattern(maze))){
            int row =6;
            int row1=6;
            int flag=0;
            int found=0;
            int flag1=0;
            int found1=0;

            int column = player1.takeTurn();
            while (flag == 0) {
                String present = Character.toString(maze[row][column]);
                if (present.equals("*")) {
                    maze[row][column] = player1.getGamePiece();
                    flag = 1;
                    System.out.println();
                    printboard(maze);
                    if (findpattern(maze)) {
                        System.out.println("\n"+player1.getName() + " has won the game!\n");
                        found=1;
                        break;
                    }
                } else row--;

            }
            if (found==1){
                System.out.println("Do you want to play again? yes/no?");
                Scanner abc = new Scanner(System.in);
                String prompt = abc.next();
                if(prompt.equals("yes")){
                    playGame();
                    return;
                }
                else if (prompt.equals("no")){
                    return;
                }
            }

            int column1 = player2.takeTurn();
            while (flag1 == 0) {
                String present = Character.toString(maze[row1][column1]);
                if (present.equals("*")) {
                    maze[row1][column1] = player2.getGamePiece();
                    flag1 = 1;
                    System.out.println();
                    printboard(maze);
                    if (findpattern(maze)) {
                        System.out.println("\n"+player2.getName() + " has won the game!\n");
                        found1=1;
                        break;
                    }
                } else row1--;

            }
            if (found1==1){
                System.out.println("Do you want to play again? yes/no?");
                Scanner abc = new Scanner(System.in);
                String prompt = abc.next();
                if(prompt.equals("yes")){
                    playGame();
                    return;
                }
                else if (prompt.equals("no")){
                    return;
                }
            }
        }
    }

    public boolean findpattern(char[][] array){
        String input1 = player1.getGamePiece() + "{4}";
        String input2 = player2.getGamePiece() + "{4}";
        int size = 7;
        for (int i = 0; i < size; i++) {
            String text = "";
            for (int j = 0; j < size; j++) {
                text += array[i][j];
            }
            if (regex(input1,text)){
                player1.addWin();
                return true;
            }
            else if (regex(input2,text)){
                player2.addWin();
                return true;
            }
        }



        for (int i = 0; i < size; i++) {
            String text = "";
            for (int j = 0; j < size; j++) {
                text += array[j][i];
            }
            if (regex(input1,text)){
                player1.addWin();
                return true;
            }
            else if (regex(input2,text)){
                player2.addWin();
                return true;
            }
        }

        { String text = "";
            for (int k = 0; k <= size - 1; k++) {
                int i = k;
                int j = 0;
                while (i >= 0) {
                    text = text + array[i][j];
                    i-=1;
                    j+=1;
                }
                if (regex(input1,text)){
                    player1.addWin();
                    return true;
                }
                else if (regex(input2,text)){
                    player2.addWin();
                    return true;
                }
            }
        }

        { String text = "";
            for (int k = 0; k <= size - 1; k++) {
                int i = size-1;
                int j = k;
                while (j <= size-1) {
                    text = text + array[i][j];
                    i-=1;
                    j+=1;
                }
                if (regex(input1,text)){
                    player1.addWin();
                    return true;
                }
                else if (regex(input2,text)){
                    player2.addWin();
                    return true;
                }
            }
        }



        { String text = "";
            for (int k = size-1; k >= 0; k--) {
                int i = 0;
                int j = k;
                while (j <= size-1) {
                    text = text + array[i][j];
                    i+=1;
                    j+=1;
                }
                if (regex(input1,text)){
                    player1.addWin();
                    return true;
                }
                else if (regex(input2,text)){
                    player2.addWin();
                    return true;
                }
            }
        }

        { String text = "";
            for (int k = 0; k <= size - 1; k++) {
                int i = k;
                int j = 0;
                while (i <= size-1) {
                    text = text + array[i][j];
                    i+=1;
                    j+=1;
                }
                if (regex(input1,text)){
                    player1.addWin();
                    return true;
                }
                else if (regex(input2,text)){
                    player2.addWin();
                    return true;
                }
            }
        }
        return false;
    }

    public void printboard(char[][] array){
        for (int i=0;i<7;i++){
            for (int j=0;j<7;j++){
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public boolean regex(String input1, String line){
        String patternString = ".*" + input1 + ".*";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        boolean matches = matcher.matches();
        if (matches) {
            return true;
        }
    return false;}

    public void playerchar(){
        player1.setPlayerNumber(1);
        System.out.println(player1.getName()+ " select a single char game piece :");
        Scanner sc = new Scanner(System.in);
        String prompt1 = sc.next();
        char input1 = prompt1.charAt(0);
        player1.setGamePiece(input1);
        player2.setPlayerNumber(2);
        System.out.println(player2.getName()+ " select a single char game piece :");
        Scanner sc2 = new Scanner(System.in);
        String prompt2 = sc2.next();
        char input2 = prompt2.charAt(0);
        player2.setGamePiece(input2);
    }


}
