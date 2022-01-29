package _6kyu;

/*
Description:
Count the number of Duplicates
Write a function that will return the count of distinct case-insensitive alphabetic characters and numeric digits that occur more than once in the input string. The input string can be assumed to contain only alphabets (both uppercase and lowercase) and numeric digits.

Example
"abcde" -> 0 # no characters repeats more than once
"aabbcde" -> 2 # 'a' and 'b'
"aabBcde" -> 2 # 'a' occurs twice and 'b' twice (`b` and `B`)
"indivisibility" -> 1 # 'i' occurs six times
"Indivisibilities" -> 2 # 'i' occurs seven times and 's' occurs twice
"aA11" -> 2 # 'a' and '1'
"ABBA" -> 2 # 'A' and 'B' each occur twice
 */

import java.util.*;

public class CountingDuplicates {
    public static int duplicateCount(String text) {
        int count = 0;
        char[] chars = text.replaceAll("\\W", "").replaceAll("_", "").toLowerCase().toCharArray();

        Map<Character, Integer> map = new HashMap<>();

        for (char c : chars) {
            map.merge(c, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                count++;
            }
        }
        return count;
    }
}
