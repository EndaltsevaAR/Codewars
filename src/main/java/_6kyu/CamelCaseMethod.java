/*
Description
Write simple .camelCase method (camel_case function in PHP, CamelCase in C# or camelCase in Java) for strings.
All words must have their first letter capitalized without spaces.

For instance:

camelCase("hello case"); // => "HelloCase"
camelCase("camel case word"); // => "CamelCaseWord"
 */

package _6kyu;

public class CamelCaseMethod {
    public static String camelCase(String str) {
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null && words[i].length() > 0) {
                int k = 0;
                do {
                    if (Character.isLetter(words[i].charAt(k))) {
                        words[i] = words[i].substring(0, k) + Character.toUpperCase(words[i].charAt(k)) + words[i].substring(k+1);
                        break;
                    } else {
                        k++;
                    }
                } while(true);
            }
        }
        return String.join("", words);
    }

    public static void main(String[] args) {
        System.out.println(camelCase("Test class"));
    }
}
