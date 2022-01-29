package _6kyu;

/*
Description:

Complete the method/function so that it converts dash/underscore delimited words into camel casing.
The first word within the output should be capitalized only if the original word was capitalized (known as Upper Camel Case,
also often referred to as Pascal case).

Examples
        "the-stealth-warrior" gets converted to "theStealthWarrior"
        "The_Stealth_Warrior" gets converted to "TheStealthWarrior"
*/

import java.lang.StringBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ConvertStringToCamelCase {

    static String toCamelCase(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        Pattern p = Pattern.compile("[-_]");
        Matcher m = p.matcher(s);
        while (m.find()) {
            int x = m.start();
            if (s.length() > x + 1) {
                if (x != 0) {
                    stringBuilder.setCharAt(x + 1, Character.toUpperCase(stringBuilder.charAt(x + 1)));

                }
            }
        }
        return stringBuilder.toString().replaceAll("[-_]", "");
    }
}