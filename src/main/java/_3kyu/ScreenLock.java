package _3kyu;

import java.util.ArrayList;
import java.util.List;

public class ScreenLock {
    final static char[][] keyboard = new char[][]{{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}};
    public int calculateCombinations(char startPosition, int patternLength){

        List<List<Character>> resultList = new ArrayList<>();
        List<Character> first = new ArrayList<>();
        first.add(startPosition);
        resultList.add(first);
        for (int i = 0; i < patternLength; i++) {
            resultList = findNeighbor(resultList);
        }
        return 0;
    }

    private List<List<Character>> findNeighbor(List<List<Character>> resultList) {
        List<List<Character>> stepList = new ArrayList<>();
        for (List<Character> branch: resultList) {
            List<Integer> lastLetterCoordinates = findCoordinates(branch.get(branch.size() - 1)); //last letter
            
        }

    }

    private static List<Integer> findCoordinates(char startPosition) {
        List<Integer> coordinates = new ArrayList<>();
        for (int y = 0; y < keyboard.length; y++) {
            for (int x = 0; x < keyboard[0].length; x++) {
                if (keyboard[y][x] == startPosition) {
                    coordinates.add(y);
                    coordinates.add(x);
                    return coordinates;
                }
            }
        }
        return coordinates;
    }
}
