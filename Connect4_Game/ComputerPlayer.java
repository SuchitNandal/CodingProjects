public class ComputerPlayer implements ConnectFourPlayerInterface {

    String name;
    char gamepiece;
    int playernumber;
    int wins = 0;


    public ComputerPlayer(String Playername){
        this.name = Playername;
    }
    @Override
    public int takeTurn() {
        return 0;
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
        this.gamepiece=gamePiece;
    }

    @Override
    public void setPlayerNumber(int num) {
        this.playernumber=num;
    }

    @Override
    public int getPlayerNumber() {
        return playernumber;
    }
}
