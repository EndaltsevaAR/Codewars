/*
Description:
Create a RomanNumerals class that can convert a roman numeral to and from an integer value. It should follow the API
demonstrated in the examples below. Multiple roman numeral values will be tested for each helper method.

Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping
any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008
is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.

Input range : 1 <= n < 4000

In this kata 4 should be represented as IV, NOT as IIII (the "watchmaker's four").

Examples
RomanNumerals.toRoman(1000) // should return 'M'
RomanNumerals.fromRoman("M") // should return 1000
 */

package _4kyu;

import java.util.*;

public class RomanNumerals {
    public static void main(String[] args) {
        System.out.println(fromRoman("II"));
    }
    private static Map<Integer, String> transm = new TreeMap<>(Comparator.reverseOrder());

    static {
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
    }

    public static String toRoman(int n) {
        StringBuilder result_string = new StringBuilder();
        for (Map.Entry<Integer, String> pair : transm.entrySet()) {
            int div = n / pair.getKey();
            if (div != 0) {
                result_string.append(String.valueOf(pair.getValue()).repeat(Math.max(0, div)));
            }
            n = n - div * pair.getKey();
        }
        return result_string.toString();
    }

    public static int fromRoman(String romanNumeral) {
        int result = 0;
        List<String> letters = Arrays.asList("M", "D", "C", "L", "X", "V", "I");
        if (romanNumeral.length() == 1) return findInt(romanNumeral);
        for (int i = 1; i < romanNumeral.length(); i++) {
            int first = letters.indexOf(romanNumeral.substring(i - 1, i));
            int sec = letters.indexOf(romanNumeral.substring(i, i + 1));
            if ( first > sec) {
                result += findInt(romanNumeral.substring(i - 1, i++ + 1));
                if (i >= romanNumeral.length()) return result;
            } else {
                result += findInt(romanNumeral.substring(i - 1, i));
            }
        }
        result += findInt(romanNumeral.substring(romanNumeral.length()-1));
        return result;
    }

    private static int findInt(String letter) {
        for (Map.Entry<Integer, String> pair : transm.entrySet()) {
            if (letter.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        return 0;
    }
}
