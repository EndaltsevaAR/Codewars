package _7kyu;

/*
Description:

Return the number (count) of vowels in the given string.
We will consider a, e, i, o, u as vowels for this Kata (but not y).
The input string will only consist of lower case letters and/or spaces.
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VowelCount {

    public static int getCount(String str) {
        int vowelsCount = 0;
        Pattern p = Pattern.compile("[aeuio]");
        Matcher matcher = p.matcher(str);
        while (matcher.find()) {
            vowelsCount++;
        }
        return vowelsCount;
    }
}
