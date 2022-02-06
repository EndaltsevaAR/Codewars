package _4kyu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObservedPin {
    public static void main(String[] args) {
        String observed = "1234567890";
       getPINs(observed);

    }

    public static List<String> getPINs(String observed) {
        System.out.println("");
        List<List<Integer>> variations = new ArrayList<>();

       /* List<List<Integer>> keyBoard = new ArrayList<>();
        keyBoard.add(Stream.of(1,2,3).collect(Collectors.toList()));
        keyBoard.add(Stream.of(4,5,6).collect(Collectors.toList()));
        keyBoard.add(Stream.of(7,8,9).collect(Collectors.toList()));*/

        List<String> numbers = Arrays.stream(observed.split("")).collect(Collectors.toList());

        for (int i = 0; i < numbers.size(); i++) {
            List<Integer> aroundButton = foundAroundButton(Integer.parseInt(numbers.get(i)));
         //   variations = updateVariations(variations);
        }

      //  List<String> answer = textVariations(variations);
return null;
      //  return answer;
    } // getPINs

    private static List<Integer> foundAroundButton(int number) {
        List<Integer> aroundNumbers = new ArrayList<>();
        int y = number / 3;
        int x = number % 3;
        if (number == 0) {
            aroundNumbers.add(8);
        } else {
            if (y - 1  >= 0) {
                aroundNumbers.add(number - 3);
            }
            if (y + 1 <= 2) {
                aroundNumbers.add(number + 3);
            }
            if (x - 1 >= 0) {
                aroundNumbers.add(number - 1);
            }
            if (x + 1 <= 2) {
                aroundNumbers.add(number + 1);
            }
        }
        for (Integer integer :aroundNumbers) {
            System.out.print(integer + " ");
        }
        System.out.println(" ");

        return aroundNumbers;
    }

}
