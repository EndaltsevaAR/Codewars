package _6kyu;

/*
Description:
Your task is to sort a given string. Each word in the string will contain a single number. This number is the position the word should have in the result.

Note: Numbers can be from 1 to 9. So 1 will be the first word (not 0).

If the input string is empty, return an empty string. The words in the input String will only contain valid consecutive numbers.

Examples
"is2 Thi1s T4est 3a"  -->  "Thi1s is2 3a T4est"
"4of Fo1r pe6ople g3ood th5e the2"  -->  "Fo1r the2 g3ood 4of th5e pe6ople"
""  -->  ""
 */

import java.util.Comparator;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

public class YourOrderPlease {
    public static String order(String words) {
        if (words.isEmpty()) {
            return "";
        }
        Stream<String> wordsStream = Stream.of(words.split(" "));
        return wordsStream.sorted(Comparator.comparingInt(YourOrderPlease::digitAtTheString)).collect(Collectors.joining(" "));
    }

    private static int digitAtTheString(String s) {
        return s.chars().boxed().filter(c -> c > 48 && c <= 57).collect(Collectors.toList()).get(0);
    }
}
