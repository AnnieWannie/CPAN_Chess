package ca.humber.Pieces;

import ca.humber.Chess.ChessMain;
import ca.humber.Chess.Square;

//Mostly behaves as pawn chess piece should; lacks en passant and promotion logic.
public class Pawn extends Piece {

    int moveFromX;
    int moveFromY;
    int moveToX;
    int moveToY;
    String type = "piece";

    public Pawn(String colorIn) {
        super(colorIn, "piece");

        if (color.equals("white")) {
            symbol = "P";
        } else {
            symbol = "p";
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

    //Pawn can move one or two spaces forward, has diagonal capture implementation
    //no en passant capture/movement. 
    @Override
    public boolean moveTo(int x, int y) {

        int moveForwardTwo;
        int moveForwardOne;
        int pawnRowOnPlySide;

        Square toSquare = ChessMain.board[moveToY][moveToX];

        if (color == "white") {
            moveForwardTwo = -2;
            moveForwardOne = -1;
            pawnRowOnPlySide = 6;
        } else {
            moveForwardTwo = 2;
            moveForwardOne = 1;
            pawnRowOnPlySide = 1;
        }

        
        //logic loop that allows for the initial 2 space movement ONLY at the
        //initial movement of the pawn.
        if (moveToY == moveFromY + moveForwardOne) {

            if ((moveToX == moveFromX - 1) || (moveToX == moveFromX + 1)) {
                if ((toSquare.getType().equals("empty")) && (!toSquare.getColor().equals(color))) {
                    return true;
                }
            } else if ((moveToX == moveFromX) && (toSquare.getType().equals("empty"))) {
                return true;
            }
        } else if ((moveToY == moveFromY + moveForwardTwo) && (moveToX == moveFromX) && (toSquare.getType().equals("empty"))) {
            if (moveFromY == pawnRowOnPlySide) {
                return true;
            }
        }

        return false;
    }
}
