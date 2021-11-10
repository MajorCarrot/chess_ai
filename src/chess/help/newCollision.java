package chess.help;

import chess.gui.chessBoard;

/*
  Bishop-1, King-2, Knight-3, Pawn-4, Queen-5?, Rook-6? collisions false means
  present and true means good to go naming first number rowO, second number
  columnO, third number itemO number, fourth number is color (white 0)
 */
 /*
 reference 

       a  b  c  d  e  f  g  h 
     +--+--+--+--+--+--+--+--+
    1|WR|WN|WB|WK|WQ|WB|WN|WR|1 
     +--+--+--+--+--+--+--+--+
    2|WP|WP|WP|WP|WP|WP|WP|WP|2 
     +--+--+--+--+--+--+--+--+ 
     ..
     +--+--+--+--+--+--+--+--+ 
    7|BP|BP|BP|BP|BP|BP|BP|BP|7
     +--+--+--+--+--+--+--+--+ 
    8|BR|BN|BB|BK|BQ|BB|BN|BR|8
     +--+--+--+--+--+--+--+--+ 
       a  b  c  d  e  f  g  h 
 
 */
/**
 * A class to check for collisions when the required details are passed
 *
 * @author Adithya
 */
public class newCollision extends moveValidator {

    /**
     * this method is to verify collisions for all pieces except queen it
     * filters out the piece using the item number and proceeds
     *
     * @param gameState it is the array which contains the gameState i.e. the
     * board
     * @param original the content of array from original position
     * @param finale the content of array from final position
     * @return whether there is collision or not
     */
    public static boolean collisionChecker(int gameState[][], int original, int finale) {
        int rowO = original / 1000 - 1;
        int rowF = finale / 1000 - 1;
        int columnO = original / 100 % 10 - 1;
        int columnF = finale / 100 % 10 - 1;
        int item = original % 100;

        if (gameState[rowF][columnF] % 10 == item % 10)//not the same color coin at final position
        {
            return false;
        }

        //for rook
        if (rowO == rowF && item / 10 == 6)//left and right for rook
        {
            if (columnO < columnF) {//this is when rook is to the left of final position
                columnO = columnO + 1;
                while (columnO < columnF) {
                    if (gameState[rowO][columnO] % 100 != 2) //if there is anything in between, rook is not allowed to execute the move
                    {
                        return false;
                    }
                    columnO++;
                }
                return true;
            } else {//when rook is to the right of final position
                columnO = columnO - 1;
                while (columnO > columnF) {
                    if (gameState[rowO][columnO] % 100 != 2) //if there is anything in between, rook is not allowed to execute the move
                    {
                        return false;
                    }
                    columnO--;
                }
                return true;
            }
        } else if (columnO == columnF && item / 10 == 6)//up and down for rook
        {
            if (rowO < rowF) {//when the rook is above the intended position, it comes down with increasing rows
                rowO = rowO + 1;
                while (rowO < rowF) {
                    if (gameState[rowO][columnO] % 100 != 2) //if there is anything in between, rook is not allowed to execute the move
                    {
                        return false;
                    }
                    rowO++;
                }
                return true;
            } else {//when the rook is below the intended position, it goes up with decreasing rows
                rowO = rowO - 1;
                while (rowO > rowF) {
                    if (gameState[rowO][columnO] % 100 != 2) //if there is anything in between, rook is not allowed to execute the move
                    {
                        return false;
                    }
                    rowO--;
                }
                return true;
            }
        }//for bishop
        else if (rowO < rowF && item / 10 == 1) {//up or down(same as rook)
            if (columnO < columnF) {//right... the diagonal condn checked in movesValidator
                rowO++;
                columnO++;

                while (rowO < rowF) {//could have used columnO < columnF as well
                    if (gameState[rowO][columnO] % 100 != 2) //if there is anything in between, bishop is not allowed to execute the move
                    {
                        return false;
                    }
                    rowO++;
                    columnO++;
                }
            } else if (columnO > columnF) {
                rowO += 1;
                columnO -= 1;

                while (rowO < rowF) {
                    if (gameState[rowO][columnO] % 100 != 2) {
                        return false;
                    }
                    rowO++;
                    columnO--;
                }
            }
        } else if (rowO > rowF && item / 10 == 1) {
            if (columnO < columnF) {
                rowO -= 1;
                columnO += 1;

                while (rowO > rowF) {
                    if (gameState[rowO][columnO] % 100 != 2) {
                        return false;
                    }
                    rowO--;
                    columnO++;
                }
            } else if (columnO > columnF) {
                rowO -= 1;
                columnO -= 1;

                while (rowO > rowF) {
                    if (gameState[rowO][columnO] % 100 != 2) {
                        return false;
                    }
                    rowO--;
                    columnO--;
                }
            }
        }
        return true;
    }

    /**
     * this method is to verify the move for the queen
     *
     * @param gameState it is the array which contains the gameState i.e. the
     * board
     * @param original the content of array from original position
     * @param finale the content of array from final position
     * @param s is the queen moving diagonally or along row/column
     * @return the move is valid or not
     */
    public static boolean collisionChecker(int gameState[][], int original, int finale, String s) {
        int rowO = original / 1000 - 1, rowF = finale / 1000 - 1, columnO = original / 100 % 10 - 1, columnF = finale / 100 % 10 - 1;

        if (gameState[rowF][columnF] % 10 == original % 10)//not the same color coin at final position
        {
            return false;
        }

        if (s.equals("Diagonal")) {
            if (rowO < rowF) {
                if (columnO < columnF) {
                    rowO += 1;
                    columnO += 1;

                    while (rowO < rowF) {
                        if (gameState[rowO][columnO] % 100 != 2) {
                            return false;
                        }
                        rowO++;
                        columnO++;
                    }
                } else if (columnO > columnF) {
                    rowO += 1;
                    columnO -= 1;

                    while (rowO < rowF) {
                        if (gameState[rowO][columnO] % 100 != 2) {
                            return false;
                        }
                        rowO++;
                        columnO--;
                    }
                }
            } else if (rowO > rowF) {
                if (columnO < columnF) {
                    rowO -= 1;
                    columnO += 1;

                    while (rowO > rowF) {
                        if (gameState[rowO][columnO] % 100 != 2) {
                            return false;
                        }
                        rowO--;
                        columnO++;
                    }
                } else if (columnO > columnF) {
                    rowO -= 1;
                    columnO -= 1;

                    while (rowO > rowF) {
                        if (gameState[rowO][columnO] % 100 != 2) {
                            return false;
                        }
                        rowO--;
                        columnO--;
                    }
                }
            }
        } else if (s.equals("Line")) {
            if (rowO == rowF)//left and right for queen
            {
                if (columnO < columnF) {
                    columnO = columnO + 1;
                    while (columnO < columnF) {
                        if (gameState[rowO][columnO] % 100 != 2) {
                            return false;
                        }
                        columnO++;
                    }
                    return true;
                } else {
                    columnO = columnO - 1;
                    while (columnO > columnF) {
                        if (gameState[rowO][columnO] % 100 != 2) {
                            return false;
                        }
                        columnO--;
                    }
                    return true;
                }
            } else if (columnO == columnF)//up and down for queen
            {
                if (rowO < rowF) {
                    rowO = rowO + 1;
                    while (rowO < rowF) {
                        if (gameState[rowO][columnO] % 100 != 2) {
                            return false;
                        }
                        rowO++;
                    }
                    return true;
                } else {
                    rowO = rowO - 1;
                    while (rowO > rowF) {
                        if (gameState[rowO][columnO] % 100 != 2) {
                            return false;
                        }
                        rowO--;
                    }
                    return true;
                }
            }
        }
        return true;
    }

    public static boolean CastlingChecker(int gameState[][], int original, int finale, boolean right) {
        int turn = original % 10;
        if (!checks.checkChecker(gameState, (turn + 1) % 2)) {
            switch (turn) {
                case 0:
                    gameState[finale / 1000 - 1][finale / 100 % 10] = finale / 100 * 100 + original % 100;
                    if (!checks.checkChecker(gameState, turn) && gameState[finale / 1000 - 1][finale / 100 % 10 - 1] % 10 != turn) {
                        gameState[finale / 1000 - 1][finale / 100 % 10] = (finale / 100 + 1) * 100 + 2;
                        gameState[finale / 1000 - 1][finale / 100 % 10 - 1] = finale / 100 * 100 + original % 100;
                        if (!checks.checkChecker(gameState, turn)) {
                            gameState = helper.undo(chessBoard.turn + 1, chessBoard.movesDone);
                            return true;
                        }
                    }
                    break;
                case 1:
                    gameState[finale / 1000 - 1][finale / 100 % 10] = finale / 100 * 100 + original % 100;
                    if (!checks.checkChecker(gameState, turn) && gameState[finale / 1000 - 1][finale / 100 % 10 - 1] % 10 != turn) {
                        gameState[finale / 1000 - 1][finale / 100 % 10] = (finale / 100 + 1) * 100 + 2;
                        gameState[finale / 1000 - 1][finale / 100 % 10 - 1] = finale / 100 * 100 + original % 100;
                        if (!checks.checkChecker(gameState, turn)) {
                            gameState = helper.undo(chessBoard.turn + 1, chessBoard.movesDone);
                            return true;
                        }
                    }
            }

        }
        return false;
    }
}
