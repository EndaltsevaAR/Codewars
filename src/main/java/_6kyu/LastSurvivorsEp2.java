package _6kyu;

/*
Description:
Substitute two equal letters by the next letter of the alphabet (two letters convert to one):

"aa" => "b", "bb" => "c", .. "zz" => "a".
The equal letters do not have to be adjacent.
Repeat this operation until there are no possible substitutions left.
Return a string.

Example:

let str = "zzzab"
    str = "azab"
    str = "bzb"
    str = "cz"
return "cz"
Notes
The order of letters in the result is not important.
The letters "zz" transform into "a".
There will only be lowercase letters.
 */

import java.util.HashMap;
import java.util.Map;

public class LastSurvivorsEp2 {

    public static String lastSurvivors(String str) {
        if (str.length() < 2) {
            return str;
        }
        boolean isActive;
        String text = str;

        do {
            isActive = false;
            Map<Character, Integer> letters = new HashMap<>();
            for (int i = 0; i < text.length(); i++) {
                if (!letters.containsKey(text.charAt(i))) {
                    letters.put(text.charAt(i), i);
                } else {
                    isActive = true;
                    StringBuilder stringBuilder = new StringBuilder(text);
                    stringBuilder.deleteCharAt(i);
                    if (text.charAt(i) == 'z') {
                        stringBuilder.setCharAt(letters.get(text.charAt(i)), 'a');
                    } else {
                        stringBuilder.setCharAt(letters.get(text.charAt(i)), (char) (text.charAt(i) + 1));
                    }
                    text = stringBuilder.toString();
                    break;
                }
            }
        } while (isActive);
        return text;
    }
}