/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.help;

import java.util.List;
import chess.gui.GamePage;
import chess.gui.chessBoard;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Adithya
 */
public class helper {

    /**
     *
     * @param move
     * @return
     */
    public static String IntToString(int move) {
        int original = move / 10000;
        int finale = move % 10000;
        String moveDone = "";
        switch (original / 100 % 10) {
            case 1:
                moveDone = "a";
                break;
            case 2:
                moveDone = "b";
                break;
            case 3:
                moveDone = "c";
                break;
            case 4:
                moveDone = "d";
                break;
            case 5:
                moveDone = "e";
                break;
            case 6:
                moveDone = "f";
                break;
            case 7:
                moveDone = "g";
                break;
            case 8:
                moveDone = "h";
                break;
        }
        moveDone += original / 1000 + "->";
        switch (finale / 100 % 10) {
            case 1:
                moveDone += "a";
                break;
            case 2:
                moveDone += "b";
                break;
            case 3:
                moveDone += "c";
                break;
            case 4:
                moveDone += "d";
                break;
            case 5:
                moveDone += "e";
                break;
            case 6:
                moveDone += "f";
                break;
            case 7:
                moveDone += "g";
                break;
            case 8:
                moveDone += "h";
                break;
        }
        moveDone += finale / 1000;
        return moveDone;
    }

    /**
     *
     * @param item
     * @return
     */
    public static String intItemToString(int item) {
        String whatever = "";
        switch (item) {
            case 1:
                whatever += "Bishop";
                break;
            case 2:
                whatever += "King";
                break;
            case 3:
                whatever += "Knight";
                break;
            case 4:
                whatever += "Pawn";
                break;
            case 5:
                whatever += "Queen";
                break;
            case 6:
                whatever += "Rook";
                break;
        }
        return whatever;
    }

    /**
     * @param b - boardState
     * @param gotMove - the move
     * @return - the original and final positions
     */
    public static int StringToInt(final int[][] b, String gotMove) {
        char ch = gotMove.charAt(0);
        int original = 0;
        switch (ch) {
            case 'a':
                original = 1;
                break;
            case 'b':
                original = 2;
                break;
            case 'c':
                original = 3;
                break;
            case 'd':
                original = 4;
                break;
            case 'e':
                original = 5;
                break;
            case 'f':
                original = 6;
                break;
            case 'g':
                original = 7;
                break;
            case 'h':
                original = 8;
                break;
        }
        original = b[Integer.parseInt(gotMove.charAt(1) + "") - 1][original - 1]; //updates initial position variable
        int finale = 0;
        ch = gotMove.charAt(4);
        switch (ch) {
            case 'a':
                finale = 1;
                break;
            case 'b':
                finale = 2;
                break;
            case 'c':
                finale = 3;
                break;
            case 'd':
                finale = 4;
                break;
            case 'e':
                finale = 5;
                break;
            case 'f':
                finale = 6;
                break;
            case 'g':
                finale = 7;
                break;
            case 'h':
                finale = 8;
                break;
        }
        finale = b[Integer.parseInt(gotMove.charAt(5) + "") - 1][finale - 1];
        return original * 10000 + finale;
    }

    /**
     *
     * @return The original Board State (in the beginning)
     */
    public static int[][] setter() {
        int[][] a = {{1160, 1230, 1310, 1420, 1550, 1610, 1730, 1860}, {2140, 2240, 2340, 2440, 2540, 2640, 2740, 2840}, {3102, 3202, 3302, 3402, 3502, 3602, 3702, 3802}, {4102, 4202, 4302, 4402, 4502, 4602, 4702, 4802}, {5102, 5202, 5302, 5402, 5502, 5602, 5702, 5802}, {6102, 6202, 6302, 6402, 6502, 6602, 6702, 6802}, {7141, 7241, 7341, 7441, 7541, 7641, 7741, 7841}, {8161, 8231, 8311, 8421, 8551, 8611, 8731, 8861}};
        return a;
    }

    /**
     *
     * @param turn - the number of turns over till game state
     * @param movesDone - the array with list of moves
     * @return undoState
     */
    public static int[][] undo(int turn, List<String> movesDone) {
        int[][] undoState = helper.setter();
        List<String> undoList = new ArrayList<>();
        GamePage.wt1.setText("");
        GamePage.bt1.setText("");
        
        GamePage.AI.aiMove = "";
        
        for (int i = 0; i < turn; i++) {
            String hasMove = movesDone.get(i);
            undoList.add(hasMove);
            int n = helper.StringToInt(undoState, hasMove);
            int original = n / 10000;
            int finale = n % 10000; 
            
            System.out.println(hasMove);
            //To print the pieces removed in the text area
            GamePage.wt1.append((finale % 10 == 1) ? "B " + helper.intItemToString(finale / 10 % 10) + "\n" : "");
            GamePage.bt1.append((finale % 10 == 0) ? "W " + helper.intItemToString(finale / 10 % 10) + "\n" : "");
            
            finale = finale / 100 * 100 + original % 100;
            undoState[finale / 1000 - 1][finale / 100 % 10 - 1] = finale;
            undoState[original / 1000 - 1][original / 100 % 10 - 1] = original / 100 * 100 + 2;
        }
        movesDone.clear();
        movesDone = undoList;
        GamePage.AI.MOVES_DONE = undoList;
        chessBoard.movesDone = undoList;
        renderPosition(undoState);
        return undoState;
    }

    /**
     *
     * @param gameState - the current Game State
     */
    public static void renderPosition(int[][] gameState) {
        int row = 0;
        int column;
        String rowBorder = " +--+--+--+--+--+--+--+--+";
        String columnNumber = "  a  b  c  d  e  f  g  h "; //creates rowBorders
        
        System.out.println(columnNumber);
        System.out.println(rowBorder);
        while (row < 8) {
            //prints each row using row details
            column = 0;
            String s = "";
            switch (row + 1) {
                case 1:
                    s += "1|";
                    break;
                case 2:
                    s += "2|";
                    break;
                case 3:
                    s += "3|";
                    break;
                case 4:
                    s += "4|";
                    break;
                case 5:
                    s += "5|";
                    break;
                case 6:
                    s += "6|";
                    break;
                case 7:
                    s += "7|";
                    break;
                case 8:
                    s += "8|";
                    break;
            }
            while (column < 8) {
                int val = gameState[row][column];
                int color = val % 10;
                int item = val % 100 / 10;
                switch (color) {
                    case 0:
                        s += "W";
                        break;
                    case 1:
                        s += "B";
                        break;
                    case 2:
                        s += " ";
                }
                switch (item) {
                    case 1:
                        s += "B|";
                        break;
                    case 2:
                        s += "K|";
                        break;
                    case 3:
                        s += "N|";
                        break;
                    case 4:
                        s += "P|";
                        break;
                    case 5:
                        s += "Q|";
                        break;
                    case 6:
                        s += "R|";
                        break;
                    case 0:
                        s += " |";
                        break;
                }
                column++;
            }
            switch (row + 1) {
                case 1:
                    s += "1";
                    break;
                case 2:
                    s += "2";
                    break;
                case 3:
                    s += "3";
                    break;
                case 4:
                    s += "4";
                    break;
                case 5:
                    s += "5";
                    break;
                case 6:
                    s += "6";
                    break;
                case 7:
                    s += "7";
                    break;
                case 8:
                    s += "8";
                    break;
            }
            System.out.println(s);
            System.out.println(rowBorder);
            row++;
        }
        System.out.println(columnNumber);
    }

    /**
     *
     * @param move
     * @return
     */
    public static List<String> nullChanger(List<String> move) {
        if (move == null) {
            return Collections.emptyList();
        } else {
            return move;
        }
    }
    
}
