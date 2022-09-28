package ca.humber.Pieces;

import ca.humber.Chess.ChessMain;
import ca.humber.Chess.Square;

//Behaves as king chess piece should
public class King extends Piece {

    int moveFromX;
    int moveFromY;
    int moveToX;
    int moveToY;
    String type = "piece";

    public King(String colorIn) {
        super(colorIn, "piece");

        if (this.color.equals("white")) {
            symbol = "K";
        } else {
            symbol = "k";
        }
    }

    @Override
    public boolean checkTileDetails(int[] moveFrom, int[] moveTo, String pieceColor) {
        this.moveFromX = moveFrom[0];
        this.moveFromY = moveFrom[1];
        this.moveToX = moveTo[0];
        this.moveToY = moveTo[1];
        if (this.color.equals(pieceColor)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        if (moveFromX != -1 && moveFromY != -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    //Allows the king to move in a square fashion.
    public boolean moveTo(int x, int y) {
        Square toSquare = ChessMain.board[moveToY][moveToX];

        for (int moveAwayX = -1; moveAwayX <= 1; moveAwayX++) {
            for (int moveAwayY = -1; moveAwayY <= 1; moveAwayY++) {
                if (moveToX == moveFromX + moveAwayX && moveToY == moveFromY + moveAwayY) {
                    if ((toSquare.getType().equals("empty"))) {
                        return true;
                    } else if (toSquare.getType().equals("empty")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
