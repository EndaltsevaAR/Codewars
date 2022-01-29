package _6kyu;

/*
Description:
There is an array with some numbers. All numbers are equal except for one. Try to find it!

Kata.findUniq(new double[]{ 1, 1, 1, 2, 1, 1 }); // => 2
Kata.findUniq(new double[]{ 0, 0, 0.55, 0, 0 }); // => 0.55
It’s guaranteed that array contains at least 3 numbers.

The tests contain some very huge arrays, so think about performance.

This is the first kata in series:

Find the unique number (this kata)
Find the unique string
Find The Unique
 */


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FindTheUniqueNumber {
    public static double findUniq(double[] arr) {
        Map<Object, Long> map = Arrays.stream(arr).boxed().
                collect(Collectors.groupingBy(k -> k, Collectors.counting()));
        for (Map.Entry<Object, Long> pair : map.entrySet()) {
            if (pair.getValue() == 1) {
                return (double) pair.getKey();
            }
        }
        return -1;
    }
}
