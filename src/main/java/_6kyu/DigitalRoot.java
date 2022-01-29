package _6kyu;

/*
Description:
Digital root is the recursive sum of all the digits in a number.

Given n, take the sum of the digits of n. If that value has more than one digit, continue reducing in this way until a single-digit number is produced. The input will be a non-negative integer.

Examples
    16  -->  1 + 6 = 7
   942  -->  9 + 4 + 2 = 15  -->  1 + 5 = 6
132189  -->  1 + 3 + 2 + 1 + 8 + 9 = 24  -->  2 + 4 = 6
493193  -->  4 + 9 + 3 + 1 + 9 + 3 = 29  -->  2 + 9 = 11  -->  1 + 1 = 2
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DigitalRoot {
    public static int digital_root(int n) {
        while (n > 9) {
            List<String> stringList = Arrays.stream(String.valueOf(n).split("")).collect(Collectors.toList());
            List<Integer> integers = new ArrayList<>();
            for (String s : stringList) {
                integers.add(Integer.valueOf(s));
            }
            n = integers.stream().reduce(0, Integer::sum);
        }
        return n;
    }
}
