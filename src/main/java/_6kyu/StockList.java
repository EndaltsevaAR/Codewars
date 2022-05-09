/*
Description
A bookseller has lots of books classified in 26 categories labeled A, B, ... Z. Each book has a code c of 3, 4, 5 or
more characters. The 1st character of a code is a capital letter which defines the book category.

In the bookseller's stocklist each code c is followed by a space and by a positive integer n (int n >= 0) which
indicates the quantity of books of this code in stock.

For example an extract of a stocklist could be:

L = {"ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"}.
or
L = ["ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"] or ....
You will be given a stocklist (e.g. : L) and a list of categories in capital letters e.g :

M = {"A", "B", "C", "W"}
or
M = ["A", "B", "C", "W"] or ...
and your task is to find all the books of L with codes belonging to each category of M and to sum their quantity
according to each category.

For the lists L and M of example you have to return the string (in Haskell/Clojure/Racket a list of pairs):

(A : 20) - (B : 114) - (C : 50) - (W : 0)
where A, B, C, W are the categories, 20 is the sum of the unique book of category A, 114 the sum corresponding to
"BKWRK" and "BTSQZ", 50 corresponding to "CDXEF" and 0 to category 'W' since there are no code beginning with W.
 */

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
