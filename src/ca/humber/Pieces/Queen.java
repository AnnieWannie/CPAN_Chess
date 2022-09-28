package ca.humber.Pieces;

import ca.humber.Chess.ChessMain;
import ca.humber.Chess.Square;

//Moves but not as a queen chess piece should
public class Queen extends Piece {

    int moveFromX;
    int moveFromY;
    int moveToX;
    int moveToY;
    String type = "piece";

    public Queen(String colorIn) {
        super(colorIn, "piece");

        if (this.color.equals("white")) {
            symbol = "Q";
        } else {
            symbol = "q";
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

    //Planned to combine bishop and rook movement to get a functioning queen
    //sadly does not work as intended and queen can move but is not even close
    //to intended queen movement.
    @Override
    public boolean moveTo(int x, int y) {
        String direction;
        String type;

        if (moveToY == moveFromY) {
            if (moveToX > moveFromX) {
                direction = "right";
                type = "straight";
            } else {
                direction = "left";
                type = "straight";
            }
        } else if (moveToX == moveFromX) {
            if (moveToY > moveFromY) {
                direction = "bot";
                type = "straight";
            } else {
                direction = "top";
                type = "straight";
            }
        } else if (moveToX > moveFromX) {
            if (moveToY < moveFromY) {
                direction = "topRight";
                type = "diagonal";
            } else {
                direction = "botRight";
                type = "diagonal";
            }
        } else if (moveToX < moveFromX) {
            if (moveToY < moveFromY) {
                direction = "topLeft";
                type = "diagonal";
            } else {
                direction = "botLeft";
                type = "diagonal";
            }
        } else {
            return false;
        }

        Square tempTile;

        if (type.equals("diagonal")) {
            int moveDistance = Math.abs(moveToX - moveFromX);

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
                } else if ((diagMoveAway == moveDistance) && ((!tempTile.getColor().equals(color)) || (!tempTile.getType().equals("empty")))) {
                    return true;
                }
            }
        } else {
            if ((direction.equals("right")) || (direction.equals("left"))) {
                int positionDifferenceMax = Math.abs(moveToX - moveFromX); //positionDifferencement max depending on what the move to values are

                for (int positionDifference = 1; positionDifference <= positionDifferenceMax; positionDifference++) { //looping through squares on the rooks path
                    if (direction.equals("right")) {
                        tempTile = ChessMain.board[moveFromY][moveFromX + positionDifference];

                        if ((!tempTile.getType().equals("empty")) && (positionDifference != positionDifferenceMax)) {
                            return false;
                        } else if ((positionDifference == positionDifferenceMax) && ((tempTile.getType().equals("empty")) || (!tempTile.getColor().equals(color)))) {
                            return true;
                        }
                    } else {
                        tempTile = ChessMain.board[moveFromY][moveFromX - positionDifference];

                        if ((!tempTile.getType().equals("empty")) && (positionDifference != positionDifferenceMax)) {
                            return false;
                        } else if ((positionDifference == positionDifferenceMax) && ((tempTile.getType().equals("empty")) || (!tempTile.getColor().equals(color)))) {
                            return true;
                        }
                    }
                }
            } else {
                int positionDifferenceMax = Math.abs(moveToY - moveFromY);

                for (int positionDifference = 1; positionDifference <= positionDifferenceMax; positionDifference++) {

                    if (direction.equals("top")) {
                        tempTile = ChessMain.board[moveFromY - positionDifference][moveFromX];

                        if ((tempTile.getType().equals("empty")) && (positionDifference != positionDifferenceMax)) {
                            return false;
                        } else if ((positionDifference == positionDifferenceMax) && ((tempTile.getType().equals("empty")) || (!tempTile.getColor().equals(color)))) {
                            return true;
                        }
                    } else {
                        tempTile = ChessMain.board[moveFromY + positionDifference][moveFromX];

                        if ((!tempTile.getType().equals("empty")) && (positionDifference != positionDifferenceMax)) {
                            return false;
                        } else if ((positionDifference == positionDifferenceMax) && ((tempTile.getType().equals("empty")) || (!tempTile.getColor().equals(color)))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
