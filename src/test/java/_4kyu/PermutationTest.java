package _4kyu;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;
import java.util.*;
import java.util.stream.*;

public class PermutationTest {
    @Test public void example1() {
        assertEquals( Arrays.asList("a"),
                Permutations.singlePermutations("a").stream().sorted().collect(Collectors.toList()) );
    }

    @Test public void example2() {
        assertEquals( Arrays.asList("ab","ba"),
                Permutations.singlePermutations("ab").stream().sorted().collect(Collectors.toList()) );
    }

    @Test public void example3() {
        assertEquals( Arrays.asList("aabb", "abab", "abba", "baab", "baba", "bbaa"),
                Permutations.singlePermutations("aabb").stream().sorted().collect(Collectors.toList()) );
    }


    @Test public void uniqueLetters() {
        List<String> abcd = Arrays.asList("abcd", "abdc", "acbd", "acdb", "adbc", "adcb", "bacd", "badc", "bcad", "bcda", "bdac", "bdca",
                "cabd", "cadb", "cbad", "cbda", "cdab", "cdba", "dabc", "dacb", "dbac", "dbca", "dcab", "dcba");

        assertEquals(abcd, Permutations.singlePermutations("abcd")
                .stream().sorted().collect(Collectors.toList()) );
        assertEquals(abcd, Permutations.singlePermutations("bcad")
                .stream().sorted().collect(Collectors.toList()) );
        assertEquals(abcd, Permutations.singlePermutations("dcba")
                .stream().sorted().collect(Collectors.toList()) );
    }


    @Test public void duplicateLetters() {

        assertEquals( Arrays.asList("aa"),
                Permutations.singlePermutations("aa").stream().sorted().collect(Collectors.toList()) );
        assertEquals( Arrays.asList("aaaab", "aaaba", "aabaa", "abaaa", "baaaa"),
                Permutations.singlePermutations("aaaab").stream().sorted().collect(Collectors.toList()) );
        assertEquals( Arrays.asList("aaaaab", "aaaaba", "aaabaa", "aabaaa", "abaaaa", "baaaaa"),
                Permutations.singlePermutations("aaaaab").stream().sorted().collect(Collectors.toList()) );
    }


    @Test public void randomTests() {

        String BASE = "abcdefghijklmnopqrstuvwxyz";

        for (int r=0 ; r<40 ; r++) {
            String s = IntStream.range(0,rand(1,8))
                    .map( i -> rand(0, BASE.length()) )
                    .mapToObj( i -> BASE.substring(i,i+1) )
                    .collect(Collectors.joining());

            assertEquals( GNAAAAAAAAAA.singlePermutations(s).stream().sorted().collect(Collectors.toList()),
                    Permutations.singlePermutations(s).stream().sorted().collect(Collectors.toList()));
        }


    }

    private Random rnd = new Random();

    private int rand(int x, int y) { return x + rnd.nextInt(y-x); }

    private static class GNAAAAAAAAAA {

        public static List<String> singlePermutations(String s) {
            StringBuilder sb   = new StringBuilder();
            Set<String>   ans  = new HashSet<>();
            Set<Integer>  used = new HashSet<>();
            DFS(s, used, sb, ans);
            return new ArrayList<String>(ans);
        }

        private static void DFS(String s, Set<Integer> used, StringBuilder sb, Set<String> ans) {

            if (sb.length() == s.length()) {
                ans.add(sb.toString());

            } else {
                for (int x=0 ; x < s.length() ; x++) {
                    if (!used.contains(x)) {
                        sb.append(s.charAt(x));
                        used.add(x);
                        DFS(s, used, sb, ans);
                        used.remove(x);
                        sb.deleteCharAt(sb.length()-1);
                    }
                }
            }
        }
    }
}
