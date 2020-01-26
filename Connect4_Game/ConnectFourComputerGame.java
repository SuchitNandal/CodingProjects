import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectFourComputerGame implements ConnectFourGameInterface {
    ConnectFourPlayerInterface player3;
    ConnectFourPlayerInterface player4;


    public ConnectFourComputerGame(ConnectFourPlayerInterface player1, ConnectFourPlayerInterface player2) {
        this.player3=player1;
        this.player4=player2;
    }

    @Override
    public void getStats() {
        System.out.println("<----------GAME OVER---------->");
        System.out.println(player3.getName() + " (Player " + player3.getPlayerNumber()+") has " +
                player3.getNumberOfWins() + " Wins and Computer (Player "+player4.getPlayerNumber()
                +") has " + player4.getNumberOfWins() + " Wins." );
    }

    @Override
    public void playGame() {
        System.out.println("Welcome to Connect Four!");
        String asterik = "-";
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
            int step1=0;

            int column = player3.takeTurn();
            while (flag == 0) {
                String present = Character.toString(maze[row][column]);
                if (present.equals("-")) {
                    maze[row][column] = player3.getGamePiece();
                    flag = 1;
                    System.out.println();
                    printboard(maze);
                    if (findpattern(maze)) {
                        System.out.println("\n"+player3.getName() + " has won the game!\n");
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

            System.out.println("Computer will now take his turn!");

             if(defense(maze)) {
                 if (findpattern(maze)) {
                     System.out.println("\nComputer has won the game!\n");
                     found1=1;
                 }
                 step1=1;
             }

               if (step1==0 & Character.toString(maze[6][3]).equals("-")){
                   maze[6][3] = player4.getGamePiece();
                   printboard(maze);
                   step1=1;
               }
               if(step1==0){
                   if(insert(maze)){
                       if (findpattern(maze)) {
                           System.out.println("\nComputer has won the game!\n");
                           found1 = 1;
                       }
                       step1=1;
                   }
               }

               if (step1==0){
                   System.out.println("i am here");
                  Random rand = new Random();
                  int column1 = rand.nextInt(6);
                   while (flag1 == 0) {
                       String present = Character.toString(maze[row1][column1]);
                       System.out.println("here?");
                       if (present.equals("-")) {
                           maze[row1][column1] = player4.getGamePiece();
                           flag1 = 1;
                           System.out.println();
                           printboard(maze);
                           if (findpattern(maze)) {
                               System.out.println("\nComputer has won the game!\n");
                               found1=1;
                               break;
                           }
                       } else row1--;
                   }
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
        String input1 = player3.getGamePiece() + "{4}";
        String input2 = player4.getGamePiece() + "{4}";
        int size = 7;
        for (int i = 0; i < size; i++) {
            String text = "";
            for (int j = 0; j < size; j++) {
                text += array[i][j];
            }
            if (regex(input1,text)){
                player3.addWin();
                return true;
            }
            else if (regex(input2,text)){
                player4.addWin();
                return true;
            }
        }



        for (int i = 0; i < size; i++) {
            String text = "";
            for (int j = 0; j < size; j++) {
                text += array[j][i];
            }
            if (regex(input1,text)){
                player3.addWin();
                return true;
            }
            else if (regex(input2,text)){
                player4.addWin();
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
                    player3.addWin();
                    return true;
                }
                else if (regex(input2,text)){
                    player4.addWin();
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
                    player3.addWin();
                    return true;
                }
                else if (regex(input2,text)){
                    player4.addWin();
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
                    player3.addWin();
                    return true;
                }
                else if (regex(input2,text)){
                    player4.addWin();
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
                    player3.addWin();
                    return true;
                }
                else if (regex(input2,text)){
                    player4.addWin();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean insert(char[][] array){
        String input1 = "-" + player4.getGamePiece() + "|" + player3.getGamePiece() + "-";
        String input2 ="-" + player4.getGamePiece() + "{2}|" + player3.getGamePiece() + "{2}-";
        String input3 ="-" + player4.getGamePiece() + "{3}|" + player3.getGamePiece() + "{3}-";
        int size = 7;
        for (int i = 0; i < size; i++) {
            String text = "";
            for (int j = 0; j < size; j++) {
                text += array[i][j];
            }
            if (regex(input3, text)) {
                Pattern pattern = Pattern.compile(input3);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    if (Character.toString(array[i][matcher.start()]).equals("-")) {
                        array[i][matcher.start()] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                }
            }
            if (regex(input2, text)) {
                Pattern pattern = Pattern.compile(input2);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    if (Character.toString(array[i][matcher.start()]).equals("-")) {
                        array[i][matcher.start()] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                }
            }
            if (regex(input1, text)) {
                Pattern pattern = Pattern.compile(input1);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    if (Character.toString(array[i][matcher.start()]).equals("-")) {
                        array[i][matcher.start()] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                }
            }
        }


        for (int i = 0; i < size; i++) {
            String text = "";
            for (int j = 0; j < size; j++) {
                text += array[j][i];
            }
            if (regex(input3, text)) {
                Pattern pattern = Pattern.compile(input3);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    if (Character.toString(array[matcher.start()][i]).equals("-")) {
                        array[matcher.start()][i] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                }
            }
            if (regex(input2, text)) {
                Pattern pattern = Pattern.compile(input2);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    if (Character.toString(array[matcher.start()][i]).equals("-")) {
                        array[matcher.start()][i] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                }
            }
            if (regex(input1, text)) {
                Pattern pattern = Pattern.compile(input1);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    if (Character.toString(array[matcher.start()][i]).equals("-")) {
                        array[matcher.start()][i] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean defense(char[][] array) {
        String input1 = "-" + player3.getGamePiece() + "{3}|" + player3.getGamePiece() + "{3}-";
        int size = 7;
        for (int i = 0; i < size; i++) {
            String text = "";
            for (int j = 0; j < size; j++) {
                text += array[i][j];
            }
            if (regex(input1, text)) {
                Pattern pattern = Pattern.compile(input1);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    if (Character.toString(array[i][matcher.start()]).equals("-")) {
                        array[i][matcher.start()] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                    if (Character.toString(array[i][matcher.end()]).equals("-")) {
                        array[i][matcher.end()] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                }
            }
        }


        for (int i = 0; i < size; i++) {
            String text = "";
            for (int j = 0; j < size; j++) {
                text += array[j][i];
            }
            if (regex(input1, text)) {
                Pattern pattern = Pattern.compile(input1);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    if (Character.toString(array[matcher.start()][i]).equals("-")) {
                        array[matcher.start()][i] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                    if (Character.toString(array[matcher.end()][i]).equals("-")) {
                        array[matcher.end()][i] = player4.getGamePiece();
                        printboard(array);
                        return true;
                    }
                }
            }
        }

        {
            String text = "";
            for (int k = 0; k <= size - 1; k++) {
                int i = k;
                int j = 0;
                while (i >= 0) {
                    text = text + array[i][j];
                    i -= 1;
                    j += 1;
                }
                if (regex(input1, text)) {
                    Pattern pattern = Pattern.compile(input1);
                    Matcher matcher = pattern.matcher(text);
                    while (matcher.find()) {
                        if (Character.toString(array[k + matcher.start()][k - matcher.start()]).equals("-")) {
                            array[k + matcher.start()][k - matcher.start()] = player4.getGamePiece();
                            printboard(array);
                            return true;
                        }
                        if (Character.toString(array[k + matcher.end()][k - matcher.end()]).equals("-")) {
                            array[k + matcher.end()][k - matcher.end()] = player4.getGamePiece();
                            printboard(array);
                            return true;
                        }
                    }
                }
            }
        }
   return false; }


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


    @Override
    public void playerchar() {
        player3.setPlayerNumber(1);
        System.out.println(player3.getName()+ " select a single char game piece :");
        Scanner sc = new Scanner(System.in);
        String prompt1 = sc.next();
        char input1 = prompt1.charAt(0);
        player3.setGamePiece(input1);
        player4.setPlayerNumber(2);
        System.out.println("The char game piece for computer will be X");
        String input2 ="X";
        char compsymbol = input2.charAt(0);
        player4.setGamePiece(compsymbol);
    }
}
