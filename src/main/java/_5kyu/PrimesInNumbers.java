package _5kyu;

/*
Description:
Given a positive number n > 1 find the prime factor decomposition of n. The result will be a string with the following form :

 "(p1**n1)(p2**n2)...(pk**nk)"
with the p(i) in increasing order and n(i) empty if n(i) is 1.

Example: n = 86240 should return "(2**5)(5)(7**2)(11)"
 */

import java.util.*;

public class PrimesInNumbers {

    public static String factors(int n) {
        List<Integer> factors = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                factors.add(i);
                n = n / i;
                i = 1;
            }
        }
        factors.add(n);
        Collections.sort(factors);
        Map<Integer, Integer> mapFactors = new TreeMap<>();

        for (Integer factor : factors) {
            if (mapFactors.containsKey(factor)) {
                mapFactors.put(factor, mapFactors.get(factor) + 1);
            } else {
                mapFactors.put(factor, 1);
            }
        }

        for (Map.Entry<Integer, Integer> pair : mapFactors.entrySet()) {
            stringBuilder.append("(").append(pair.getKey());
            if (pair.getValue() > 1) {
                stringBuilder.append("**").append(pair.getValue());
            }
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }
}
