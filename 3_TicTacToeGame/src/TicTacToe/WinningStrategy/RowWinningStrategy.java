package TicTacToe.WinningStrategy;

import TicTacToe.Board;
import TicTacToe.Symbol;

public class RowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Symbol symbol) {
        for(int i = 0; i < board.getSize(); i++){
            boolean isMatch = true;
            for(int j = 0; j < board.getSize(); j++){
                if(board.getSymbol(i, j) != symbol){
                    isMatch = false;
                    break;
                }
            }
            if(isMatch){
                return true;
            }
        }
        return false;
    }
}
