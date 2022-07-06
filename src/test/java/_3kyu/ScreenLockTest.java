package _3kyu;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import java.util.Random;
import java.util.*;
import java.util.stream.Collectors;

public class ScreenLockTest {
    ScreenLock sl = new ScreenLock();

    @Test
    public void bascisInputs (){

        assertEquals(0, sl.calculateCombinations('A',10));
        assertEquals(0, sl.calculateCombinations('A',0));
        assertEquals(0, sl.calculateCombinations('E',14));
        assertEquals(1, sl.calculateCombinations('B',1));
        assertEquals(5, sl.calculateCombinations('C',2));
        assertEquals(8, sl.calculateCombinations('E',2));
    }

    @Test
    public void someInputs (){
        assertEquals(256, sl.calculateCombinations('E',4));
        assertEquals(37, sl.calculateCombinations('D',3));
    }

    @Test
    public void largeInputs (){
        assertEquals(23280, sl.calculateCombinations('E',8));
    }

    @Test
    public void randomTests(){
        Random rnd = new Random();
        MyScreenLock my = new MyScreenLock();
        char[] letters = {'A','B','C','D','E','F','G','H','I','B'};
        for(int i=0; i<10; i++){
            int patternLength = (int)(rnd.nextDouble() * 10.0);
            assertEquals(my.calculateCombinations(letters[patternLength],patternLength),
                    sl.calculateCombinations(letters[patternLength],patternLength));
        }

    }

    private class MyScreenLock {

        private String LETTERS = "ABCDEFGHI";
        private final List<String> notAllowedPatterns = Arrays.asList("AC,AG,AI,BH,CI,CA,CG,DF,FD,GA,GC,GI,HB,IC,IG,IA".split(","));
        private final Map<String, String> map = new HashMap<>();
        public void init(){
            map.put("AC","B");
            map.put("AG","D");
            map.put("AI","E");
            map.put("BH","E");
            map.put("CI","F");
            map.put("CG","E");
            map.put("DF","E");
            map.put("GI","H");
        }
        public int calculateCombinations(char startPosition, int patternLength){
            if(patternLength>LETTERS.length()) return 0; //no combination
            if(patternLength==0) return 0; //zero combinations
            init();
            String chain = "";//startPosition+"";
            for (int i = 0; i < LETTERS.length(); i++) {
                chain += (LETTERS.charAt(i) == startPosition) ? "" : LETTERS.charAt(i);
            }

            ArrayList<String> s = permutate(chain);
            Set<String> hashSet = s.stream().map(str->startPosition+str.substring(0, patternLength-1)).collect(Collectors.toSet());

            int count = 0;
            Iterator it = hashSet.iterator();
            while (it.hasNext()){
                String w = (String)it.next();
                boolean pass = true;
                for (int i = 0; i < notAllowedPatterns.size(); i++) {
                    if(w.contains(notAllowedPatterns.get(i))){
                        if(w.startsWith(notAllowedPatterns.get(i))){
                            pass = false;
                            break;
                        }
                        String character;
                        String pattern = notAllowedPatterns.get(i);
                        if(map.get(pattern) == null){
                            character = map.get(pattern.charAt(1)+""+pattern.charAt(0));
                        }else{
                            character = map.get(pattern);
                        }

                        if(!w.substring(0,w.indexOf(pattern)).contains(character)){
                            pass = false;
                            break;
                        }
                    }
                }
                if(!pass) continue;
                count++;
            }
            return count;
        }

        private ArrayList<String> permutate(String string){
            ArrayList<String> s = permutater(string);
            SortedMap<String, String> map = new TreeMap<>(String::compareTo);
            for (int i = 0; i < s.size(); i++) {
                map.put(s.get(i), "");
            }
            s.clear();
            Iterator it = map.keySet().iterator();
            while(it.hasNext()){
                String key = (String)it.next();
                s.add(key);
            }
            return s;
        }
        private ArrayList<String> permutater(String string){

            ArrayList<String> words = new ArrayList<>();
            if(string.length() == 1){
                words.add(string);
                return words;
            }
            for (int i = 0; i < string.length(); i++) {
                String w = "";
                for (int j = 0; j < string.length(); j++) {
                    if(i==j) continue;
                    w += string.charAt(j);
                }
                ArrayList<String> my = permutater(w);
                for (int j = 0; j < my.size(); j++) {
                    String toAdd = string.charAt(i)+""+my.get(j);
                    words.add(toAdd);
                }
                if(my.get(0).length() == 8) break;
            }
            return words;
        }
    }

}