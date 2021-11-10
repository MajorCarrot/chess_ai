/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.ai;

import chess.help.helper;
import java.util.List;

//ai always black
/**
 *
 * @author Adithya
 */
public class aiExecuter {

    private static final int[] WEIGHTS_FOR_ITEM = {0, 500, 1000000000, 300, 10, 900, 500};
    private static final byte[][] POSITION_WEIGHT
            = {{1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 3, 3, 3, 3, 2, 2},
            {2, 2, 3, 4, 4, 3, 2, 2},
            {2, 2, 3, 4, 4, 3, 2, 2},
            {2, 2, 3, 3, 3, 3, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2},
            {1, 1, 1, 1, 1, 1, 1, 1}};

    private static final byte[][] POSITION_WEIGHT_KING = {{4, 4, 4, 4, 4, 4, 4, 4},
    {3, 3, 3, 3, 3, 3, 3, 3},
    {2, 2, 2, 2, 2, 2, 2, 2},
    {1, 1, 1, 2, 2, 1, 1, 1},
    {1, 1, 1, 2, 2, 1, 1, 1},
    {2, 2, 2, 2, 2, 2, 2, 2},
    {2, 2, 2, 2, 2, 2, 2, 2},
    {3, 3, 3, 3, 3, 3, 3, 3}};

    static int executer(List<String> movesDone, int turn, List<String> doTheseMoves) {
        int stateValue;
        String req = "";
        int max = Integer.MIN_VALUE;
        int parallelState[][] = helper.setter();

        movesDone.forEach((String doneMoves) -> {
            int n = helper.StringToInt(parallelState, doneMoves);
            int original = n / 10000;
            int finale = n % 10000;
            parallelState[original / 1000 - 1][original / 100 % 10 - 1] = original / 100 * 100 + 2;
            parallelState[finale / 1000 - 1][finale / 100 % 10 - 1] = finale / 100 * 100 + original % 100;
        });

        for (String hasMove : doTheseMoves) {
            stateValue = stateEvaluator(parallelState, turn, hasMove);
            if (max < stateValue) {
                max = stateValue;
                req = hasMove;
            }
        }
        doTheseMoves.clear();
        return max;
    }

    static int stateEvaluator(int parallelState[][], int turn, String hasMove) {
        int stateValue = 0;
        int n = helper.StringToInt(parallelState, hasMove);
        int original = n / 10000;
        int finale = n % 10000;
        if(finale % 100 / 10 == 2)
            return Integer.MAX_VALUE - 1;
        parallelState[finale / 1000 - 1][finale / 100 % 10 - 1] = finale / 100 * 100 + original % 100;
        parallelState[original / 1000 - 1][original / 100 % 10 - 1] = original / 100 * 100 + 2;

        stateValue += boardEvaluator(parallelState, turn);

        parallelState[finale / 1000 - 1][finale / 100 % 10 - 1] = finale;
        parallelState[original / 1000 - 1][original / 100 % 10 - 1] = original;

        return stateValue;
    }

    static int boardEvaluator(int parallelState[][], int turn) {
        int boardValue = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (parallelState[i][j] % 10 != 2) {
                    if (parallelState[i][j] % 10 != turn % 2) {
                        if (parallelState[i][j] / 10 % 10 == 1) {
                            boardValue -= POSITION_WEIGHT_KING[i][j] * WEIGHTS_FOR_ITEM[2];
                        } else {
                            boardValue -= POSITION_WEIGHT[i][j] * WEIGHTS_FOR_ITEM[parallelState[i][j] % 100 / 10];
                        }
                    } else {
                        if (parallelState[i][j] / 10 % 10 == 1) {
                            boardValue += POSITION_WEIGHT_KING[i][j] * WEIGHTS_FOR_ITEM[2];
                        } else {
                            boardValue += POSITION_WEIGHT[i][j] * WEIGHTS_FOR_ITEM[parallelState[i][j] % 100 / 10];
                        }
                    }
                }
            }
        }
        return boardValue;
    }

    private static int itemWeight(int finale) {
        return WEIGHTS_FOR_ITEM[finale / 10 % 10];//0 none, 1 - 6 other items
    }
}
