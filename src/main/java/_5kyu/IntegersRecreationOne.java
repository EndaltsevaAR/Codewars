package _5kyu;

/*
Description:
1, 246, 2, 123, 3, 82, 6, 41 are the divisors of number 246. Squaring these divisors we get: 1, 60516, 4, 15129, 9, 6724, 36, 1681.
The sum of these squares is 84100 which is 290 * 290.

Task
Find all integers between m and n (m and n integers with 1 <= m <= n) such that the sum of their squared divisors is itself a square.

We will return an array of subarrays or of tuples (in C an array of Pair) or a string. The subarrays (or tuples or Pairs) will have two
elements: first the number the squared divisors of which is a square and then the sum of the squared divisors.

Example:
list_squared(1, 250) --> [[1, 1], [42, 2500], [246, 84100]]
list_squared(42, 250) --> [[42, 2500], [246, 84100]]
The form of the examples may change according to the language, see "Sample Tests".

Note
In Fortran - as in any other language - the returned string is not permitted to contain any redundant trailing whitespace: you can use
dynamically allocated character strings.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IntegersRecreationOne {

    public static String listSquared(long m, long n) {
        Map<Long, Integer> results = new TreeMap<>();
        for (long i = m; i < n; i++) {
            List<Integer> divizors = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divizors.add(j);
                }
            }
            int sum = divizors.stream().map(t -> t * t).reduce(Integer::sum).get();
            if (sum < 0)
                break;

            switch ((int) (sum & 0xF)) {
                case 0:
                case 1:
                case 4:
                case 9:
                    long tst = (long) Math.sqrt(sum);
                    if (tst * tst == sum) {
                        results.put(i, sum);
                        break;
                    }
                default:
                    break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder().append("[");
        for (Map.Entry<Long, Integer> longIntegerEntry : results.entrySet()) {
            stringBuilder.append("[").
                    append(longIntegerEntry.getKey()).append(", ").
                    append(longIntegerEntry.getValue()).append("], ");
        }
        if (stringBuilder.length() >= 5)
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.append("]").toString();
    }
}
