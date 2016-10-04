import java.util.Scanner;

/**
 * Created by evgeny.mamontov on 22.06.2016.
 */

public class TicTacToe {

    Scanner in = new Scanner(System.in);
    Field field = new Field();
    String gameOver[] = {"Ничья","Победил Х","Победил O"};
    int x,y;
    boolean playerOne;

    public static void main(String[] args) {
        new TicTacToe().go();
    }

    public void go() {
        System.out.println("Игра началась!");

        for (int i=0; i<9; i++){
            playerOne = !playerOne;
            field.render(i,playerOne);
            System.out.println("Введите координаты по вертикали и горизонтали");
            field.set(in.nextInt()-1,in.nextInt()-1,playerOne);
            if (field.checkWin() != 0) break;
        }

        System.out.println(gameOver[field.checkWin()]);
    }
}

