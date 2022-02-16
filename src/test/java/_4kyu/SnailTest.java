package _4kyu;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Random;
import static java.util.stream.Collectors.joining;

public class SnailTest {
    private int[] tsnail(int[][] array) {
        int[] sorted = new int[array.length * array.length];
        tsnail(array, -1, 0, 1, 0, array.length, 0, sorted);
        return sorted;
    }

    private void tsnail(int[][] array, int x, int y, int dx, int dy, int l, int i, int[] sorted) {
        if (l == 0)
            return;
        for (int j = 0; j < l; j++) {
            x += dx;
            y += dy;
            sorted[i++] = array[y][x];
        }
        tsnail(array, x, y, -dy, dx, dy == 0 ? l - 1 : l, i, sorted);

    } // recursive snail

    public String int2dToString(int[][] a) {
        return "["+Arrays.stream(a).map(row -> Arrays.toString(row)).collect(joining("\n"))+"]";
    }

    public void test(int[][] array, int[] result) {
        String text = int2dToString(array) + " should be sorted to " + Arrays.toString(result);
        System.out.println(text);
        Assert.assertArrayEquals( result, Snail.snail(array));
    }

    @Test
    public void SnailTest1() {
        int[][] array
                = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[] r = {1, 2, 3, 6, 9, 8, 7, 4, 5};
        test(array, r);
    }

    @Test
    public void SnailTest2() {
        int[][] array
                = {{1, 2, 3, 9},
                {4, 5, 6, 4},
                {7, 8, 9, 1},
                {1, 2, 3, 4}};
        int r[] = {1, 2, 3, 9, 4, 1, 4, 3, 2, 1, 7, 4, 5, 6, 9, 8};
        test(array, r);
    }

    @Test
    public void SnailTest2Empty() {
        int a[][]={{}};
        test(a, new int[0]);
    }

    @Test
    public void SnailTestOne() {
        int a[][]={{1}};
        test(a, new int[]{1});
    }


    @Test
    public void SnailRandomTest() {
        System.out.println("Random Tests");
        Random rand = new Random();
        for (int n = 0; n < 100; n++) {
            int l = rand.nextInt(20)+1;
            int[][] array = new int[l][l];
            for (int i = 0; i < l; i++) for (int j = 0; j < l; j++) array[j][i]=1 + rand.nextInt(1000);
            test(array, tsnail(array));
        } // for
    } // Random tests
}
