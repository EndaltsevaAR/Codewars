package _4kyu;

public class ConwayLife {
    public static void main(String[] args) {
        int[][] start = new int[][]
                {{1, 0, 0, 1},
                        {0, 1, 1, 0},
                        {1, 1, 0, 0}};
        getGeneration(start, 2);

    }

    public static int[][] getGeneration(int[][] cells, int generations) {
        int[][] cellsWithBond = generateCells(cells);
        int[][] tempArray = new int[cellsWithBond.length][cellsWithBond[0].length];
        for (int[] row : cellsWithBond) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }


        for (int gen = 0; gen < generations; gen++) {


            for (int y = 0; y < cellsWithBond.length; y++) {
                for (int x = 0; x < cellsWithBond[0].length; x++) {
                    int sumOfBorders = transformCell(y, x, cellsWithBond) - cellsWithBond[y][x];
                    if (cellsWithBond[y][x] == 0 && sumOfBorders == 3) {
                        tempArray[y][x] = 1;
                    } else if (cellsWithBond[y][x] == 1 && (sumOfBorders < 2 || sumOfBorders > 3)) {
                        tempArray[y][x] = 0;
                    } else {
                        tempArray[y][x] = cellsWithBond[y][x];
                    }
                }
            }
            cellsWithBond = tempArray;
            System.out.println();
            for (int[] row : cellsWithBond) {
                for (int i : row) {
                    System.out.print(i + " ");
                }
                System.out.println("");
            }
        }
        return null;
    }

    private static int transformCell(int y, int x, int[][] cellsWithBond) {
        int sum = 0;
        int yMin = Math.max(y-1, 0);
        int xMin = Math.max(x-1, 0);
        int yMax = Math.min(y+2, cellsWithBond.length);
        int xMax = Math.min(x+2, cellsWithBond[0].length);
        for (int i = yMin; i < yMax; i++) {
            for (int j = xMin; j < xMax; j++) {
                sum += cellsWithBond[i][j];
            }
        }
        return sum;
    }

    private static int[][] generateCells(int[][] cells) {
        int[][] cellsWithBond = new int[cells.length + 2][cells[0].length + 2];
        for (int i = 1; i < cellsWithBond.length - 1; i++) {
            int[] line = new int[cellsWithBond[i].length];
            System.arraycopy(cells[i - 1], 0, line, 1, cells[i - 1].length);
            cellsWithBond[i] = line;
        }
        return cellsWithBond;
    }
}
