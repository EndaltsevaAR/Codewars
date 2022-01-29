package _4kyu;

/*
Description:
Sudoku Background
Sudoku is a game played on a 9x9 grid. The goal of the game is to fill all cells of the grid with digits from 1 to 9, so that each column,
each row, and each of the nine 3x3 sub-grids (also known as blocks) contain all of the digits from 1 to 9.
(More info at: http://en.wikipedia.org/wiki/Sudoku)

Sudoku Solution Validator
Write a function validSolution/ValidateSolution/valid_solution() that accepts a 2D array representing a Sudoku board, and returns true
if it is a valid solution, or false otherwise. The cells of the sudoku board may also contain 0's, which will represent empty cells. Boards containing one or more zeroes are considered to be invalid solutions.

The board is always 9 cells by 9 cells, and every cell only contains integers from 0 to 9.

Examples
validSolution([
  [5, 3, 4, 6, 7, 8, 9, 1, 2],
  [6, 7, 2, 1, 9, 5, 3, 4, 8],
  [1, 9, 8, 3, 4, 2, 5, 6, 7],
  [8, 5, 9, 7, 6, 1, 4, 2, 3],
  [4, 2, 6, 8, 5, 3, 7, 9, 1],
  [7, 1, 3, 9, 2, 4, 8, 5, 6],
  [9, 6, 1, 5, 3, 7, 2, 8, 4],
  [2, 8, 7, 4, 1, 9, 6, 3, 5],
  [3, 4, 5, 2, 8, 6, 1, 7, 9]
]); // => true
validSolution([
  [5, 3, 4, 6, 7, 8, 9, 1, 2],
  [6, 7, 2, 1, 9, 0, 3, 4, 8],
  [1, 0, 0, 3, 4, 2, 5, 6, 0],
  [8, 5, 9, 7, 6, 1, 0, 2, 0],
  [4, 2, 6, 8, 5, 3, 7, 9, 1],
  [7, 1, 3, 9, 2, 4, 8, 5, 6],
  [9, 0, 1, 5, 3, 7, 2, 1, 4],
  [2, 8, 7, 4, 1, 9, 6, 3, 5],
  [3, 0, 0, 4, 8, 1, 1, 7, 9]
]); // => false
 */

import java.util.*;
import java.util.stream.Collectors;

public class SudokuSolutionValidator {

    public static boolean check(int[][] sudoku) {
        for (int[] ints : sudoku) { //rows
            List<Integer> row = Arrays.stream(ints).boxed().collect(Collectors.toList());

            if (row.contains(0) || checkDuplicates(row)) {
                return false;
            }
        }
        List<Integer> column = new ArrayList<>();
        for (int i = 0; i < sudoku[0].length; i++) { //column
            for (int j = 0; j < sudoku.length; j++) {
                if (sudoku[i][j] == 0) {
                    return false;
                }
                column.add(sudoku[i][j]);
            }
            if (checkDuplicates(column)) {
                return false;
            }
            column = new ArrayList<>();
        }

        int columnIter = 0;   //diag
        int rowIter = 0;
        List<Integer> diag = new ArrayList<>();
        while (columnIter * 3 < sudoku.length) {
            while (rowIter * 3 < sudoku[0].length) {
                for (int i = columnIter * 3; i < columnIter * 3 + 3; i++) {
                    for (int j = rowIter * 3; j < rowIter * 3 + 3; j++) {
                        diag.add(sudoku[i][j]);
                    }
                }
                if (checkDuplicates(diag)) {
                    return false;
                }
                diag = new ArrayList<>();
                rowIter++;
            }
            diag = new ArrayList<>();
            rowIter = 0;
            columnIter++;
        }
        return true;
    }

    private static boolean checkDuplicates(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        return set.size() != list.size();
    }
}
