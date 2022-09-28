package ca.humber.Pieces;

import ca.humber.Chess.ChessMain;
import ca.humber.Chess.Square;

//Behaves as bishop chess piece should
public class Bishop extends Piece {

    int moveFromX;
    int moveFromY;
    int moveToX;
    int moveToY;
    String type = "piece";

    public Bishop(String colorIn) {
        super(colorIn, "piece");

        if (color.equals("white")) {
            symbol = "B";
        } else {
            symbol = "b";
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

    //Implements diagonal movement for the bishop piece
    @Override
    public boolean moveTo(int x, int y) {
        Square toSquare = ChessMain.board[moveToY][moveToX];

        int moveDistance = Math.abs(moveToX - moveFromX);

        String direction;

        if (moveToX > moveFromX) {
            if (moveToY < moveFromY) {
                direction = "topRight";
            } else {
                direction = "botRight";
            }
        } else {
            if (moveToY < moveFromY) {
                direction = "topLeft";
            } else {
                direction = "botLeft";
            }
        }

        Square tempTile;

        for (int diagMoveAway = 1; diagMoveAway <= moveDistance; diagMoveAway++) {

            switch (direction) {
                case "topRight":
                    tempTile = ChessMain.board[moveFromY - diagMoveAway][moveFromX + diagMoveAway];
                    break;
                case "botRight":
                    tempTile = ChessMain.board[moveFromY + diagMoveAway][moveFromX + diagMoveAway];
                    break;
                case "topLeft":
                    tempTile = ChessMain.board[moveFromY - diagMoveAway][moveFromX - diagMoveAway];
                    break;
                default:
                    tempTile = ChessMain.board[moveFromY + diagMoveAway][moveFromX - diagMoveAway];
                    break;
            }

            if ((!tempTile.getType().equals("empty")) && (diagMoveAway != moveDistance)) {
                return false;
            } else if ((diagMoveAway == moveDistance) && ((!tempTile.getColor().equals(color)) || (tempTile.getType().equals("empty")))) {
                return true;
            }
        }
        return false;
    }
}
