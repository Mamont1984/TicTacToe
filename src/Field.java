/**
 * Created by evgeny.mamontov on 03.10.2016.
 */
public class Field {

    final int FIELD_WIDTH = 3;
    final int FIELD_HEIGHT = 3;

    private int[][] fld = new int[FIELD_WIDTH][FIELD_HEIGHT];

    void set(int x, int y, boolean _playerOne) {
        if (_playerOne) fld[x][y] =  1;
        else            fld[x][y] = -1;
    }

    char get(int x, int y) {
        if ( fld[x][y] ==  1) return 'Х';
        if ( fld[x][y] == -1) return 'О';
        return '.';
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

        for (int i = 0; i < 3; i++ ) {
            a += fld[i][0];   //
            b += fld[i][1];
            c += fld[i][2];
            x += fld[0][i];
            y += fld[1][i];
            z += fld[2][i];
            k += fld[i][i];   //диагональ 1
            l += fld[i][2-i]; //диагональ 2
        }

        if ((a == -3) || (b == -3) || (c == -3)  // столбцы test
            || (x == -3) ||(y == -3) ||(z == -3) // строки test
            || (k == -3) ||(l == -3)) return 2;  // диагонали test

        if ((a == 3) || (b == 3) || (c == 3)     // столбцы test
                || (x == 3) ||(y == 3) ||(z == 3)// строки test
                || (k == 3) ||(l == 3)) return 1;// диагонали test

        return 0;
    }

    void render(int teak, boolean _playerOne) {

        if (_playerOne) System.out.println("Ходит крестик");
        else            System.out.println("Ходит нолик");

        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                System.out.print(get(i,j)+ " ");
            }
            System.out.println("");
        }

    }
}
