package _3kyu;

import java.util.ArrayList;
import java.util.List;

public class ScreenLock {
    public int calculateCombinations(char startPosition, int patternLength){
        List<List<Character>> resultList = new ArrayList<>();
        for (int i = 0; i < patternLength; i++) {
            resultList = findNeib(resultList);
        }
        return 0;
    }

    private List<List<Character>> findNeib(List<List<Character>> resultList) {
    }
}
