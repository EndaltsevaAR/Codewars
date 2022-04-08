package _6kyu;

import org.junit.Test;
        import static org.junit.Assert.assertEquals;
        import org.junit.runners.JUnit4;

        import java.util.Arrays;
        import java.lang.*;
        import java.util.Random;

public class TheSupermarketQueueTest {
    @Test
    public void testNormalCondition() {
        assertEquals(9, TheSupermarketQueue.solveSuperMarketQueue(new int[] { 2, 2, 3, 3, 4, 4 }, 2));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, TheSupermarketQueue.solveSuperMarketQueue(new int[] {}, 1));
    }

    @Test
    public void testBigN() {
        assertEquals(5, TheSupermarketQueue.solveSuperMarketQueue(new int[] { 1, 2, 3, 4, 5 }, 100));
    }

    @Test
    public void testSingleCustomer() {
        assertEquals(2, TheSupermarketQueue.solveSuperMarketQueue(new int[] { 2 }, 5));
    }

    @Test
    public void testSingleCustomerSingleTill() {
        assertEquals(5, TheSupermarketQueue.solveSuperMarketQueue(new int[] { 5 }, 1));
    }

    @Test
    public void testSingleTillManyCustomers() {
        assertEquals(15, TheSupermarketQueue.solveSuperMarketQueue(new int[] { 1, 2, 3, 4, 5 }, 1));
    }

    @Test
    public void testLongCustomerArray() {
        assertEquals(113, TheSupermarketQueue.solveSuperMarketQueue(new int[] {29, 18, 6, 23, 25, 29, 24, 17, 10, 8, 8, 22, 20, 16, 13, 17, 7, 21, 7, 11, 18, 26, 25, 1, 18, 29, 16, 26, 7, 11, 13, 20, 12, 6, 23, 3, 10, 9, 8, 5, 6, 18, 19, 26, 5, 15, 4, 15, 1, 4 }, 7));
    }

    @Test
    public void testRandomValues() {
        final int max_tills = 6;
        Random rand = new Random();
        for (int l = 0; l < 2; l++) {
            int n = rand.nextInt(max_tills) + 1;
            int[] list = generateRandomArray();
            for (int j = 0; j < 3; j++) {
                n = ((n + j) % max_tills) + 1;
                System.out.println("Testing n: " + n + ", array: " + stringifiedArray(list));
                assertEquals(MySolution.solveSuperMarketQueue(list, n), TheSupermarketQueue.solveSuperMarketQueue(list, n));
            }
        }
    }

    public int[] generateRandomArray() {
        final int max_elements = 25;
        final int max_single_value = 7;
        Random rand = new Random();
        int size = rand.nextInt(max_elements) + 1;
        int[] list = new int[size];
        for (int i = 0; i < size; i++) {
            list[i] = rand.nextInt(max_single_value) + 1;
        }
        return list;
    }

    public String stringifiedArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("" + arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static class MySolution {
        // Because screw streams, that's why!
        public static int getArrayIndex(int[] arr, int value) {
            int k = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == value) {
                    k = i;
                    break;
                }
            }
            return k;
        }

        public static int solveSuperMarketQueue(int[] customers, int n) {
            // I want to be explicit about it
            if (customers.length == 0) {
                return 0;
            }
            int i = n;
            // I want to use primitive int, problems?
            int ssize = Math.min(customers.length, n);
            int[] sample = new int[ssize];
            for (int j = 0; j < ssize; j++) {
                sample[j] = customers[j];
            }
            while (i < customers.length) {
                int mmin = Arrays.stream(sample).min().getAsInt();
                int idx = getArrayIndex(sample, mmin);
                sample[idx] += customers[i];
                i++;
            }
            int mmax = Arrays.stream(sample).max().getAsInt();
            return mmax;
        }
    }

}