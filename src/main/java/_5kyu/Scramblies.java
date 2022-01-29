package _5kyu;

/*
Description:
Complete the function scramble(str1, str2) that returns true if a portion of str1 characters can be rearranged to match str2, otherwise
returns false.

Notes:

Only lower case letters will be used (a-z). No punctuation or digits will be included.
Performance needs to be considered
Input strings s1 and s2 are null terminated.
Examples
scramble('rkqodlw', 'world') ==> True
scramble('cedewaraaossoqqyt', 'codewars') ==> True
scramble('katas', 'steak') ==> False
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scramblies {
    public static boolean scramble(String str1, String str2) {
        List<String> str1Letters = new ArrayList<>(Arrays.asList(str1.split("")));
        List<String> str2Letters = new ArrayList<>(Arrays.asList(str2.split("")));
        for (String str2Letter : str2Letters) {
            if (str1Letters.contains(str2Letter)) {
                str1Letters.remove(str2Letter);
            } else {
                return false;
            }
        }
        return true;
    }
}
