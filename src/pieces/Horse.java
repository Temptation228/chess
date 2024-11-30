package pieces;

import chess.ChessPiece;
import chess.ChessBoard;


public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!checkPosition(toLine, toColumn)) return false;

        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);

        if ((deltaLine == 2 && deltaColumn == 1) || (deltaLine == 1 && deltaColumn == 2)) {
            if (chessBoard.board[toLine][toColumn] == null ||
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
