package _6kyu;

import java.util.HashMap;
import java.util.Map;

public class StockList {
    public static void main(String[] args) {
        String[] art = new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String[] cd = new String[]{"A", "B"};
        System.out.println(stockSummary(art, cd));
    }

    public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter) {
        if (lstOf1stLetter.length == 0 || lstOfArt.length == 0) return "";
        Map<String, Integer> letterMap = new HashMap<>();
        for (String book : lstOfArt) {
            String letter = book.substring(0, 1);
            int numb = Integer.parseInt(book.split(" ")[1]);
            if (letterMap.containsKey(letter)) {
                letterMap.put(letter, letterMap.get(letter) + numb);
            } else {
                letterMap.put(letter, numb);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String firstLetter : lstOf1stLetter) {
            int sumObBook = letterMap.getOrDefault(firstLetter, 0);
            stringBuilder.append(String.format("(%s : %d) - ", firstLetter, sumObBook));
        }
        stringBuilder.setLength(stringBuilder.length() - 3);
        return stringBuilder.substring(0);
    }
}
