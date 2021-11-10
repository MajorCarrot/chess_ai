/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.gui;

import chess.help.checks;
import chess.help.helper;
import chess.help.moveValidator;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Adithya
 */
public class chessBoard {

    /**
     * A list to store all the moves done
     */
    public static List<String> movesDone = new ArrayList<>();

    /**
     * the Item which was removed
     */
    public int item;

    /**
     * The board state has the arrangement of the board at any instant
     */
    public int boardState[][] = helper.setter();

    /**
     * The number of turns which has passed... If it is even, it is White's turn
     * else it is Black's turn
     */
    public static int turn = 0;

    /**
     * This executes any given move and coordinates the working of the program
     * right from updating the array with the boardState to checking whether the
     * move is valid or not and displaying messages of check/checkmate
     *
     * @param gotMove The move which has been passed from GamePage
     * @return Whether the move was successfully executed or not
     */
    public boolean executer(String gotMove) {
        try {
            int posn = helper.StringToInt(boardState, gotMove);
            int original = posn / 10000;
            int finale = posn % 10000;

            if (original != finale && original % 10 == turn % 2) {

                boolean valid = moveValidator.checker(boardState, original, finale);

                if (valid) {
                    movesDone.add(turn, gotMove);

                    boolean checkValidator = staleMate(boardState, gotMove);
                    
                    if (checkValidator) {
                        System.out.println("Enter a valid move (Check for U)");
                        JOptionPane.showMessageDialog(null, "Enter a valid move (Check for U)");
                        movesDone.remove(turn);
                        return false;
                    }
                    
                    System.out.println("Good Move");

                    updater(original, finale);

                    if (turn % 2 == 1) {
                        System.out.println("White's turn");
                    } else {
                        System.out.println("Black's turn");
                    }
                    return true;
                } else {
                    System.out.println("Enter a valid move");
                    JOptionPane.showMessageDialog(null, "Enter a valid move");
                    return false;
                }
            } else {
                System.out.println("Enter a valid move");
                JOptionPane.showMessageDialog(null, "Enter a valid move");
                return false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }

    /**
     * This function updates the array renders the position in output window,
     * displays messages of check and checkmate
     *
     * @param original The original position of the piece moved
     * @param finale What is there in the final position to which the piece is
     * moved
     */
    void updater(int original, int finale)//finale->Final, original->initial
    {
        item = itemRemoved(finale);
        
        //updates the arra
        boardState[finale / 1000 - 1][finale / 100 % 10 - 1] = finale / 100 * 100 + original % 100;
        boardState[original / 1000 - 1][original / 100 % 10 - 1] = original / 100 * 100 + 2;

        //prints the boardstate after executing the move
        helper.renderPosition(boardState);

        //if there is a check present, it checks for checkmate
        if (checks.checkChecker(boardState, turn % 2)) {
            List<String> moves = helper.nullChanger(GamePage.AI.generateMove(boardState, turn, movesDone));
            if (moves.isEmpty()) {
                JOptionPane.showMessageDialog(null, "CHECKMATE!!!!!!");
                System.out.println("CHECKMATE!!!!!!");
                if (turn % 2 == 0) {
                    JOptionPane.showMessageDialog(null, "White won");
                } else {
                    JOptionPane.showMessageDialog(null, "Black won");
                }
            }
            JOptionPane.showMessageDialog(null, "CHECK!!!!!!");
            System.out.println("CHECK!!!!!!");
        }

    }

    /**
     * Finds the piece removed and returns the number correspondingly
     *
     * @param finale The array value at the final position (Before executing the
     * move)
     * @return The number corresponding to the piece removed
     */
    int itemRemoved(int finale) {
        int removed = boardState[finale / 1000 - 1][finale / 100 % 10 - 1] % 100 / 10;
        return removed;
    }

    /**
     * The function checks for stalemate and returns true if there is a
     * stalemate.
     *
     * @param parallelBoard it is the current board state which will be updated
     * and on which stalemate would be checked
     * @param hasMove The move to be executed
     * @return Whether the move is valid or not from stalemate point of view
     */
    boolean staleMate(int parallelBoard[][], String hasMove) {
        int n = helper.StringToInt(parallelBoard, hasMove);
        int original = n / 10000;
        int finale = n % 10000;

        /**
         * Executes the move before checking for stalemate
         */
        parallelBoard[finale / 1000 - 1][finale / 100 % 10 - 1] = n % 10000 / 100 * 100 + original % 100;
        parallelBoard[original / 1000 - 1][original / 100 % 10 - 1] = original / 100 * 100 + 2;

        /**
         * Note: turn + 1 because the person whose turn it is has to be checked
         * for check in the case of stalemate and this means that it should
         * appear as if the other person has done the move
         */
        boolean checkValidator = checks.checkChecker(parallelBoard, (turn + 1) % 2);

        /**
         * Note: changing the value to what it was because the original array
         * gets affected otherwise
         */
        parallelBoard[finale / 1000 - 1][finale / 100 % 10 - 1] = finale;
        parallelBoard[original / 1000 - 1][original / 100 % 10 - 1] = original;
        return checkValidator;
    }

}
