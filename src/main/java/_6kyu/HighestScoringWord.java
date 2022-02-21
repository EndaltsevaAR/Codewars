package _6kyu;

import java.util.*;
import java.util.stream.Collectors;

public class HighestScoringWord {
    public static void main(String[] args) {
        System.out.println(high("b aa"));
    }
    public static String high(String s) {
        System.out.println(s);
        List<String> words = Arrays.stream(s.split(" ")).collect(Collectors.toList());
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            int summ = word.chars().map(letter -> letter - 96).sum();
            result.put(word, summ);
        }
        int max = result.values().stream().max(Comparator.naturalOrder()).get();
        for (String word :words) {
            if (result.get(word) == max) {
                return word;
            }
        }
        return "";
    }
}
