package _3kyu;

/*
DESCRIPTION:
Your task, is to create a NxN spiral with a given size.

For example, spiral with size 5 should look like this:

00000
....0
000.0
0...0
00000
and with the size 10:

0000000000
.........0
00000000.0
0......0.0
0.0000.0.0
0.0..0.0.0
0.0....0.0
0.000000.0
0........0
0000000000
Return value should contain array of arrays, of 0 and 1, with the first row being composed of 1s. For example for given
size 5 result should be:

[[1,1,1,1,1],[0,0,0,0,1],[1,1,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Because of the edge-cases for tiny spirals, the size will be at least 5.

General rule-of-a-thumb is, that the snake made with '1' cannot touch to itself.
 */

public class Spiralizor {
    public static int[][] spiral;
    public static int[] startPoint;       //first element is y coordinate, second: x

    public static int[][] spiralize(int size) {
        spiral = new int[size][size];
        startPoint = new int[]{1, 0};  //like pointer

        for (int y = 0; y < size; y++) {  //transform array to full of 0
            for (int x = 0; x < size; x++) {
                spiral[y][x] = 1;
            }
        }
        if (size < 5) return spiral;  //too small size

        while (true) {  //create spiral from left to right, from top to bottom
            if (!moveTop() || !moveRight() || !moveDown() || !moveLeft()) break;
        }
        return spiral;
    }

    private static boolean moveTop() {
        boolean isChange = false;
        for (int x = startPoint[1]; x < spiral.length - 1; x++) {
           if (spiral[startPoint[0]][x + 1] != 0) {
                spiral[startPoint[0]][x] = 0;
                startPoint[1] = x;
                isChange = true;
            } else {
                return isChange;
            }
        }
        return isChange;
    }

    private static boolean moveRight() {
        boolean isChange = false;
        for (int y = startPoint[0]; y < spiral.length - 1; y++) {
            if (spiral[y + 1][startPoint[1]] != 0) {
                spiral[y][startPoint[1]] = 0;
                startPoint[0] = y;
                isChange = true;
            } else {
                return isChange;
            }
        }
        return isChange;
    }

    private static boolean moveDown() {
        boolean isChange = false;
        for (int x = startPoint[1]; x > 0; x--) {
            if (spiral[startPoint[0]][x - 1] != 0) {
                spiral[startPoint[0]][x] = 0;
                startPoint[1] = x;
                isChange = true;
            } else {
                return isChange;
            }
        }
        return isChange;
    }


    private static boolean moveLeft() {
        boolean isChange = false;
        for (int y = startPoint[0]; y > 0; y--) {
            if (spiral[y - 1][startPoint[1]] != 0) {
                spiral[y][startPoint[1]] = 0;
                startPoint[0] = y;
                isChange = true;
            } else {
                return isChange;
            }
        }
        return isChange;
    }
}

