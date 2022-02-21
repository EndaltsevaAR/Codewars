package _7kyu;

/* Description:
In this little assignment you are given a string of space separated numbers, and have to return the highest and lowest number.

Examples
highAndLow("1 2 3 4 5")  // return "5 1"
highAndLow("1 2 -3 4 5") // return "5 -3"
highAndLow("1 9 3 4 -5") // return "9 -5"
Notes
All numbers are valid Int32, no need to validate them.
There will always be at least one number in the input string.
Output string must be two numbers separated by a single space, and highest number is first.
 */

import java.util.Arrays;
import java.util.Comparator;

public class HighestAndLowest {
    public static String highAndLow(String numbers) {
        int max = Arrays.stream(numbers.split(" ")).map(Integer::parseInt).max(Comparator.naturalOrder()).get();
        int min = Arrays.stream(numbers.split(" ")).map(Integer::parseInt).min(Comparator.naturalOrder()).get();
        return String.format("%d %d", max, min);
    }
}