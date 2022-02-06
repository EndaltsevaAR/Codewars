package _4kyu;

/* Description:
Alright, detective, one of our colleagues successfully observed our target person, Robby the robber. We followed him to a secret warehouse,
where we assume to find all the stolen stuff. The door to this warehouse is secured by an electronic combination lock. Unfortunately our spy
isn't sure about the PIN he saw, when Robby entered it.

The keypad has the following layout:

┌───┬───┬───┐
│ 1 │ 2 │ 3 │
├───┼───┼───┤
│ 4 │ 5 │ 6 │
├───┼───┼───┤
│ 7 │ 8 │ 9 │
└───┼───┼───┘
    │ 0 │
    └───┘
He noted the PIN 1357, but he also said, it is possible that each of the digits he saw could actually be another adjacent digit (horizontally
or vertically, but not diagonally). E.g. instead of the 1 it could also be the 2 or 4. And instead of the 5 it could also be the 2, 4, 6 or 8.

He also mentioned, he knows this kind of locks. You can enter an unlimited amount of wrong PINs, they never finally lock the system or sound the alarm.
That's why we can try out all possible (*) variations.

* possible in sense of: the observed PIN itself and all variations considering the adjacent digits

Can you help us to find all those variations? It would be nice to have a function, that returns an array (or a list in Java/Kotlin and C#) of all
variations for an observed PIN with a length of 1 to 8 digits. We could name the function getPINs (get_pins in python, GetPINs in C#).
But please note that all PINs, the observed one and also the results, must be strings, because of potentially leading '0's. We already prepared
some test cases for you.

Detective, we are counting on you!
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObservedPin {
    public static void main(String[] args) {
        String observed = "11";  //test
        for (String s : getPINs(observed)) {
            System.out.println(s);
        }
    }

    public static List<String> getPINs(String observed) { // getPINs
        List<List<Integer>> variations = new ArrayList<>();
        List<String> numbers = Arrays.stream(observed.split("")).collect(Collectors.toList());
        for (String number : numbers) {
            List<Integer> aroundButton = foundAroundButton(Integer.parseInt(number));
            variations = updateVariations(variations, aroundButton); // kata is solved by dynamic programming
        }
        return textVariations(variations);
    }

    private static List<Integer> foundAroundButton(int number) {
        List<Integer> aroundNumbers = new ArrayList<>();
        if (number > 9) return null;   //number can not be bigger than 9
        aroundNumbers.add(number);
        if (number == 0) {             //around 0 button
            aroundNumbers.add(8);
        } else {
            if (number - 3 > 0) aroundNumbers.add(number - 3);            //upper button
            if (number + 3 < 10) aroundNumbers.add(number + 3);           //lower button
            if ((number - 1) % 3 != 0) aroundNumbers.add(number - 1);     //left button
            if (number % 3 != 0) aroundNumbers.add(number + 1);           //right button
        }
        if (number == 8) {
            aroundNumbers.add(0);
        }
        return aroundNumbers;
    }

    private static List<List<Integer>> updateVariations(List<List<Integer>> variations, List<Integer> aroundButton) {
        List<List<Integer>> result = new ArrayList<>();
        if (variations.isEmpty()) {
            variations.add(new ArrayList<>());
        }
        for (List<Integer> line : variations) {
            for (Integer aroundButtonNumber : aroundButton) {
                List<Integer> temp = new ArrayList<>(line);
                temp.add(aroundButtonNumber);
                result.add(temp);
            }
        }
        return result;
    }

    private static List<String> textVariations(List<List<Integer>> variations) {
        List<String> result = new ArrayList<>();
        for (List<Integer> numbers : variations) {
            result.add(numbers.stream().map(String::valueOf).collect(Collectors.joining()));
        }
        return result;
    }
}
