package pieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!checkPosition(toLine, toColumn)) return false;
        int direction = color.equals("White") ? 1 : -1;
        if (column == toColumn) {
            if (line + direction == toLine && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
            if ((line == 1 || line == 6) && line + 2 * direction == toLine && chessBoard.board[line + direction][column] == null && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
        } else if (Math.abs(column - toColumn) == 1 && line + direction == toLine) {

            if (chessBoard.board[toLine][toColumn] != null &&
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
