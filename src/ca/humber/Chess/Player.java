package ca.humber.Chess;

import java.util.Scanner;

class Player {

    private String name;
    private String color;
    private final Scanner scanner = new Scanner(System.in);

    Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    //method to gather either player 1 or player 2's 
    //movement inputs from the main game logic.
    public int[][] getMove() {

        int[][] move = new int[2][2];
        int moveY = -1;
        int moveX = -1;
        for (int playerDecision = 1; playerDecision <= 2; playerDecision++) {

            //While loop will repeat till a valid move is returned
            //to the Main class.
            while (true) {
                if (playerDecision == 1) {
                    System.out.print(name + ", input your column and row combination to select your piece. (EX:3 2)\n>"); //prompt
                } else {
                    System.out.print(name + ", input your column and row combination to select a tile to move to. (EX:3 4)\n>");
                }

                //Block of code to help minimize the possibility of accidentally crashing
                //the game. If the user is intentionally trying to incite an error though
                //they will likely succeed.
                String moveTemp = scanner.nextLine();
                if (moveTemp.contains(" ")) {
                    String[] moveTokens = moveTemp.split(" ");
                    moveX = Integer.valueOf(moveTokens[0]);
                    moveY = Integer.valueOf(moveTokens[1]);
                }

                //check that user input is within board bounds
                //unfortunately there are ways for the player to 
                //still crash the game via invalid inputs
                //need to implement throw exceptions
                //and (or) greater depth of input possibilities to ensure
                //the user is giving a valid input
                if (moveX < 9 && moveX > 0 && moveY < 9 && moveY > 0) {
                    int x = moveX - 1;
                    int y = moveY;

                    if (x != -1) {
                        y = 8 - y;
                        int tempArray[] = {x, y};
                        if (playerDecision == 1) {
                            if (ChessMain.board[y][x].getType() == "empty" || ChessMain.board[y][x].getColor() != color) {

                                //temp array to flag illegal move and 
                                //restart the loop until a valid move input.
                                tempArray[0] = -1;
                                tempArray[1] = -1;
                                int[][] errorArray = {tempArray, tempArray};
                                return errorArray;
                            }
                        }

                        move[playerDecision - 1] = tempArray;
                        break;
                    }
                }
            }
        }
        return move;
    }
}
