package _4kyu;

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
