package pieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class King extends ChessPiece {


    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!super.checkPosition(toLine, toColumn)) return false;
        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);
        if (deltaLine <= 1 && deltaColumn <= 1 && !(line == toLine && column == toColumn)) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int adjLine = toLine + i;
                    int adjColumn = toColumn + j;
                    if (adjLine >= 0 && adjLine < 8 && adjColumn >= 0 && adjColumn < 8) {
                        ChessPiece otherPiece = chessBoard.board[adjLine][adjColumn];
                        if (otherPiece instanceof King && !otherPiece.getColor().equals(this.color)) {
                            return false;
                        }
                    }
                }
            }
            return chessBoard.board[toLine][toColumn] == null ||
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (!checkPosition(line, column)) return false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
