/*
Write a function that, given a string of text (possibly with punctuation and line-breaks), returns an array of the
top-3 most occurring words, in descending order of the number of occurrences.

Assumptions:
A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
Matches should be case-insensitive, and the words in the result should be lowercased.
Ties may be broken arbitrarily.
If a text contains fewer than three unique words, then either the top-2 or top-1 words should be returned, or an empty
array if a text contains no words.
Examples:
top_3_words("In a village of La Mancha, the name of which I have no desire to call to
mind, there lived not long since one of those gentlemen that keep a lance
in the lance-rack, an old buckler, a lean hack, and a greyhound for
coursing. An olla of rather more beef than mutton, a salad on most
nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
on Sundays, made away with three-quarters of his income.")
# => ["a", "of", "on"]

top_3_words("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e")
# => ["e", "ddd", "aa"]

top_3_words("  //wont won't won't")
# => ["won't", "wont"]
 */

package _4kyu;

import java.util.*;
import java.util.stream.Collectors;

public class TopWords {
    public static List<String> top3(String s) {
        String regex = "[-.?!)(,:/_; \\t\\n\\r]+";           //without '
        String[] initWords = s.toLowerCase(Locale.ROOT).split(regex);
        List<String> linesWithoutSpace = new ArrayList<>();
        for (String s1 : initWords) { //delete empty elements and elements just with '
            if (s1.length() != 0 && isWord(s1)) {
                linesWithoutSpace.add(s1);
            }
        }
        Map<String, Long> lettersMapCount = linesWithoutSpace.stream().collect(Collectors.groupingBy(k -> k, Collectors.counting()));

        return lettersMapCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                limit(3).map(Map.Entry::getKey).collect(Collectors.toList());

    }

    private static boolean isWord(String s1) {
        for (int i = 0; i < s1.length(); i++) {
            if (Character.isLetterOrDigit(s1.charAt(i))) return true;
        }
        return false;
    }
}