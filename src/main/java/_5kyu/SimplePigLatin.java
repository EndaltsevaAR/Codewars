package _5kyu;

/*
Description:
Move the first letter of each word to the end of it, then add "ay" to the end of the word. Leave punctuation marks untouched.

Examples
pigIt('Pig latin is cool'); // igPay atinlay siay oolcay
pigIt('Hello world !');     // elloHay orldway !
 */


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimplePigLatin {
    public static String pigIt(String str) {
        return Stream.of(str.split(" ")).
                map(s -> {
                    if (Character.isLetter(s.charAt(0))) {
                        return s.substring(1) + s.charAt(0) + "ay";
                    } else {
                        return s;
                    }
                }).collect(Collectors.joining(" "));
    }
}