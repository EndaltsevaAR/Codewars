package _6kyu;

public class SimpleEncryption {
    public static String encrypt(final String text, final int n) {
        String word = text;
        for (int t = 0; t < n; t++) {
            StringBuilder encyptString = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if (i % 2 == 1) {
                    encyptString.append(word.charAt(i));
                }
            }
            for (int i = 0; i < word.length(); i++) {
                if (i % 2 == 0) {
                    encyptString.append(word.charAt(i));
                }
            }
            word = encyptString.toString();
        }
        return word;
    }

    public static String decrypt(final String encryptedText, final int n) {
        String word = encryptedText;
        for (int t = 0; t < n; t++) {
            StringBuilder decryptString = new StringBuilder();
            String firstPart = word.substring(word.length() / 2);
            String secondPart = word.substring(0, word.length() / 2);
            for (int i = 0; i < Math.max(firstPart.length(), secondPart.length()); i++) {
                if (i < firstPart.length()) decryptString.append(firstPart.charAt(i));
                if (i < secondPart.length()) decryptString.append(secondPart.charAt(i));
            }
            word = decryptString.toString();
        }
        return word;
    }
}
