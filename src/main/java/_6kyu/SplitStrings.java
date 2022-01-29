package _6kyu;

/*
Description:
Complete the solution so that it splits the string into pairs of two characters. If the string contains an odd number of characters
then it should replace the missing second character of the final pair with an underscore ('_').

Examples:

StringSplit.solution("abc") // should return {"ab", "c_"}
StringSplit.solution("abcdef") // should return {"ab", "cd", "ef"}
 */

public class SplitStrings {
    public static String[] solution(String s) {
        if (s.length() == 0) {
            return new String[0];
        }
        if (s.length()%2 == 1) {
            s += "_";
        }
        return s.split("(?<=\\G.{2})");
    }
}
