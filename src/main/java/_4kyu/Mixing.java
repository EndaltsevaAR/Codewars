/*
Description
Given two strings s1 and s2, we want to visualize how different the two strings are. We will only take into account
the lowercase letters (a to z). First let us count the frequency of each lowercase letters in s1 and s2.

s1 = "A aaaa bb c"

s2 = "& aaa bbb c d"

s1 has 4 'a', 2 'b', 1 'c'

s2 has 3 'a', 3 'b', 1 'c', 1 'd'

So the maximum for 'a' in s1 and s2 is 4 from s1; the maximum for 'b' is 3 from s2. In the following we will not
consider letters when the maximum of their occurrences is less than or equal to 1.

We can resume the differences between s1 and s2 in the following string: "1:aaaa/2:bbb" where 1 in 1:aaaa stands for
string s1 and aaaa because the maximum for a is 4. In the same manner 2:bbb stands for string s2 and bbb because
the maximum for b is 3.

The task is to produce a string in which each lowercase letters of s1 or s2 appears as many times as its maximum if
this maximum is strictly greater than 1; these letters will be prefixed by the number of the string where they appear
with their maximum value and :. If the maximum is in s1 as well as in s2 the prefix is =:.

In the result, substrings (a substring is for example 2:nnnnn or 1:hhh; it contains the prefix) will be in decreasing
order of their length and when they have the same length sorted in ascending lexicographic order (letters and digits -
more precisely sorted by codepoint); the different groups will be separated by '/'. See examples and "Example Tests".

Hopefully other examples can make this clearer.

s1 = "my&friend&Paul has heavy hats! &"
s2 = "my friend John has many many friends &"
mix(s1, s2) --> "2:nnnnn/1:aaaa/1:hhh/2:mmm/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"

s1 = "mmmmm m nnnnn y&friend&Paul has heavy hats! &"
s2 = "my frie n d Joh n has ma n y ma n y frie n ds n&"
mix(s1, s2) --> "1:mmmmmm/=:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"

s1="Are the kids at home? aaaaa fffff"
s2="Yes they are here! aaaaa fffff"
mix(s1, s2) --> "=:aaaaaa/2:eeeee/=:fffff/1:tt/2:rr/=:hh"
Note for Swift, R, PowerShell
The prefix =: is replaced by E:

s1 = "mmmmm m nnnnn y&friend&Paul has heavy hats! &"
s2 = "my frie n d Joh n has ma n y ma n y frie n ds n&"
mix(s1, s2) --> "1:mmmmmm/E:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/E:ee/E:ss"
 */
package _4kyu;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mixing {

    public static String mix(String s1, String s2) {
        List<String> firstStringList = getListCount(s1);              //list of counts with identical letters for 1 string
        List<String> secondStringList = getListCount(s2);             //...for 2nd string
        int maxFirstListLength = findMaxListLength(firstStringList);  //max count of letters from string
        int maxSecondListLength = findMaxListLength(secondStringList);
        List<String> usedLetters = new ArrayList<>();                 //each letters will be present at the result string just once
        StringBuilder resultWord = new StringBuilder();               //result

        for (int i = Math.max(maxFirstListLength, maxSecondListLength); i > 1; i--) {  //started from the longest letter's lines
            for (String firstLetter : firstStringList) {                               //checking 1st string
                if (firstLetter.length() == i && !secondStringList.contains(firstLetter) && !usedLetters.contains(firstLetter.substring(0, 1))) {
                    addToUsedLetters(firstLetter, "1", usedLetters, resultWord);
                }
            }
            List<String> eqLetters = new ArrayList<>();
            for (String secondLetter : secondStringList) {                             //checking 2nd string
                if (secondLetter.length() == i && !usedLetters.contains(secondLetter.substring(0, 1))) {
                    if (firstStringList.contains(secondLetter)) {
                        eqLetters.add(secondLetter);
                    } else {
                        addToUsedLetters(secondLetter, "2", usedLetters, resultWord);
                    }
                }
            }
            for (String eqLetter : eqLetters) {                                         //if counts are equal
                addToUsedLetters(eqLetter, "=", usedLetters, resultWord);
            }
        }
        if (resultWord.length() > 0) resultWord.setLength(resultWord.length() - 1);     //delete last element(/)
        return resultWord.toString();
    }

    private static List<String> getListCount(String text) {
        Map<Character, Long> countMap = text.codePoints().filter(Character::isLetter).filter(Character::isLowerCase).                         //filter lowercase letters
                mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).                           //create listLetters which counting letters
                entrySet().stream().filter(pair -> pair.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));    //filter letter which counts bigger than 1

        List<String> listLetters = new ArrayList<>();
        for (Map.Entry<Character, Long> letterCountPair : countMap.entrySet()) {
            StringBuilder letter = new StringBuilder();
            for (int i = 0; i < letterCountPair.getValue(); i++) {
                letter.append(letterCountPair.getKey());
            }
            listLetters.add(letter.toString());
        }
        Collections.sort(listLetters);
        return listLetters;
    }

    private static void addToUsedLetters(String letters, String sign, List<String> usedLetters, StringBuilder resultWord) {
        usedLetters.add(letters.substring(0, 1));
        resultWord.append(String.format("%s:%s/", sign, letters));
    }

    private static int findMaxListLength(List<String> stringList) {
        if (stringList != null && !stringList.isEmpty()) {
            return stringList.stream().map(String::length).max(Comparator.naturalOrder()).get();
        } else {
            return 0;
        }
    }
}
