package _6kyu;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class RomanNumeralsEncoder {
    public String solution(int n) {
        StringBuilder result_string = new StringBuilder();
        Map<Integer, String> transm = new TreeMap<>(Comparator.reverseOrder());
        transm.put(1000, "M");
        transm.put(900, "CM");
        transm.put(500, "D");
        transm.put(400, "CD");
        transm.put(100, "C");
        transm.put(90, "XC");
        transm.put(50, "L");
        transm.put(40, "XL");
        transm.put(10, "X");
        transm.put(9, "IX");
        transm.put(5, "V");
        transm.put(4, "IV");
        transm.put(1, "I");
        for (Map.Entry<Integer, String> pair: transm.entrySet()) {
            int div = n / pair.getKey();
            if (div != 0) {
                result_string.append(String.valueOf(pair.getValue()).repeat(Math.max(0, div)));
            }
            n = n - div* pair.getKey();
        }
        return result_string.toString();
    }
}
