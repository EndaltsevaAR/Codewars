package _4kyu;

/* Description:

 */

import java.util.Arrays;
import java.util.Comparator;

public class Interval {

    public static void main(String[] args) {
        System.out.println(sumIntervals(new int[][]{{-253, 5739}, {-5999, -717}, {-4416, 5449}, {-612, 7999}, {-9137, -4055}, {-3961, -2744}, {-4118, -4049}, {2854, 8956}, {-913, 369}, {2417, 2685}, {-7968, -6866}, {6516, 8507}, {-5964, 2174}}));
    }

    public static int sumIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;       //if interval is not init
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));   //sorting all rows of intervals by first column
        int sumOfLength = 0;
        int[] start = intervals[0];
        if (intervals.length == 1) return start[1] - start[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > start[1]) {                //if there is gap between rows
                sumOfLength += start[1] - start[0];
                start = intervals[i];
            } else {                                                  //find min and max from both rows
                int[] tempArray = Arrays.copyOf(start, start.length + intervals[i].length);
                System.arraycopy(intervals[i], 0, tempArray, start.length, intervals[i].length);
                Arrays.sort(tempArray);
                start = new int[]{tempArray[0], tempArray[tempArray.length-1]};
            }
        }

        return sumOfLength + start[1] - start[0];
    }
}
