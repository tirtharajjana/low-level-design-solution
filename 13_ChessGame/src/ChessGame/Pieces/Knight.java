package ChessGame.Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.Color;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        return ((rowDiff ==2 && colDiff ==1 ) || (rowDiff ==1 && colDiff ==2));
    }
}
