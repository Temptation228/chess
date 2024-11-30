package chess;

public abstract class ChessPiece {
    protected String color;
    protected boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    protected boolean checkPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
