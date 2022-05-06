/*
Description
The input is a string str of digits. Cut the string into chunks (a chunk here is a substring of the initial string) of
 size sz (ignore the last chunk if its size is less than sz).

If a chunk represents an integer such as the sum of the cubes of its digits is divisible by 2, reverse that chunk;
otherwise rotate it to the left by one position. Put together these modified chunks and return the result as a string.

If sz is <= 0 or if str is empty return ""
sz is greater (>) than the length of str it is impossible to take a chunk of size sz hence return "".
Examples:
revrot("123456987654", 6) --> "234561876549"
revrot("123456987653", 6) --> "234561356789"
revrot("66443875", 4) --> "44668753"
revrot("66443875", 8) --> "64438756"
revrot("664438769", 8) --> "67834466"
revrot("123456779", 8) --> "23456771"
revrot("", 8) --> ""
revrot("123456779", 0) --> ""
revrot("563000655734469485", 4) --> "0365065073456944"
Example of a string rotated to the left by one position:
s = "123456" gives "234561".
 */

package _6kyu;

public class RevRot {
    public static String revRot(String strng, int sz) {
        if (sz <= 0 || strng.isEmpty() || sz > strng.length()) return "";
        StringBuilder transfWord = new StringBuilder();
        for (int i = 0; i < strng.length() / sz; i++) {
            StringBuilder stringBuilder = new StringBuilder(strng.substring(i * sz, (i + 1) * sz));
            if (isCubes(stringBuilder.toString())) {
                transfWord.append(stringBuilder.reverse());
            } else transfWord.append(stringBuilder.substring(1)).append(stringBuilder.substring(0, 1));

        }
        return transfWord.toString();
    }

    private static boolean isCubes(String chunk) {
        int result = chunk.chars().map(i -> (int) Math.pow(i - 48, 3)).sum();
        return result % 2 == 0;
    }
}
