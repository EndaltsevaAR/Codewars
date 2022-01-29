package _4kyu;

/*
Description:
A format for expressing an ordered list of integers is to use a comma separated list of either

individual integers
or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. The range includes all
integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers. For example "12,13,15-17"
Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

Example:

Solution.rangeExtraction(new int[] {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
# returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20"
 */

import java.util.ArrayList;
import java.util.List;

public class RangeExtraction {

    public static String rangeExtraction(int[] arr) {
        if (arr.length == 0) return "";
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        List<Integer> tempCache = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1 && arr[i + 1] - arr[i] == 1) {
                count++;
                tempCache.add(arr[i]);
            } else {
                switch (count) {
                    case 0 -> stringBuilder.append(arr[i]).append(",");
                    case 1 -> {
                        stringBuilder.append(tempCache.get(0)).append(",");
                        stringBuilder.append(arr[i]).append(",");
                    }
                    default -> stringBuilder.append(tempCache.get(0)).append("-").append(arr[i]).append(",");
                }
                count = 0;
                tempCache = new ArrayList<>();
            }
        }
        return stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).toString();
    }
}
