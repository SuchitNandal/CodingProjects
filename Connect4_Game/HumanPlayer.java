import java.util.Scanner;

public class HumanPlayer implements ConnectFourPlayerInterface {
    String name;
    char gamepiece;
    int playernumber;
    int wins = 0;

    public HumanPlayer(String Playername){
        this.name = Playername;
    }

    public HumanPlayer(){
        this.name = "Player " + playernumber;
    }

    @Override
    public int takeTurn() {
        System.out.print("Player "+getPlayerNumber()+": "+getName()+" select a column\n");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        return input;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfWins() {
        return wins;
    }

    @Override
    public void addWin() {
        wins++;

    }

    @Override
    public char getGamePiece() {
        return gamepiece;
    }

    @Override
    public void setGamePiece(char gamePiece) {
        this.gamepiece = gamePiece;

    }

    @Override
    public void setPlayerNumber(int num) {
        this.playernumber = num;

    }

    public int getPlayerNumber() {
        return playernumber;
    }
}
