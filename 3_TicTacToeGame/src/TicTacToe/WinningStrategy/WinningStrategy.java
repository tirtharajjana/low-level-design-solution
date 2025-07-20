package TicTacToe.WinningStrategy;

import TicTacToe.Board;
import TicTacToe.GameStatus;
import TicTacToe.Symbol;

public interface WinningStrategy {
     boolean checkWinner(Board board, Symbol symbol);
}
