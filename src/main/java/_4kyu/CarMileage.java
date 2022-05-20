package _4kyu;

import java.util.Arrays;
import java.util.List;

public class CarMileage {

    public static int isInteresting(int number, int[] awesomePhrases) {
        if (number < 100 || number >= 1000000) return 0;    //if illegal argument
        for (Integer phrase : awesomePhrases) {             //phrases checking
            if (number == phrase) {
                return 2;
            } else if (phrase - number < 3) {
                return 1;
            }
        }

        if (isAllZeroes(number) || isTheSame(number)) { //Any digit followed by all zeros: 100, 90000
            return 2;
        } else if (isAllZeroes(number + 1) || isAllZeroes(number + 2) ||
                isTheSame(number + 1) || isTheSame(number + 2)) {
            return 1;
        }
        return 0;
    }

    private static boolean isAllZeroes(int number) {
        String numbStr = String.valueOf(number);
        for (int i = 1; i < numbStr.length(); i++) {
            if (numbStr.charAt(i) != '0') return false;
        }
        return numbStr.charAt(0) != '0';
    }

    private static boolean isTheSame(int number) {
        return Arrays.stream(String.valueOf(number).split("")).distinct().count() == 1;
    }

    private static boolean isIncrement(int number) {
        List<Character> numbers = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
        String numbStr = String.valueOf(number);
        for (int i = 1; i < numbStr.length(); i++) {

        }
        return false;
    }

}
