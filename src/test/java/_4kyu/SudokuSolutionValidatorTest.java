package _4kyu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SudokuSolutionValidatorTest {
    private static final int[][] basicBoard = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}
    };

    private static final int[] complete = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static boolean check(int[][] sudoku) {
        return Stream.of(
                new int []{0, 0}, new int []{3, 1}, new int []{6, 2},
                new int []{1, 3}, new int []{4, 4}, new int []{7, 5},
                new int []{2, 6}, new int []{5, 7}, new int []{8, 8}
        ).allMatch(point -> check(point, sudoku));
    }

    private static boolean check(int[] point, int[][] sudoku) {
        int[] row = new int[9], col = new int[9], box = new int[9];
        int x = point[0] / 3 * 3, y = point[1] / 3 * 3;

        for (int i = 0; i < 9; i++) {
            row[i] = sudoku[i][point[1]];
            col[i] = sudoku[point[0]][i];
            box[i] = sudoku[x + i / 3][y + i % 3];
        }

        Arrays.sort(row);
        Arrays.sort(col);
        Arrays.sort(box);

        return  Arrays.equals(row, complete) &&
                Arrays.equals(col, complete) &&
                Arrays.equals(box, complete);
    }

    @Test
    void exampleTest() {
        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        assertTrue(SudokuSolutionValidator.check(sudoku));

        sudoku[0][0]++;
        sudoku[1][1]++;
        sudoku[0][1]--;
        sudoku[1][0]--;

        assertFalse(SudokuSolutionValidator.check(sudoku));

        sudoku[0][0]--;
        sudoku[1][1]--;
        sudoku[0][1]++;
        sudoku[1][0]++;

        sudoku[4][4] = 0;

        assertFalse(SudokuSolutionValidator.check(sudoku));
    }

    @Test
    void subsquaresTest() {
        int[][] sudoku = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        assertFalse(SudokuSolutionValidator.check(sudoku));
    }

    @Test
    void randomTests() {
        for (int i = 0; i < 10; i++) {
            int[][] sudoku = new int[9][9];
            Random r = new Random();

            for (int x = 0; x < 9; x++) for (int y = 0; y < 9; y++) sudoku[x][y] = r.nextInt(10);

            assertEquals(check(sudoku), SudokuSolutionValidator.check(sudoku));
        }
    }

    @Test
    void moreRandomTests() {
        for (int i = 0; i < 10; i++) {
            int[][] sudoku = shuffledSudoku(true);
            assertEquals(check(sudoku), SudokuSolutionValidator.check(sudoku));
        }
        for (int i = 0; i < 10; i++) {
            int[][] sudoku = shuffledSudoku(false);
            assertEquals(check(sudoku), SudokuSolutionValidator.check(sudoku));
        }
    }

    /*    not entirely random-generated valid sudoku,
     *    but shuffled enough not to accept hard-coded solutions
     */
    private int[][] shuffledSudoku(boolean valid) {
        int[][] sudoku = basicBoard.clone();
        Random r = new Random();

        int shuffles = 10 + r.nextInt(10);
        for (int j = 0; j < shuffles; j++) {
            int x = r.nextInt(9), y = r.nextInt(9), toShuffleX, toShuffleY;

            if (valid) {
                int X = x / 3 * 3, Y = y / 3 * 3;
                toShuffleX = new int[]{X, X + 1, X + 2}[r.nextInt(2)];
                toShuffleY = new int[]{Y, Y + 1, Y + 2}[r.nextInt(2)];
            } else {
                toShuffleX = r.nextInt(9);
                toShuffleY = r.nextInt(9);
            }

            int temp;
            for (int k = 0; k < 9; k++) {
                temp = sudoku[x][k];
                sudoku[x][k] = sudoku[toShuffleX][k];
                sudoku[toShuffleX][k] = temp;
            }
            for (int k = 0; k < 9; k++) {
                temp = sudoku[k][y];
                sudoku[k][y] = sudoku[k][toShuffleY];
                sudoku[k][toShuffleY] = temp;
            }
        }
        return sudoku;
    }
}
