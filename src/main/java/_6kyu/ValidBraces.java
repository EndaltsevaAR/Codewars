package _6kyu;

/*
Description:
Write a function that takes a string of braces, and determines if the order of the braces is valid. It should return true if the string
is valid, and false if it's invalid.

This Kata is similar to the Valid Parentheses Kata, but introduces new characters: brackets [], and curly braces {}. Thanks
to @arnedag for the idea!

All input strings will be nonempty, and will only consist of parentheses, brackets and curly braces: ()[]{}.

What is considered Valid?
A string of braces is considered valid if all braces are matched with the correct brace.

Examples
"(){}[]"   =>  True
"([{}])"   =>  True
"(}"       =>  False
"[(])"     =>  False
"[({})](]" =>  False
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValidBraces {

    public boolean isValid(String braces) {
        Map<Character, Character> branchesPair = new HashMap<>();
        branchesPair.put('(', ')');
        branchesPair.put('{', '}');
        branchesPair.put('[', ']');

        Map<Character, String> expect = new HashMap<>();
        expect.put(')', "Simple");
        expect.put('}', "Curly");
        expect.put(']', "Square");

        ArrayList<String> expectation = new ArrayList<>();
        char[] letters = braces.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (branchesPair.containsKey(letters[i])) {
                expectation.add(expect.get(branchesPair.get(letters[i])));
            } else if (expect.containsKey((letters[i]))) {
                String s = expect.get(letters[i]);
                if (expectation.size() > 0 && expectation.get(expectation.size() - 1).equals(s)) {
                    expectation.remove(expectation.size() - 1);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return expectation.size() <= 0;
    }
}
