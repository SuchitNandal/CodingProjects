public class ConnectFourDriver
{
    public static void main(String[] args) throws InterruptedException {
        ConnectFourPlayerInterface player1 = new HumanPlayer("Suchit");
        ConnectFourPlayerInterface player2 = new HumanPlayer("Ninad");
        ConnectFourGameInterface game = new ConnectFourGame(player1, player2);
        game.playerchar();
        game.playGame();
        game.getStats();
        Thread.sleep(2000);
        ConnectFourPlayerInterface player3 = new HumanPlayer("Suchit");
        ConnectFourPlayerInterface player4 = new ComputerPlayer("Computer");
        ConnectFourGameInterface solo = new ConnectFourComputerGame(player3, player4);
        solo.playerchar();
        solo.playGame();
        solo.getStats();

    }
}
