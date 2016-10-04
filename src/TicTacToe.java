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
    final boolean PL = false;
    final boolean PC = true;

    Scanner in = new Scanner(System.in);
    Player player1 = new Player(PLAYER1_NAME, PLAYER1_SIGN, PL);
    Player player2 = new Player(PLAYER2_NAME, PLAYER2_SIGN, PC);

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    private void game() {
        /* initialization */
        Field field = new Field();
        field.init();
        Player currentPlayer = player1;

        System.out.println("Игра началась!");

        for (int i = 0; i < CELLS_COUNT; i++) {
            do {
                field.showField();
                System.out.println("Ход игрока " + currentPlayer.name);
            } while (field.setFieldValue(in.nextInt(), currentPlayer.sign));
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
                }

            // if (field.checkWin() != 0) break;
        }

        System.out.println("Игра закончилась ничьей!");
    }

    class Field {

        private int[][] cellsArray = new int[FIELD_WIDTH][FIELD_HEIGHT];

        private void init() {
            int fieldNumber = 1;
            for (int i = 0; i < FIELD_HEIGHT; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    cellsArray[i][j] = fieldNumber++;
                }
            }
        }

        boolean setFieldValue(int cell, char currentPlayerChar) {
            int cellX = cell / FIELD_WIDTH;
            int cellY = cell % FIELD_WIDTH;
            if ((cell > 0) && (cell <= CELLS_COUNT)
                && ((cellsArray[cellX][cellY] != ((int) PLAYER1_SIGN))
                && (cellsArray[cellX][cellY] != ((int) PLAYER2_SIGN)))){
                        cellsArray[cellX][cellY] = currentPlayerChar;
                        return false;
            }
            return true;
        }

        int getCell(int x, int y) {
            return cellsArray[x][y];
        }

        int checkWin() {

            int a = 0;
            int b = 0;
            int c = 0;
            int x = 0;
            int y = 0;
            int z = 0;
            int k = 0;
            int l = 0;

            for (int i = 0; i < 3; i++) {
                a += cellsArray[i][0];   //
                b += cellsArray[i][1];
                c += cellsArray[i][2];
                x += cellsArray[0][i];
                y += cellsArray[1][i];
                z += cellsArray[2][i];
                k += cellsArray[i][i];   //диагональ 1
                l += cellsArray[i][2 - i]; //диагональ 2
            }

            if ((a == -3) || (b == -3) || (c == -3)  // столбцы test
                    || (x == -3) || (y == -3) || (z == -3) // строки test
                    || (k == -3) || (l == -3)) return 2;  // диагонали test

            if ((a == 3) || (b == 3) || (c == 3)     // столбцы test
                    || (x == 3) || (y == 3) || (z == 3)// строки test
                    || (k == 3) || (l == 3)) return 1;// диагонали test

            return 0;
        }


        void showField() {

            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_HEIGHT; j++) {
                    System.out.print(getCell(i, j) + " ");
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
    }
}
