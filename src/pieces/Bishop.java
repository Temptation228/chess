package pieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!checkPosition(toLine, toColumn)) return false;

        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            int deltaLine = (toLine - line) / Math.abs(toLine - line);
            int deltaColumn = (toColumn - column) / Math.abs(toColumn - column);
            int currentLine = line + deltaLine;
            int currentColumn = column + deltaColumn;

            while (currentLine != toLine && currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false;
                }
                currentLine += deltaLine;
                currentColumn += deltaColumn;
            }
            return chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
