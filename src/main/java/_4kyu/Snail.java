package _4kyu;

/*
Description:
Snail Sort
Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.

array = [[1,2,3],
         [4,5,6],
         [7,8,9]]
snail(array) #=> [1,2,3,6,9,8,7,4,5]
For better understanding, please follow the numbers of the next array consecutively:

array = [[1,2,3],
         [8,9,4],
         [7,6,5]]
snail(array) #=> [1,2,3,4,5,6,7,8,9]
This image will illustrate things more clearly:


NOTE: The idea is not sort the elements from the lowest value to the highest; the idea is to traverse the 2-d array in a clockwise
snailshell pattern.

NOTE 2: The 0x0 (empty matrix) is represented as en empty array inside an array [[]].

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Snail {

    public static int[] snail(int[][] array) {
        List<Integer> result = new ArrayList<>();
        int size = array.length * array[0].length;
        while (result.size() < size) {
            result.addAll(Arrays.stream(array[0]).boxed().collect(Collectors.toList()));
            int[][] temp = new int[array[0].length][array.length - 1];
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[0].length; j++) {
                    temp[i][j] = array[j + 1][array[0].length - 1 - i];
                }
            }
            array = temp;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
