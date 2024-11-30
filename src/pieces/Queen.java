package pieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!checkPosition(toLine, toColumn)) return false;

        Rook rook = new Rook(color);
        Bishop bishop = new Bishop(color);
        return rook.canMoveToPosition(chessBoard, line, column, toLine, toColumn) ||
                bishop.canMoveToPosition(chessBoard, line, column, toLine, toColumn);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
