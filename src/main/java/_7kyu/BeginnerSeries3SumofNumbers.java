package _7kyu;


/*
Description:
Given two integers a and b, which can be positive or negative, find the sum of all the integers between and
including them and return it. If the two numbers are equal return a or b.

Note: a and b are not ordered!

Examples (a, b) --> output (explanation)
(1, 0) --> 1 (1 + 0 = 1)
(1, 2) --> 3 (1 + 2 = 3)
(0, 1) --> 1 (0 + 1 = 1)
(1, 1) --> 1 (1 since both are same)
(-1, 0) --> -1 (-1 + 0 = -1)
(-1, 2) --> 2 (-1 + 0 + 1 + 2 = 2)
*/


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeginnerSeries3SumofNumbers {
    public int GetSum(int a, int b) {
        int sum = 0;
        List<Integer> integers = Stream.of(a, b).sorted().collect(Collectors.toList());
        for (int i = integers.get(0); i <= integers.get(1); i++) {
            sum += i;
        }
        return sum;
    }
}
