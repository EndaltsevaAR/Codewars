package _6kyu;

/*
Description:
Task
You will be given an array of numbers. You have to sort the odd numbers in ascending order while leaving the even numbers at their original positions.

Examples
[7, 1]  =>  [1, 7]
[5, 8, 6, 3, 4]  =>  [3, 8, 6, 5, 4]
[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]  =>  [1, 8, 3, 6, 5, 4, 7, 2, 9, 0]
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortTheOdd {
    public static int[] sortArray(int[] array) {
        List<Integer> odds = Arrays.stream(array).boxed().filter(i -> i % 2 == 1).
                sorted().collect(Collectors.toList());

        return Arrays.stream(array).boxed().map(i ->
        {
            if (i % 2 == 1) {
                int d = odds.get(0);
                odds.remove(0);
                return d;
            } else {
                return i;
            }
        }).mapToInt(i -> i).toArray();
    }
}
