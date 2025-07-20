package TicTacToe.WinningStrategy;

import TicTacToe.Board;
import TicTacToe.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Symbol symbol) {
        // main diagonal
        boolean mainDiagonalMatch = true;

        for(int i = 0; i < board.getSize(); i++) {
            if(board.getSymbol(i, i) != symbol){
                mainDiagonalMatch = false;
                break;
            }
        }

        if(mainDiagonalMatch){
            return true;
        }

        // Anti-diagonal
        boolean antiDiagonalMatch = true;

        for(int i = 0; i < board.getSize(); i++) {
            if(board.getSymbol(i, board.getSize() - 1 - i)  != symbol){
                antiDiagonalMatch = false;
                break;
            }
        }

       if(antiDiagonalMatch){
           return true;
       }

       return false;
    }
}
