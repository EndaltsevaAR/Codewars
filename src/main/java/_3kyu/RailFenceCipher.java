package _3kyu;

import java.util.ArrayList;
import java.util.List;

public class RailFenceCipher {
    public static void main(String[] args) {
        decode("WECRLTEERDSOEEFEAOCAIVDEN", 5);
    }

    static String encode(String s, int n) {
        System.out.println("encode " + n);
        System.out.println(s);
        System.out.println();
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
        System.out.println("decode " + n);
        System.out.println(s);
        System.out.println();
        if (s == null || n < 2 || s.length() == 0) {     //if decoded string is empty or n is too small
            return s;
        }
        int numberOfCircle = (s.length() - 1) / (2 * n - 2); //number of full circles
        int tail = (s.length() - 1) % numberOfCircle;   //size of tail after last full circle
        int lastElement = 0;
        int firstElement = 0;

        List<String> railStringList = new ArrayList<>();

        for (int lineNumber = 0; lineNumber < n; lineNumber++) { //split encoded string to lines
            if (lineNumber != 0) { //define start to substring
                firstElement = lastElement + 1;
            }
            if (lineNumber != 0 && lineNumber != n - 1) { //first and last line will have size of number at circles, others - twice bigger
                lastElement += numberOfCircle * 2;
            } else {
                lastElement += numberOfCircle;
            }

            if (tail > lineNumber + 1) {    //because count of lines started from 0, but tails: from 1
                if (tail > n && lineNumber >= n - 1 - (tail - n)) {  //used size of tail to concrete size of each line
                    lastElement += 2;
                } else {
                    lastElement++;
                }
            }
            railStringList.add(s.substring(firstElement, lastElement + 1)); //last element do not include at the substring function using


        }
        char[] decodedString = new char[s.length()];

        for (int i = 0; i < railStringList.size(); i++) {

            for (int j = 0; j < railStringList.get(i).length(); j++) {
                if (i == 0 || i == railStringList.size() - 1) {
                    decodedString[i + j * (2 * n - 2)] = railStringList.get(i).charAt(j);
                } else {
                    if (j % 2 == 0) {
                        decodedString[i + (2 * n - 2) * j / 2] = railStringList.get(i).charAt(j);
                    } else {
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
