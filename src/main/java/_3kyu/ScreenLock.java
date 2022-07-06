package _3kyu;

import java.util.ArrayList;
import java.util.List;

public class ScreenLock {
    final static char[][] keyboard = new char[][]{{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}};

    public static void main(String[] args) {
        ScreenLock screenLock = new ScreenLock();
        System.out.println(screenLock.calculateCombinations('C', 2));
    }

    public int calculateCombinations(char startPosition, int patternLength) {
        if (patternLength == 0) return 0;
        List<String> resultList = new ArrayList<>();
        resultList.add(Character.toString(startPosition));
        for (int i = 1; i < patternLength; i++) {
            resultList = findNeighbor(resultList);
        }
        for (String s :resultList) {
            System.out.print(s + " ");
        }

        return resultList.size();
    }

    private List<String> findNeighbor(List<String> resultList) {
        List<String> stepList = new ArrayList<>();  //поменять на стрингбилдеры
        for (String branch : resultList) {
            List<Integer> lastCoordinates = findCoordinates(branch.charAt(branch.length() - 1));
            for (int y = 0; y < keyboard.length; y++) {
                for (int x = 0; x < keyboard[0].length; x++) {
                    if (!branch.contains(Character.toString(keyboard[y][x])) &&
                            !isHaveOpenNeighbors(lastCoordinates.get(0), lastCoordinates.get(1), y, x, branch)) {
                        stepList.add(branch + keyboard[y][x]);
                    }
                }
            }
        }
        return stepList;
    }

    private boolean isHaveOpenNeighbors(int yFirst, int xFirst, int yNewLetter, int xNewLetter, String branch) {
        List<Integer> yCoordinates = new ArrayList<>();
        List<Integer> xCoordinates = new ArrayList<>();
        boolean isYMove = false;
        boolean isXMove = false;
        if ((Math.abs(yNewLetter - yFirst)) % 2 == 0 && (Math.abs(xNewLetter - xFirst))% 2 != 1 && yNewLetter != yFirst) {
            if ((Math.abs(xNewLetter - xFirst)) % 2 == 0 && xNewLetter != xFirst ) {
                isYMove = true;
                isXMove = true;
            } else {
                isYMove = true;
            }
        } else if ((Math.abs(xNewLetter - xFirst)) % 2 == 0 && yNewLetter == yFirst && xNewLetter != xFirst) {
            isXMove = true;

        }

        if (isYMove) {
            for (int i = Math.min(yFirst, yNewLetter) + 1; i < Math.max(yFirst, yNewLetter); i++) {
                yCoordinates.add(i);
            }
        }
        if (isXMove) {
            for (int i = Math.min(xFirst, xNewLetter) + 1; i < Math.max(xFirst, xNewLetter); i++) {
                xCoordinates.add(i);
            }
        }


        for (int i = 0; i < Math.max(yCoordinates.size(), xCoordinates.size()); i++) {
            if (isYMove && isXMove) {
                if (!branch.contains(Character.toString(keyboard[yCoordinates.get(i)][xCoordinates.get(i)]))) {
                    return true;
                }
            } else if (isYMove) {
                if (!branch.contains(Character.toString(keyboard[yCoordinates.get(i)][xFirst]))) {
                    return true;
                }
            } else if (isXMove) {
                if (!branch.contains(Character.toString(keyboard[yFirst][xCoordinates.get(i)]))) {
                    return true;
                }
            }
        }
        return false;
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
