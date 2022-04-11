/*
Description:
The number 89 is the first integer with more than one digit that fulfills the property partially introduced in the
title of this kata. What's the use of saying "Eureka"? Because this sum gives the same number.

In effect: 89 = 8^1 + 9^2

The next number in having this property is 135.

See this property again: 135 = 1^1 + 3^2 + 5^3

We need a function to collect these numbers, that may receive two integers a, b that defines the range [a, b]
(inclusive) and outputs a list of the sorted numbers in the range that fulfills the property described above.

Let's see some cases:

sum_dig_pow(1, 10) == [1, 2, 3, 4, 5, 6, 7, 8, 9]

sum_dig_pow(1, 100) == [1, 2, 3, 4, 5, 6, 7, 8, 9, 89]
If there are no numbers of this kind in the range [a, b] the function should output an empty list.

sum_dig_pow(90, 100) == []
 */

package _6kyu;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class SumDigPower {

    public static List<Long> sumDigPow(long a, long b) {
        return LongStream.range(a, b).filter(l -> filter_func(l) == l).boxed().collect(Collectors.toList());
    }

    private static long filter_func(long number) {
        String word = Long.toString(number);
        return (long) IntStream.range(0, word.length()).mapToDouble(l -> Math.pow(Character.getNumericValue(word.charAt(l)), l + 1)).sum();
    }
}
