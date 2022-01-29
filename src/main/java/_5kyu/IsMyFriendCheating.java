package _5kyu;

/*
Description:
A friend of mine takes the sequence of all numbers from 1 to n (where n > 0).
Within that sequence, he chooses two numbers, a and b.
He says that the product of a and b should be equal to the sum of all numbers in the sequence, excluding a and b.
Given a number n, could you tell me the numbers he excluded from the sequence?
The function takes the parameter: n (n is always strictly greater than 0) and returns an array or a string (depending on the language)
of the form:

[(a, b), ...] or [[a, b], ...] or {{a, b}, ...} or or [{a, b}, ...]
with all (a, b) which are the possible removed numbers in the sequence 1 to n.

[(a, b), ...] or [[a, b], ...] or {{a, b}, ...} or ... will be sorted in increasing order of the "a".

It happens that there are several possible (a, b). The function returns an empty array (or an empty string) if no possible numbers are
found which will prove that my friend has not told the truth! (Go: in this case return nil).

Examples:
removNb(26) should return [(15, 21), (21, 15)]
or
removNb(26) should return { {15, 21}, {21, 15} }
or
removeNb(26) should return [[15, 21], [21, 15]]
or
removNb(26) should return [ {15, 21}, {21, 15} ]
or
removNb(26) should return "15 21, 21 15"
or

in C:
removNb(26) should return  {{15, 21}{21, 15}} tested by way of strings.
Function removNb should return a pointer to an allocated array of Pair pointers, each one also allocated.
Note
See examples of returns for each language in "RUN SAMPLE TESTS"
 */

import java.util.ArrayList;
import java.util.List;

public class IsMyFriendCheating {

    public static List<long[]> removNb(long n) {
        List<long[]> results = new ArrayList<>();
        long sumForFactors = (long) (0.5 * n * (n + 1) + 1);
        for (long i = n; i >= 1; i--) {
            if (sumForFactors % i == 0) {
                long a = sumForFactors / i;
                if (a > n) break;
                long b = sumForFactors / a;
                results.add(new long[]{a - 1, b - 1});
            }
        }
        return results;
    }
}
