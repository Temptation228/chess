package pieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!checkPosition(toLine, toColumn)) return false;

        if (line == toLine || column == toColumn) {
            int stepLine = (toLine - line) != 0 ? (toLine - line) / Math.abs(toLine - line) : 0;
            int stepColumn = (toColumn - column) != 0 ? (toColumn - column) / Math.abs(toColumn - column) : 0;
            int currentLine = line + stepLine;
            int currentColumn = column + stepColumn;

            while (currentLine != toLine || currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false;
                }
                currentLine += stepLine;
                currentColumn += stepColumn;
            }
            return chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
