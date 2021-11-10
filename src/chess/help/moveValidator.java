package chess.help;

//false means do not proceed true means ok
import chess.gui.chessBoard;

//Bishop-1, King-2, Knight-3, Pawn-4, Queen-5, Rook-6
/**
 *
 * @author Adithya
 */
public class moveValidator extends chessBoard {

    /**
     * Validates the move which has been passed after checking for collisions as
     * well
     *
     * @param gameState it is the array which contains the gameState i.e. the
     * board
     * @param original the content of array from original position
     * @param finale the content of array from final position
     * @return whether the move is valid (completely) or not
     */
    public static boolean checker(int gameState[][], int original, int finale) {

        int rowO = original / 1000 - 1;
        int columnO = original / 100 % 10 - 1;
        int rowF = finale / 1000 - 1;
        int columnF = finale / 100 % 10 - 1;
        int item = original % 100;

        if (original % 10 == gameState[rowF][columnF] % 10) {
            return false;
        }

        switch (item) {
            //for bishop
            case 10:
            case 11:
                if (Math.abs(rowO - rowF) == Math.abs(columnO - columnF)) {
                    return newCollision.collisionChecker(gameState, original, finale);
                } else {
                    return false;
                }

            //for king
            case 20:
            case 21:
                if ((rowO == rowF && Math.abs(columnO - columnF) == 1) || (columnO == columnF && Math.abs(rowO - rowF) == 1)) {
                    return newCollision.collisionChecker(gameState, original, finale);
                } else if (Math.abs(rowO - rowF) == 1 && Math.abs(columnO - columnF) == 1) {
                    return newCollision.collisionChecker(gameState, original, finale);
                } else if (Math.abs(columnO - columnF) == 2) {
                    if (columnO > columnF) {
                        return newCollision.CastlingChecker(gameState, original, finale, true);
                    } else if (columnO < columnF) {
                        return newCollision.CastlingChecker(gameState, original, finale, true);
                    }
                } else {
                    return false;
                }
                
            //for knight    
            case 30:
            case 31:
                if (Math.abs(rowO - rowF) == 1 && Math.abs(columnO - columnF) == 2) {
                    return newCollision.collisionChecker(gameState, original, finale);
                } else if (Math.abs(rowO - rowF) == 2 && Math.abs(columnO - columnF) == 1) {
                    return newCollision.collisionChecker(gameState, original, finale);
                } else {
                    return false;
                }

            //for Pawn
            case 40:
            case 41:
                boolean originOrNot = false;
                switch (original) {
                    case 2140:
                    case 2240:
                    case 2340:
                    case 2440:
                    case 2540:
                    case 2640:
                    case 2740:
                    case 2840:
                    case 7141:
                    case 7241:
                    case 7341:
                    case 7441:
                    case 7541:
                    case 7641:
                    case 7741:
                    case 7841:
                        originOrNot = true;
                }

                if (originOrNot == true) {
                    if (columnO == columnF && Math.abs(rowO - rowF) == 2
                            && ((original % 10 == 1 && gameState[rowF + 1][columnF] % 100 == 2 && gameState[rowF][columnF] % 100 == 2)
                            || (original % 10 == 0 && gameState[rowF - 1][columnF] % 100 == 2 && gameState[rowF][columnF] % 100 == 2)))//checks two steps
                    {
                        return true;
                    }
                }

                if (columnO == columnF && Math.abs(rowO - rowF) == 1) {
                    if (item % 10 == 1 && (rowO - rowF) == 1 && gameState[rowF][columnF] % 100 == 2) {
                        return true;
                    } else if (item % 10 == 0 && (rowO - rowF) == -1 && gameState[rowF][columnF] % 100 == 2) {
                        return true;
                    }
                    return false;
                } else if (Math.abs(rowO - rowF) == 1 && Math.abs(columnO - columnF) == 1 && gameState[rowF][columnF] % 10 != item % 10 && gameState[rowF][columnF] % 10 != 2) {
                    if (item % 10 == 1 && (rowO - rowF) == 1) {
                        return true;
                    } else if (item % 10 == 0 && (rowO - rowF) == -1) {
                        return true;
                    }
                    return false;
                } else {
                    return false;
                }

            //for queen
            case 50:
            case 51:
                if (Math.abs(rowO - rowF) == Math.abs(columnO - columnF)) {
                    return newCollision.collisionChecker(gameState, original, finale, "Diagonal");
                } else if ((rowO == rowF && columnO != columnF) || (columnO == columnF && rowO != rowF)) {
                    return newCollision.collisionChecker(gameState, original, finale, "Line");
                } else {
                    return false;
                }

            //for rook
            case 60:
            case 61:
                if ((rowO == rowF && columnO != columnF) || (columnO == columnF && rowO != rowF)) {
                    return newCollision.collisionChecker(gameState, original, finale);
                } else {
                    return false;
                }
        }
        return false;
    }
}
