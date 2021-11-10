package chess.help;

/**
 * To check whether there is a check for the king
 *
 * @author Adithya
 */
public class checks {

    /**
     * The method checks whether there is a check given the gameState and the
     * coin which did the last move (Note: false means no check and true means 
     * check is present)
     *
     * @param gameState current Game State
     * @param last last Moved type (white/black)
     * @return check or not
     */
    public static boolean checkChecker(int gameState[][], int last) {
        int row = 0, column = 0;
        last = last % 2;
        boolean kingPos = true;
        if (last == 1) {
            row = 0;
            column = 0;
            outer:
            while (kingPos == true)//finds position of white king when black has made the move
            {
                while (column < 8) {
                    if (gameState[row][column] % 100 == 20) {
                        break outer;
                    }
                    column++;
                }
                row++;
                column = 0;
            }
        } else if (last == 0) {
            row = 7;
            column = 7;
            outer:
            while (kingPos == true && !(row < 0))//finds position of black king when white has made the move
            {
                while (column >= 0) {
                    if (gameState[row][column] % 100 == 21) {
                        break outer;
                    }
                    column--;
                }
                row--;
                column = 7;
            }
        }
        
        boolean active1 = true;
        boolean active2 = true;
        int count = 1;
        
        if(column == -1 || column == 8 || row == -1 || row == 8) {
            return false;
        }
        
        while (column - count >= 0 || column + count < 8)//along row
        {
            if (column - count >= 0 && active1 == true) {
                if (gameState[row][column - count] % 100 != 2) {
                    //not same as the king and is either queen or rook
                    if (gameState[row][column - count] % 10 == last && 
                            (gameState[row][column - count] % 100 / 10 == 6 
                            || gameState[row][column - count] % 100 / 10 == 5))
                    {
                        return true;
                    } else {
                        active1 = false;
                    }
                }
            }
            if (column + count < 8 && active2 == true) {
                if (gameState[row][column + count] % 100 != 2) {
                    if (gameState[row][column + count] % 10 == last && (gameState[row][column + count] % 100 / 10 == 6 || gameState[row][column + count] % 100 / 10 == 5))//not same as the king
                    {
                        return true;
                    } else {
                        active2 = false;
                    }
                }
            }
            count++;
        }

        count = 1;
        active1 = true;
        active2 = true;
        boolean active3 = true, active4 = true;

        //If there is nothing it boolean variables remain true and loop goes on. 
        //If there is something which threatens king then it returns true
        //If there is something which does not threaten the king, it makes active false
        while ((column - count >= 0 || column + count < 8) && (row - count >= 0 || row + count < 8))//along diagonals
        {
            if (row - count >= 0) {
                if (column - count >= 0 && active1 == true) {
                    if (gameState[row - count][column - count] % 100 != 2) {
                        if (gameState[row - count][column - count] % 10 == last && (gameState[row - count][column - count] % 100 / 10 == 1 || gameState[row - count][column - count] % 100 / 10 == 5))//not same as the king
                        {
                            return true;
                        } else {
                            active1 = false;
                        }
                    }
                }
                if (column + count < 8 && active2 == true) {
                    if (gameState[row - count][column + count] % 100 != 2) {
                        if (gameState[row - count][column + count] % 10 == last && (gameState[row - count][column + count] % 100 / 10 == 1 || gameState[row - count][column + count] % 100 / 10 == 5))//not same as the king
                        {
                            return true;
                        } else {
                            active2 = false;
                        }
                    }
                }
            }

            if (row + count < 8) {
                if (column - count >= 0 && active3 == true) {
                    if (gameState[row + count][column - count] % 100 != 2) {
                        if (gameState[row + count][column - count] % 10 == last && (gameState[row + count][column - count] % 100 / 10 == 1 || gameState[row + count][column - count] % 100 / 10 == 5))//not same as the king
                        {
                            return true;
                        } else {
                            active3 = false;
                        }
                    }
                }

                if (column + count < 8 && active4 == true) {
                    if (gameState[row + count][column + count] % 100 != 2) {
                        if (gameState[row + count][column + count] % 10 == last && (gameState[row + count][column + count] % 100 / 10 == 1 || gameState[row + count][column + count] % 100 / 10 == 5))//not same as the king
                        {
                            return true;
                        } else {
                            active4 = false;
                        }
                    }
                }
            }
            count++;
        }

        count = 1;
        active1 = true;
        active2 = true;

        while (row - count >= 0 || row + count < 8)//along column
        {
            if (row - count >= 0 && active1 == true) {
                if (gameState[row - count][column] % 100 != 2) {
                    if (gameState[row - count][column] % 10 == last && (gameState[row - count][column] % 100 / 10 == 6 || gameState[row - count][column] % 100 / 10 == 5))//not same as the king
                    {
                        return true;
                    } else {
                        active1 = false;
                    }
                }
            }
            if (row + count < 8 && active2 == true) {
                if (gameState[row + count][column] % 100 != 2) {
                    if (gameState[row + count][column] % 10 == last && (gameState[row + count][column] % 100 / 10 == 6 || gameState[row + count][column] % 100 / 10 == 5))//not same as the king
                    {
                        return true;
                    } else {
                        active2 = false;
                    }
                }
            }
            count++;
        }

        if (row + 2 < 8) {
            if (column + 1 < 8) {
                if (last == 0 && gameState[row + 2][column + 1] % 100 == 30) {
                    return true;
                } else if (last == 1 && gameState[row + 2][column + 1] % 100 == 31) {
                    return true;
                }
            }

            if (column - 1 >= 0) {
                if (last == 0 && gameState[row + 2][column - 1] % 100 == 30) {
                    return true;
                } else if (last == 1 && gameState[row + 2][column - 1] % 100 == 31) {
                    return true;
                }
            }
        }

        if (row + 1 < 8) {
            if (column + 2 < 8) {
                if (last == 0 && gameState[row + 1][column + 2] % 100 == 30) {
                    return true;
                } else if (last == 1 && gameState[row + 1][column + 2] % 100 == 31) {
                    return true;
                }
            }

            if (column - 2 >= 0) {
                if (last == 0 && gameState[row + 1][column - 2] % 100 == 30) {
                    return true;
                } else if (last == 1 && gameState[row + 1][column - 2] % 100 == 31) {
                    return true;
                }
            }
        }

        if (row - 2 >= 0) {
            if (column + 1 < 8) {
                if (last == 0 && gameState[row - 2][column + 1] % 100 == 30) {
                    return true;
                } else if (last == 1 && gameState[row - 2][column + 1] % 100 == 31) {
                    return true;
                }
            }

            if (column - 1 >= 0) {
                if (last == 0 && gameState[row - 2][column - 1] % 100 == 30) {
                    return true;
                } else if (last == 1 && gameState[row - 2][column - 1] % 100 == 31) {
                    return true;
                }
            }
        }

        if (row - 1 >= 0) {
            if (column + 2 < 8) {
                if (last == 0 && gameState[row - 1][column + 2] % 100 == 30) {
                    return true;
                } else if (last == 1 && gameState[row - 1][column + 2] % 100 == 31) {
                    return true;
                }
            }

            if (column - 2 >= 0) {
                if (last == 0 && gameState[row - 1][column - 2] % 100 == 30) {
                    return true;
                } else if (last == 1 && gameState[row - 1][column - 2] % 100 == 31) {
                    return true;
                }
            }
        }
        //The following set is for king
        if (row - 1 >= 0) {
            if (column - 1 >= 0) {
                if (last == gameState[row - 1][column - 1] % 10 && gameState[row - 1][column - 1] % 100 / 10 == 2) {
                    return true;
                }
            }
            if (column + 1 < 8) {
                if (last == gameState[row - 1][column + 1] % 10 && gameState[row - 1][column + 1] % 100 / 10 == 2) {
                    return true;
                }
            }
            if (last == gameState[row - 1][column] % 10 && gameState[row - 1][column] % 100 / 10 == 2) {
                return true;
            }
        }

        if (row + 1 < 8) {
            if (column - 1 >= 0) {
                if (last == gameState[row + 1][column - 1] % 10 && gameState[row + 1][column - 1] % 100 / 10 == 2) {
                    return true;
                }
            }
            if (column + 1 < 8) {
                if (last == gameState[row + 1][column + 1] % 10 && gameState[row + 1][column + 1] % 100 / 10 == 2) {
                    return true;
                }
            }
            if (last == gameState[row + 1][column] % 10 && gameState[row + 1][column] % 100 / 10 == 2) {
                return true;
            }
        }

        if (column - 1 >= 0) {
            if (last == gameState[row][column - 1] % 10 && gameState[row][column - 1] % 100 / 10 == 2) {
                return true;
            }
        }

        if (column + 1 < 8) {
            if (last == gameState[row][column + 1] % 10 && gameState[row][column + 1] % 100 / 10 == 2) {
                return true;
            }
        }
        //The following set is for pawn
        if (row - 1 >= 0) {
            if (column - 1 >= 0) {
                if (last == gameState[row - 1][column - 1] % 10 && gameState[row - 1][column - 1] % 100 == 40) {
                    return true;
                }
            }
            if (column + 1 < 8) {
                if (last == gameState[row - 1][column + 1] % 10 && gameState[row - 1][column + 1] % 100 == 40) {
                    return true;
                }
            }
        }

        if (row + 1 < 8) {
            if (column - 1 >= 0) {
                if (last == gameState[row + 1][column - 1] % 10 && gameState[row + 1][column - 1] % 100 == 41) {
                    return true;
                }
            }
            if (column + 1 < 8) {
                if (last == gameState[row + 1][column + 1] % 10 && gameState[row + 1][column + 1] % 100 == 41) {
                    return true;
                }
            }
        }

        return false;
    }
}
