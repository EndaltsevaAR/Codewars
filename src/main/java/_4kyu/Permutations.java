package _4kyu;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public static List<String> singlePermutation(String s) {
        List<String> perm = new ArrayList<>();
        if (s == null) {
            return null;
        } else if (s.length() == 0) {
            perm.add("");
            return perm;
        }
        char initial = s.charAt(0);
        String rem = s.substring(1);
        List<String> words = singlePermutation(rem);
        for (String strNew : words) {
            for (int i = 0; i <= strNew.length(); i++) {
                perm.add(insertChar(strNew, initial, i));
            }
        }
        return perm.stream().distinct().collect(Collectors.toList());
    }

    public static String insertChar(String str, char c, int j) {
        String begin = str.substring(0, j);
        String end = str.substring(j);
        return begin + c + end;
    }
}
