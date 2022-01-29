package _5kyu;

/*
Description:
Common denominators

You will have a list of rationals in the form

{ {numer_1, denom_1} , ... {numer_n, denom_n} }
or
[ [numer_1, denom_1] , ... [numer_n, denom_n] ]
or
[ (numer_1, denom_1) , ... (numer_n, denom_n) ]
where all numbers are positive ints. You have to produce a result in the form:

(N_1, D) ... (N_n, D)
or
[ [N_1, D] ... [N_n, D] ]
or
[ (N_1', D) , ... (N_n, D) ]
or
{{N_1, D} ... {N_n, D}}
or
"(N_1, D) ... (N_n, D)"
depending on the language (See Example tests) in which D is as small as possible and

N_1/D == numer_1/denom_1 ... N_n/D == numer_n,/denom_n.
Example:
convertFracs [(1, 2), (1, 3), (1, 4)] `shouldBe` [(6, 12), (4, 12), (3, 12)]
Note:
Due to the fact that the first translations were written long ago - more than 6 years - these first translations have only irreducible
fractions.

Newer translations have some reducible fractions. To be on the safe side it is better to do a bit more work by simplifying fractions even
if they don't have to be.

Note for Bash:
input is a string, e.g "2,4,2,6,2,8" output is then "6 12 4 12 3 12"
 */

import java.util.*;

public class CommonDenominators {

    public static String convertFrac(long[][] lst) {
        lst = getArrayWithSimpleDenominator(lst);

        long nok = findNok(lst);

        if (nok != 0) {
            for (long[] sample : lst) {
                sample[0] = sample[0] * (nok / sample[1]);
                sample[1] = nok;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (long[] longs : lst) {
            stringBuilder.append("(").append(longs[0]).append(",").append(longs[1]).append(")");
        }
        return stringBuilder.toString();
    }

    private static long findNok(long[][] lst) {
        List<Long> totalFactors = new ArrayList<>();
        for (long[] longs : lst) {
            List<Long> bFactors = findFactors(longs[1]);
            if (bFactors.isEmpty()) {
                continue;
            }
            List<Long> totalCopy = new ArrayList<>(totalFactors);
            if (totalCopy.isEmpty()) {
                totalFactors.addAll(bFactors);
                continue;
            }
            for (int i = 0; i < bFactors.size(); i++) {
                if (totalCopy.isEmpty()) {
                    totalFactors.add(bFactors.get(i));
                    totalCopy.add(bFactors.get(i));
                    break;
                }
                for (int j = 0; j < totalCopy.size(); j++) {
                    if (bFactors.get(i).equals(totalCopy.get(j))) {
                        totalCopy.remove(bFactors.get(i));
                        break;
                    }
                    if (j == (totalCopy.size() - 1) && !totalCopy.contains(bFactors.get(i))) {
                        totalFactors.add(bFactors.get(i));
                        totalCopy.add(bFactors.get(i));
                    }
                }
            }
        }
        for (Long aLong : totalFactors) {
            System.out.print(aLong + " ");
        }

        return totalFactors.stream().reduce(1L, (acc, x) -> acc * x);
    }


    private static List<Long> findFactors(long l) {
        List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= l; i++) {
            if (l % i == 0) {
                factors.add(i);
                l = l / i;
                i = 1;
            }
        }
        return factors;
    }

    private static long[][] getArrayWithSimpleDenominator(long[][] lst) {
        for (long[] entryLong : lst) {
            if (entryLong[0] == 1 || entryLong[0] == 0 || entryLong[1] == 1 || entryLong[1] == 0) {
                continue;
            }
            List<Long> aFactors = findFactors(entryLong[0]);
            List<Long> bFactors = findFactors(entryLong[1]);
            List<Long> inters = new ArrayList<>();
            for (int i = 0; i < aFactors.size(); i++) {
                for (int j = 0; j < bFactors.size(); j++) {
                    if (aFactors.get(i).equals(bFactors.get(j))) {
                        inters.add(aFactors.get(i));
                        bFactors.remove(j);
                        break;
                    }
                }
            }
            long max = inters.stream().reduce(1L, (acc, x) -> acc * x);
            entryLong[0] = entryLong[0] / max;
            entryLong[1] = entryLong[1] / max;
        }
        return lst;
    }
}
