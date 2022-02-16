package _4kyu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.runners.JUnit4;

public class NextBiggerNumberWithTheSameDigitsTest {
    @Test
    public void basicTests() {
        assertEquals(21, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(12));
        assertEquals(531, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(513));
        assertEquals(2071, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(2017));
        assertEquals(441, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(414));
        assertEquals(414, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(144));
        assertEquals(19009, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(10990));
    }

    @Test
    public void biggerTests() {
        assertEquals(123456798, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(123456789));
        assertEquals(1234567908, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(1234567890));
        assertEquals(-1, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(9876543210L));
        assertEquals(-1, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(9999999999L));
        assertEquals(59884848483559L, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(59884848459853L));
        assertEquals(98829009, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(98820990));
    }

    @Test
    public void randomTests() {
        for (int i = 0; i < 140; i++) {
            long n = (long) (Math.random() * (Integer.MAX_VALUE - 100)) + 100;

            assertEquals(Sol(n), NextBiggerNumberWithTheSameDigits.nextBiggerNumber(n));
        }
    }

    private static long Sol(long n) {
        byte[] ds = Long.toString(n).getBytes();
        int nd = ds.length;
        int i = nd - 2;
        while (i >= 0 && ds[i] >= ds[i + 1])
            i--;
        if (i < 0)
            return -1;
        int j = nd - 1;
        while (ds[j] <= ds[i])
            j--;
        swap(ds, i, j);
        for (int k = 1; i + k < nd - k; k++)
            swap(ds, i + k, nd - k);
        return Long.parseLong(new String(ds));
    }

    private static void swap(byte[] xs, int i, int j) {
        byte x = xs[i];
        xs[i] = xs[j];
        xs[j] = x;
    }
}
