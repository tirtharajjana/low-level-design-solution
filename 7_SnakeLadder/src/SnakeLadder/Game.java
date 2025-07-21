package SnakeLadder;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.DelayQueue;

public class Game {
    Board board;
    Dice dice;
    Deque<Player> playersList = new LinkedList<>();
    Player winner;

    public Game() {
        initializeGame();
    }

    void initializeGame() {
        board = new Board(10, 5, 4);
        dice = new Dice(1);
        winner = null;
        addPlayer();
    }

    private void addPlayer() {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame() {
        while (winner == null) {
            Player playerTurn = findPlayerTurn();
            System.out.println("player turn is:" + playerTurn.id + " current position is: " + playerTurn.currentPosition);

            int diceNumber = dice.rollDice();

            int playerPosition = playerTurn.currentPosition + diceNumber;
            playerPosition = jumpCheck(playerPosition);
            playerTurn.currentPosition = playerPosition;

            System.out.println("player turn is:" + playerTurn.id + " new Position is: " + playerPosition);

            if(playerPosition >= board.cells.length * board.cells.length-1){
                winner = playerTurn;
            }
        }
        System.out.println("WINNER IS:" + winner.id);


    }

    private Player findPlayerTurn(){
        Player player = playersList.removeFirst();
        playersList.addLast(player);
        return player;
    }

    private int jumpCheck(int playerNewPosition){
        if(playerNewPosition > board.cells.length * board.cells.length -1){
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if(cell.jump != null && cell.jump.start == playerNewPosition){
            String jumpBy = (cell.jump.start < cell.jump.end)? "ladder" : "snake";
            System.out.println("jump done by: " + jumpBy);
            return cell.jump.end;
        }
        return playerNewPosition;
    }

}
