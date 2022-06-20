package _3kyu;

import java.util.ArrayList;
import java.util.List;

public class RailFenceCipher {
    public static void main(String[] args) {
        System.out.println(encode("",4 ));
    }

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
                if (currentLine == n-1) { //transition point to change direction from down to moving up
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
            for (Character character: characters) {
                stringBuilder.append(character);
            }
        }
        return stringBuilder.toString();
    }

    static String decode(String s, int n) {
        // Your code here
        return null;
    }
}
