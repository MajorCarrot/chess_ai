package chess.ai;

import chess.help.movesForPiece;
import chess.help.helper;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the AI... Have used my take on the MiniMax algorithm... Currently
 * implementing Alpha Beta Pruning
 *
 * @author Adithya
 */
public class aiNew {

    /**
     * the String corresponding to the best move
     */
    //public List<String> correspondingMove = new ArrayList<>();
    public String aiMove = "";
    /**
     * depth initializers
     */
    private final int MAX_DEPTH_LOW = 3;

    /**
     * It stores the number of pieces of WHITE and BLACK left
     */
    public byte WHITE = 16;
    public byte BLACK = 16;
    /**
     * a list to store the moves done
     */
    public List<String> MOVES_DONE = new ArrayList<>();

    /**
     * Initializes the AI to find the best move using minimax algorithm
     *
     * @param movesDone the list of moves done in the GUI
     * @param turn the number of turn passed till now
     */
    public void myAI(List<String> movesDone, int turn) {
        int l = movesDone.size();
        int i = 0;
        while (i < l) {
            this.MOVES_DONE.add(movesDone.get(i));
            i++;
        }

        int curState[][] = helper.setter();
        for(String hasMove : movesDone){
            int n = helper.StringToInt(curState, hasMove);
            int original = n / 10000;
            int finale = n % 10000;
            curState[finale / 1000 - 1][finale / 100 % 10 - 1] = finale / 100 * 100 + original % 100;
            curState[original / 1000 - 1][original / 100 % 10 - 1] = original / 100 * 100 + 2;
        }
        int depth = MAX_DEPTH_LOW;
        System.out.println("AI, "+ movesDone);
        helper.renderPosition(curState);
        Max(curState, depth, turn);        
    }

    /**
     * Generates all possible moves for a given parallelState
     *
     * @param parallelState The current state of the board in one of the
     * parallel boards imagined by the AI
     * @param turn The type of piece whose turn it is
     * @param movesDone The moves done till now
     * @return The list containing all the moves for that boardState
     */
    public List<String> generateMove(int[][] parallelState, int turn, List<String> movesDone) {
        List<String> generatedMove = new ArrayList<>();
        int doneNo = 0;
        outer:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (turn % 2 == 0 && doneNo <= 16) {
                    //if it is white's turn, then it generates move for white
                    if (parallelState[i][j] % 10 == 0) {
                        doneNo++;
                        generatedMove.addAll(helper.nullChanger(movesForPiece.moveMyPiece(parallelState[i][j], parallelState, movesDone)));
                    }
                } else if (turn % 2 == 1 && doneNo <= 16) {
                    //if it is blacks's turn, it generates moves for black
                    if (parallelState[7 - i][j] % 10 == 1) {
                        doneNo++;
                        generatedMove.addAll(helper.nullChanger(movesForPiece.moveMyPiece(parallelState[7 - i][j], parallelState, movesDone)));
                    }
                } else {//if all coins have moves generated, then it breaks the outer loop
                    break outer;
                }
            }
        }
        return helper.nullChanger(generatedMove);
    }

    /**
     * Finds the maximum possible value of the board for a given parallelState
     * after performing all possible moves
     *
     * @param parallelState The parallelState imagined by the AI
     * @param depth The level of depth currently reached (depth = 1 is least)
     * @param turn The piece whose turn it is (Black = 1, White = 0)
     * @return The maximum integer value
     */
    int Max(int[][] parallelState, int depth, int turn) {
        int max = Integer.MIN_VALUE;
        List<String> doTheseMoves = generateMove(parallelState, turn % 2, this.MOVES_DONE);
        //if depth is greater than 1 it generates the moves and if the depth is equal to the maximum depth, it stores the move
        if (depth > 1) {
            for (String hasMove : doTheseMoves) {

                MOVES_DONE.add(hasMove);
                //predictedState will predict the best move which will be made by opponent
                int predictedState = aiExecuter.boardEvaluator(parallelState, turn);
                predictedState -= Max(parallelState, depth - 1, turn + 1);
                MOVES_DONE.remove(hasMove);

                if (max < predictedState) {
                    max = predictedState;

                    //only if the depth is equal to the maximum depth state, the corresponding move should change
                    if (depth == MAX_DEPTH_LOW) {
                        aiMove = hasMove;
                    }
                }
            }
            doTheseMoves.clear();
            return max;
        } else {
            //if it is the final 
            max = aiExecuter.executer(MOVES_DONE, turn, doTheseMoves);
            doTheseMoves.clear();
            return max;
        }
    }
}
