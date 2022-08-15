package _4kyu;

/*
Description:
Lyrics...
Pyramids are amazing! Both in architectural and mathematical sense. If you have a computer, you can mess with pyramids
even if you are not in Egypt at the time. For example, let's consider the following problem. Imagine that you have a
pyramid built of numbers, like this one here:

   /3/
  \7\ 4
 2 \4\ 6
8 5 \9\ 3
Here comes the task...
Let's say that the 'slide down' is the maximum sum of consecutive numbers from the top to the bottom of the pyramid.
As you can see, the longest 'slide down' is 3 + 7 + 4 + 9 = 23

Your task is to write a function that takes a pyramid representation as argument and returns its' largest 'slide down'.
For example:

* With the input `[[3], [7, 4], [2, 4, 6], [8, 5, 9, 3]]`
* Your function should return `23`.
By the way...
My tests include some extraordinarily high pyramids so as you can guess, brute-force method is a bad idea unless you
have a few centuries to waste. You must come up with something more clever than that.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LongestSlideDown {

    public static int longestSlideDown(int[][] pyramid) {
        /*
        The algorithm is based on dynamic programming. The program works line by line. The algorithm finds all possible
        sums at a given stage and saves only the largest of them
         */
        List<List<Integer>> sumList = new ArrayList<>(); //list for sums. Structure and size will be identical as pyramid
        sumList.add(Arrays.stream(pyramid[0]).boxed().toList()); //first line of pyramid

        for (int y = 1; y < pyramid.length; y++) { //algorithm start from second line of pyramid
            List<Integer> downLine = new ArrayList<>();
            for (int x = 0; x < sumList.get(y - 1).size(); x++) {
                int left = sumList.get(y - 1).get(x) + pyramid[y][x];  //left branch
                if (downLine.size() == 0) {  //if there is start of line program just save sum
                    downLine.add(left);
                } else if (downLine.get(x) < left) { //if cell is not empty, we compare sums and save biggest
                    downLine.set(x, left);
                }
                downLine.add(sumList.get(y - 1).get(x) + pyramid[y][x + 1]); //right branch
            }
            sumList.add(downLine);
        }
        return sumList.get(sumList.size() - 1).stream().max(Comparator.naturalOrder()).get(); //max sum from last line
    }
}
