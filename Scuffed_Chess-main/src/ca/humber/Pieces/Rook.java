package ca.humber.Pieces;

import ca.humber.Chess.ChessMain;
import ca.humber.Chess.Square;

//Behaves as a rook chess piece should
public class Rook extends Piece {

    int moveFromX;
    int moveFromY;
    int moveToX;
    int moveToY;
    String type = "piece";

    public Rook(String colorIn) {
        super(colorIn, "piece");

        if (color.equals("white")) {
            symbol = "R";
        } else {
            symbol = "r";
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
    public boolean moveTo(int x, int y) {

        String direction;

        if (moveToY == moveFromY) {
            if (moveToX > moveFromX) {
                direction = "right";
            } else {
                direction = "left";
            }
        } else if (moveToX == moveFromX) {
            if (moveToY > moveFromY) {
                direction = "bot";
            } else {
                direction = "top";
            }
        } else {
            return false;
        }

        Square tempTile;

        if ((direction.equals("right")) || (direction.equals("left"))) {
            int moveDiffMax = Math.abs(moveToX - moveFromX);

            for (int moveDiff = 1; moveDiff <= moveDiffMax; moveDiff++) {
                if (direction.equals("right")) {
                    tempTile = ChessMain.board[moveFromY][moveFromX + moveDiff];

                    if ((!tempTile.getType().equals("empty")) && (moveDiff != moveDiffMax)) {
                        return false;
                    } else if ((moveDiff == moveDiffMax) && ((tempTile.getType().equals("empty")) || (!tempTile.getColor().equals(color)))) {
                        return true;
                    }
                } else {
                    tempTile = ChessMain.board[moveFromY][moveFromX - moveDiff];

                    if ((!tempTile.getType().equals("empty")) && (moveDiff != moveDiffMax)) {
                        return false;
                    } else if ((moveDiff == moveDiffMax) && ((tempTile.getType().equals("empty")) || (!tempTile.getColor().equals(color)))) {
                        return true;
                    }
                }
            }
        } else {
            int moveDiffMax = Math.abs(moveToY - moveFromY); 

            for (int moveDiff = 1; moveDiff <= moveDiffMax; moveDiff++) { 

                if (direction.equals("top")) {
                    tempTile = ChessMain.board[moveFromY - moveDiff][moveFromX];

                    if ((!tempTile.getType().equals("empty")) && (moveDiff != moveDiffMax)) {
                        return false;
                    } else if ((moveDiff == moveDiffMax) && ((tempTile.getType().equals("empty")) || (!tempTile.getColor().equals(color)))) {
                        return true;
                    }
                } else {
                    tempTile = ChessMain.board[moveFromY + moveDiff][moveFromX];

                    if ((!tempTile.getType().equals("empty")) && (moveDiff != moveDiffMax)) {
                        return false;
                    } else if ((moveDiff == moveDiffMax) && ((tempTile.getType().equals("empty")) || (!tempTile.getColor().equals(color)))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
