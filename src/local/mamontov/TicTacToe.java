package local.mamontov;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by evgeny.mamontov on 22.06.2016.
 */

public class TicTacToe {

    final int FIELD_WIDTH = 3;
    final int FIELD_HEIGHT = 3;
    final int CELLS_COUNT = FIELD_HEIGHT * FIELD_HEIGHT;
    final String PLAYER1_NAME = "Player";
    final String PLAYER2_NAME = "Computer";
    final char PLAYER1_SIGN = 'X';
    final char PLAYER2_SIGN = 'O';
    final boolean PL = false;   // Human player
    final boolean PC = true;    // Computer player

    Scanner in = new Scanner(System.in);
    Player player1 = new Player(PLAYER1_NAME, PLAYER1_SIGN, PL);
    Player player2 = new Player(PLAYER2_NAME, PLAYER2_SIGN, PC);

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    private void game() {

        Field field = new Field();
        Player currentPlayer = player1;

        System.out.println("Игра началась!");

        for (int i = 0; i < CELLS_COUNT; i++) {

            if (currentPlayer.isComputer) {
                    field.showField();
                    System.out.println("Ход игрока " + currentPlayer.name);
                    field.setFieldValue(currentPlayer.doTurn(field.cellsArray), currentPlayer.sign);
                    System.out.println(currentPlayer.doTurn(field.cellsArray));
            } else {
                do {
                    field.showField();
                    System.out.println("Ход игрока " + currentPlayer.name);
                } while (field.setFieldValue(in.nextInt(), currentPlayer.sign));
            }
                /* Victory checks */
                if ((field.isWin(currentPlayer)) && (currentPlayer == player1)) {
                    field.showField();
                    System.out.println("Выиграл " + player1.name + " !!!");
                    System.exit(0);
                } else if ((field.isWin(currentPlayer)) && (currentPlayer == player2)) {
                    field.showField();
                    System.out.println("Выиграл " + player2.name + " !!!");
                    System.exit(0);
                }
            /* Switch to next player */
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }

        System.out.println("Игра закончилась ничьей!");
    }

    class Field {

        private int[][] cellsArray = new int[FIELD_WIDTH][FIELD_HEIGHT];
        /* initializing play field */
        Field() {
            int fieldNumber = 0;
            for (int i = 0; i < FIELD_HEIGHT; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    cellsArray[i][j] = fieldNumber++;
                }
            }
        }
        /* Make a move */
        boolean setFieldValue(int cell, char currentPlayerChar) {
            int cellX = cell / FIELD_WIDTH;
            int cellY = cell % FIELD_WIDTH;
            if ((cell >= 0) && (cell < CELLS_COUNT)
                && ((cellsArray[cellX][cellY] != ((int) PLAYER1_SIGN))
                && (cellsArray[cellX][cellY] != ((int) PLAYER2_SIGN)))){
                        cellsArray[cellX][cellY] = currentPlayerChar;
                        return false; //good cell
            }
            return true; //bad cell, try one more time
        }

        int getCell(int x, int y) {
            return cellsArray[x][y];
        }
        /* Chekin for a win */
        boolean isWin(Player currentPlayer) {

        int s = (int) currentPlayer.sign;
            for (int i = 0; i < FIELD_HEIGHT; i++) {
                if ((cellsArray[i][0] == s) && (cellsArray[i][1] == s) && (cellsArray[i][2] == s)) return true;

                if ((cellsArray[0][i] == s) && (cellsArray[1][i] == s) && (cellsArray[2][i] == s)) return true;
            }

            if (((cellsArray[0][0] == s) && (cellsArray[1][1] == s) && (cellsArray[2][2] == s)) ||
                ((cellsArray[2][0] == s) && (cellsArray[1][1] == s) && (cellsArray[0][2] == s))) return true;

            return false;
        }


        void showField() {

            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_HEIGHT; j++) {
                    if ((getCell(i, j) == (int) player1.sign)) {
                        System.out.print(player1.sign + " ");
                    } else if ((getCell(i, j) == (int) player2.sign)) {
                        System.out.print(player2.sign + " ");
                    } else {
                        System.out.print(getCell(i, j) + " ");
                    }
                }
                System.out.println("");
            }

        }

    }


    class Player {

        String name;
        char sign;
        boolean isComputer;
        int intNumber;

        Player(String name, char sign, boolean isComputer) {
            this.name = name;
            this.sign = sign;
            this.isComputer = isComputer;
            intNumber = (int) sign;
        }

        int doTurn(int[][] cellsArray) {
            Random random = new Random();
            int computersTurn;
            int cellX;
            int cellY;
                do {
                    computersTurn = random.nextInt(CELLS_COUNT);
                    cellX = computersTurn / FIELD_WIDTH;
                    cellY = computersTurn % FIELD_WIDTH;
                }
                while ((cellsArray[cellX][cellY] == ((int) PLAYER1_SIGN))
                    || (cellsArray[cellX][cellY] == ((int) PLAYER2_SIGN)));
            return computersTurn;
        }
    }

}
