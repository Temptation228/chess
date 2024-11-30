package chess;

import pieces.King;
import pieces.Rook;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (!checkPos(startLine) || !checkPos(startColumn) || !checkPos(endLine) || !checkPos(endColumn)) {
            return false;
        }
        ChessPiece piece = board[startLine][startColumn];
        if (piece == null || !nowPlayer.equals(piece.getColor())) {
            return false;
        }
        if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
            ChessPiece targetPiece = board[endLine][endColumn];
            board[endLine][endColumn] = piece;
            board[startLine][startColumn] = null;
            if (isKingInCheck(nowPlayer)) {
                board[startLine][startColumn] = piece;
                board[endLine][endColumn] = targetPiece;
                return false;
            }
            if (piece instanceof King || piece instanceof Rook) {
                piece.setCheck(false);
            }
            this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
            return true;
        }
        return false;
    }

    private boolean isKingInCheck(String playerColor) {
        int kingLine = -1, kingColumn = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                if (piece instanceof King && piece.getColor().equals(playerColor)) {
                    kingLine = i;
                    kingColumn = j;
                    break;
                }
            }
        }
        return ((King) board[kingLine][kingColumn]).isUnderAttack(this, kingLine, kingColumn);
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && board[0][3] == null) {
                if (board[0][0].getColor().equals("White") && board[0][1] == null && board[0][2] == null &&
                        new King("White").isUnderAttack(this, 0, 2)) {
                    return false;
                }
                ((King) board[0][4]).setCheck(false);
                board[0][2] = board[0][4];
                board[0][4] = null;
                board[0][3] = board[0][0];
                board[0][0] = null;
                nowPlayer = "Black";
                return true;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && board[7][3] == null) {
                if (board[7][0].getColor().equals("Black") && board[7][1] == null && board[7][2] == null &&
                        new King("Black").isUnderAttack(this, 7, 2)) {
                    return false;
                }
                ((King) board[7][4]).setCheck(false);
                board[7][2] = board[7][4];
                board[7][4] = null;
                board[7][3] = board[7][0];
                board[7][0] = null;
                nowPlayer = "White";
                return true;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && board[0][5] == null && board[0][6] == null) {
                if (board[0][7].getColor().equals("White") && new King("White").isUnderAttack(this, 0, 6)) {
                    return false;
                }
                ((King) board[0][4]).setCheck(false);
                board[0][6] = board[0][4];
                board[0][4] = null;
                board[0][5] = board[0][7];
                board[0][7] = null;
                nowPlayer = "Black";
                return true;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && board[7][5] == null && board[7][6] == null) {
                if (board[7][7].getColor().equals("Black") && new King("Black").isUnderAttack(this, 7, 6)) {
                    return false;
                }
                ((King) board[7][4]).setCheck(false);
                board[7][6] = board[7][4];
                board[7][4] = null;
                board[7][5] = board[7][7];
                board[7][7] = null;
                nowPlayer = "White";
                return true;
            } else return false;
        }
    }
}
