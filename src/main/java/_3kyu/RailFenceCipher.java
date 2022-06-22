package _3kyu;

/* Description:
Create two functions to encode and then decode a string using the Rail Fence Cipher. This cipher is used to encode a
string by placing each character successively in a diagonal along a set of "rails". First start off moving diagonally
and down. When you reach the bottom, reverse direction and move diagonally and up until you reach the top rail.
Continue until you reach the end of the string. Each "rail" is then read left to right to derive the encoded string.

For example, the string "WEAREDISCOVEREDFLEEATONCE" could be represented in a three rail system as follows:

W       E       C       R       L       T       E
  E   R   D   S   O   E   E   F   E   A   O   C
    A       I       V       D       E       N
The encoded string would be:

WECRLTEERDSOEEFEAOCAIVDEN
Write a function/method that takes 2 arguments, a string and the number of rails, and returns the ENCODED string.

Write a second function/method that takes 2 arguments, an encoded string and the number of rails, and returns the
DECODED string.

For both encoding and decoding, assume number of rails >= 2 and that passing an empty string will return an empty string.

Note that the example above excludes the punctuation and spaces just for simplicity. There are, however, tests that
include punctuation. Don't filter out punctuation as they are a part of the string.
 */

import java.util.ArrayList;
import java.util.List;

public class RailFenceCipher {
    static String encode(String s, int n) {
        if (s == null || n < 2 || s.length() == 0) {     //if encoded string is empty or n is too small
            return s;
        }

        List<List<Character>> railLines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            railLines.add(new ArrayList<>());
        }
        railLines.get(0).add(s.charAt(0)); //first element of first line

        boolean isDown = true;
        int currentLine = 1;

        for (int i = 1; i < s.length(); i++) {  //for each character from string except for the first element
            railLines.get(currentLine).add(s.charAt(i));
            if (isDown) { //if direction of coding is down until last line (from first line (not inclusive) to last line (inclusive)
                if (currentLine == n - 1) { //transition point to change direction from down to moving up
                    isDown = false;
                    currentLine--;
                } else {
                    currentLine++;
                }
            } else {     //if direction of coding is up until first line (from last line (not inclusive) to first line (inclusive))
                if (currentLine == 0) { //transition point to change direction from up to moving down
                    isDown = true;
                    currentLine++;
                } else {
                    currentLine--;
                }
            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        for (List<Character> characters : railLines) {
            for (Character character : characters) {
                stringBuilder.append(character);
            }
        }
        return stringBuilder.toString();
    }

    static String decode(String s, int n) {
        if (s == null || n < 2 || s.length() == 0) {     //if decoded string is empty or n is too small
            return s;
        }
        int numberOfCircle = (s.length()) / (2 * n - 2); //number of full circles
        int tail = (s.length()) % (2 * n - 2);   //size of tail after last full circle
        int lastElement = 0;
        int firstElement = 0;

        List<String> railStringList = new ArrayList<>();

        for (int lineNumber = 0; lineNumber < n; lineNumber++) { //split encoded string to n lines
            if (lineNumber != 0) { //define start to substring (except first line, it is already 0)
                firstElement = lastElement;
            }
            if (lineNumber != 0 && lineNumber != n - 1) { //first and last line will have size of number at circles, others - twice bigger
                lastElement += numberOfCircle * 2;
            } else {
                lastElement += numberOfCircle;
            }

            //size adjustment depending has this line tail (or 2) or not
            if (tail >= lineNumber + 1) {    //because count of lines started from 0, but tails: from 1  //if this condition is true, there are yet one tail
                if (tail > n && lineNumber >= n - 1 - (tail - n) && lineNumber != n - 1) {  //used size of tail to concrete size of each line //if tail less than n, tails just leftward \\at the last line there is no tail at all
                    lastElement += 2;
                } else {
                    lastElement++;
                }
            }
            railStringList.add(s.substring(firstElement, lastElement));
        }
        char[] decodedString = new char[s.length()];

        for (int i = 0; i < railStringList.size(); i++) { //decoding part

            for (int j = 0; j < railStringList.get(i).length(); j++) {
                if (i == 0 || i == railStringList.size() - 1) { //first and last lines involved just once at the circle
                    decodedString[i + j * (2 * n - 2)] = railStringList.get(i).charAt(j); //2n - 2 size of the circle
                } else {
                    if (j % 2 == 0) {  //for left part of the circle
                        decodedString[i + (2 * n - 2) * j / 2] = railStringList.get(i).charAt(j);
                    } else { //for right part of the circle
                        decodedString[(2 * n - 2) * (j / 2 + 1) - i] = railStringList.get(i).charAt(j);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (Character character : decodedString) {
            result.append(character);
        }
        return result.toString();
    }
}
