/*
Description
Consider a sequence u where u is defined as follows:

The number u(0) = 1 is the first one in u.
For each x in u, then y = 2 * x + 1 and z = 3 * x + 1 must be in u too.
There are no other numbers in u.
Ex: u = [1, 3, 4, 7, 9, 10, 13, 15, 19, 21, 22, 27, ...]

1 gives 3 and 4, then 3 gives 7 and 10, 4 gives 9 and 13, then 7 gives 15 and 22 and so on...

Task:
Given parameter n the function dbl_linear (or dblLinear...) returns the element u(n) of the ordered (with <) sequence u
(so, there are no duplicates).

Example:
dbl_linear(10) should return 22

Note:
Focus attention on efficiency
 */

package _4kyu;

import java.util.*;
import java.util.stream.Collectors;

public class DoubleLinear {

    public static int dblLinear(int n) {
        System.out.println(n);
        List<Long> secq = new ArrayList<>();
        secq.add(1L);
        for (int i = 0; i < n*5; i++) {
            secq.add(2 * secq.get(i) + 1);
            secq.add(3 * secq.get(i) + 1);
        }
        long result = secq.stream().distinct().sorted(Comparator.naturalOrder()).limit(n+1).max(Comparator.naturalOrder()).get();
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(dblLinear(10136));
    }
}
