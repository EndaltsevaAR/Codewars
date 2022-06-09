package _4kyu;

/*
Description:
Given a 2D array and a number of generations, compute n timesteps of Conway's Game of Life.

The rules of the game are:

Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
Any live cell with more than three live neighbours dies, as if by overcrowding.
Any live cell with two or three live neighbours lives on to the next generation.
Any dead cell with exactly three live neighbours becomes a live cell.
Each cell's neighborhood is the 8 cells immediately around it (i.e. Moore Neighborhood). The universe is infinite in both the x and y dimensions and all cells are
initially dead - except for those specified in the arguments. The return value should be a 2d array cropped around all of the living cells. (If there are no living cells,
then return [[]].)

For illustration purposes, 0 and 1 will be represented as ░░ and ▓▓ blocks respectively (PHP, C: plain black and white squares). You can take advantage of the htmlize
function to get a text representation of the universe, e.g.:

System.out.println(LifeDebug.htmlize(cells));
 */

import java.util.Arrays;

public class ConwayLife {

    public static int[][] getGeneration(int[][] cells, int generations) {
        for (int gen = 0; gen < generations; gen++) { //for each generation

            int[][] cellsWithBond = generateDeadBorder(cells);         //add dead borders if it is necessary
            int[][] tempArray = new int[cellsWithBond.length][cellsWithBond[0].length]; //create temp array, because in the generation we can not change origin array's elements

            for (int y = 0; y < cellsWithBond.length; y++) {  //game itself
                for (int x = 0; x < cellsWithBond[0].length; x++) {
                    int sumOfBorders = getSumOfBorderCells(y, x, cellsWithBond) - cellsWithBond[y][x];
                    if (cellsWithBond[y][x] == 0 && sumOfBorders == 3) {
                        tempArray[y][x] = 1;
                    } else if (cellsWithBond[y][x] == 1 && (sumOfBorders < 2 || sumOfBorders > 3)) {
                        tempArray[y][x] = 0;
                    } else {
                        tempArray[y][x] = cellsWithBond[y][x];
                    }
                }
            }

            cells = new int[cellsWithBond.length][cellsWithBond[0].length]; //return data to origin array
            for (int y = 0; y < cellsWithBond.length; y++) {
                System.arraycopy(tempArray[y], 0, cells[y], 0, cells[0].length);
            }
        }
        return cellsWithoutDeadBond(cells);       //after all generations, delete dead borders
    }

    private static int getSumOfBorderCells(int y, int x, int[][] cellsWithBond) {
        int sum = 0;
        int yMin = Math.max(y - 1, 0);
        int xMin = Math.max(x - 1, 0);
        int yMax = Math.min(y + 2, cellsWithBond.length);
        int xMax = Math.min(x + 2, cellsWithBond[0].length);
        for (int i = yMin; i < yMax; i++) {
            for (int j = xMin; j < xMax; j++) {
                sum += cellsWithBond[i][j];
            }
        }
        return sum;
    }

    private static int[][] generateDeadBorder(int[][] cells) {
        int up = 0, down = 0, left = 0, right = 0;
        if (cells.length == 0) {
            up++;
            down++;
            left++;
            right++;
        } else {
            if (isRowLive(cells[0])) up++;
            if (isRowLive(cells[cells.length - 1])) down++;
            if (isColumnLive(cells, 0)) left++;
            if (isColumnLive(cells, cells[0].length - 1)) right++;
        }
        int[][] cellsWithBond = new int[cells.length + up + down][cells[0].length + left + right];
        for (int i = up; i < cellsWithBond.length - down; i++) {
            int[] line = new int[cellsWithBond[i].length];
            for (int j = left; j < line.length - right; j++) {
                line[j] = cells[i - up][j - left];
            }
            cellsWithBond[i] = line;
        }
        return cellsWithBond;
    }

    private static boolean isRowLive(int[] row) {
        return Arrays.stream(row).anyMatch(i -> i == 1);
    }

    private static boolean isColumnLive(int[][] cells, int columnNumber) {
        for (int[] cell : cells) {
            if (cell[columnNumber] == 1) return true;
        }
        return false;
    }

    private static int[][] cellsWithoutDeadBond(int[][] cells) {
        int up = 0, down = 0, left = 0, right = 0;
        for (int[] cell : cells) {
            if (!isRowLive(cell)) up++;
            else break;
        }
        for (int i = cells.length - 1; i >= 0 ; i--) {
            if (!isRowLive(cells[i])) down++;
            else break;
        }
        for (int i = 0; i < cells[0].length; i++) {
            if (!isColumnLive(cells, i)) left++;
            else break;
        }
        for (int i = cells[0].length - 1; i >= 0; i--) {
            if (!isColumnLive(cells, i)) right++;
            else break;
        }
        if (up + down > cells.length) { //if all array is dead
            up = cells.length/2;
            down = cells.length - up;
        }
        if (left + right > cells[0].length) { //if all array is dead
            left = cells[0].length/2;
            right = cells[0].length - left;
        }
        int[][] cellsWithoutDeadBorders = new int[cells.length - up - down][cells[0].length - left - right];
        for (int x = 0; x < cellsWithoutDeadBorders.length; x++) {
            for (int y = 0; y < cellsWithoutDeadBorders[0].length ; y++) {
                cellsWithoutDeadBorders[x][y] = cells[x + up][y + left];
            }
        }
        return cellsWithoutDeadBorders;
    }
}
