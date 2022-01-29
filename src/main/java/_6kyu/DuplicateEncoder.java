package _6kyu;

/*
Description:
The goal of this exercise is to convert a string to a new string where each character in the new string is "(" if that character
appears only once in the original string, or ")" if that character appears more than once in the original string. Ignore capitalization when determining if a character is a duplicate.

Examples
"din"      =>  "((("
"recede"   =>  "()()()"
"Success"  =>  ")())())"
"(( @"     =>  "))(("
Notes
Assertion messages may be unclear about what they display in some languages. If you read "...It Should encode XXX", the "XXX" is the
expected result, not the input!
 */

import java.util.HashMap;
import java.util.Map;

public class DuplicateEncoder {
    static String encode(String word) {
        String wordWithoutCase = word.toLowerCase();
        Map<Character, Integer> letters = new HashMap<>();
        for (char c : wordWithoutCase.toLowerCase().toCharArray()) {
            letters.put(c, letters.containsKey(c) ? 1 : 0);
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < wordWithoutCase.length(); i++) {
            if (letters.get(wordWithoutCase.charAt(i)) == 1) {
                stringBuilder.append(')');
            } else {
                stringBuilder.append('(');
            }
        }
        return stringBuilder.toString();
    }
}
