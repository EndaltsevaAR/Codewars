package _3kyu;

/*
Description:
Screen Locking Patterns
You might already be familiar with many smartphones that allow you to use a geometric pattern as a security measure. To unlock the device, you need to connect a sequence of dots/points in a grid by swiping your finger without lifting it as you trace the pattern through the screen.

The image below has an example pattern of 7 dots/points: (A -> B -> I -> E -> D -> G -> C).

lock_example.png

For this kata, your job is to implement a function that returns the number of possible patterns starting from a given first point, that have a given length.

More specifically, for a function countPatternsFrom(firstPoint, length), the parameter firstPoint is a single-character string corresponding to the point in the grid (i.e.: 'A') where your patterns start, and the parameter length is an integer indicating the number of points (length) every pattern must have.

For example, countPatternsFrom("C", 2), should return the number of patterns starting from 'C' that have 2 two points. The return value in this case would be 5, because there are 5 possible patterns:

(C -> B), (C -> D), (C -> E), (C -> F) and (C -> H).

Bear in mind that this kata requires returning the number of patterns, not the patterns themselves, so you only need to count them. Also, the name of the function might be different depending on the programming language used, but the idea remains the same.

Rules
In a pattern, the dots/points cannot be repeated: they can only be used once, at most.
In a pattern, any two subsequent dots/points can only be connected with direct straight lines in either of these ways:
Horizontally: like (A -> B) in the example pattern image.
Vertically: like (D -> G) in the example pattern image.
Diagonally: like (I -> E), as well as (B -> I), in the example pattern image.
Passing over a point between them that has already been 'used': like (G -> C) passing over E, in the example pattern
image. This is the trickiest rule. Normally, you wouldn't be able to connect G to C, because E is between them, however
when E has already been used as part the pattern you are tracing, you can connect G to C passing over E, because E is
ignored, as it was already used once.

The sample tests have some examples of the number of combinations for some cases to help you check your code.

Haskell Note: A data type Vertex is provided in place of the single-character strings. See the solution setup code for
more details.

Fun fact:

In case you're wondering out of curiosity, for the Android lock screen, the valid patterns must have between 4 and 9
dots/points. There are 389112 possible valid patterns in total; that is, patterns with a length between 4 and 9
dots/points.
 */

import java.util.ArrayList;
import java.util.List;

public class ScreenLock {
    final static char[][] keyboard = new char[][]{{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}};       //keyboard of smartphone

    public static void main(String[] args) {
        ScreenLock screenLock = new ScreenLock();
        System.out.println(screenLock.calculateCombinations('C', 2));
    }

    public int calculateCombinations(char startPosition, int patternLength) {
        if (patternLength == 0) return 0;     //there is empty list
        List<List<Character>> resultList = new ArrayList<>();    //list of combinations
        List<Character> startList = new ArrayList<>();  //first list (just first letter)
        startList.add(startPosition);
        resultList.add(startList);
        for (int i = 1; i < patternLength; i++) {
            resultList = findNeighbor(resultList); //dynamic programming: result list is overwritten and expand each step
        }
        return resultList.size();
    }

    private List<List<Character>> findNeighbor(List<List<Character>> resultList) {
        List<List<Character>> stepList = new ArrayList<>(); //combinations of this step
        for (List<Character> branch : resultList) { //for each exist combination
            List<Integer> lastCoordinates = findCoordinates(branch.get(branch.size() - 1)); //coordinations of last element current combination
            for (int y = 0; y < keyboard.length; y++) {
                for (int x = 0; x < keyboard[0].length; x++) {
                    if (!branch.contains(keyboard[y][x]) && //if this letter already in current combination, there is mistake
                            !isHaveOpenNeighbors(lastCoordinates.get(0), lastCoordinates.get(1), y, x, branch)) { //we can not go throw not used letters
                        List<Character> temp = new ArrayList<>(branch);
                        temp.add(keyboard[y][x]);
                        stepList.add(temp);
                    }
                }
            }
        }
        return stepList;
    }

    private boolean isHaveOpenNeighbors(int yFirst, int xFirst, int yNewLetter, int xNewLetter, List<Character> branch) {
        List<Integer> yCoordinates = new ArrayList<>();
        List<Integer> xCoordinates = new ArrayList<>();
        boolean isYMove = false;
        boolean isXMove = false;
        //we define definition of possible moving
        if ((Math.abs(yNewLetter - yFirst)) % 2 == 0 && (Math.abs(xNewLetter - xFirst))% 2 != 1 && yNewLetter != yFirst) {  //moving OY
            if ((Math.abs(xNewLetter - xFirst)) % 2 == 0 && xNewLetter != xFirst ) { //with moving OX
                isYMove = true;
                isXMove = true;
            } else { //without moving OX
                isYMove = true;
            }
        } else if ((Math.abs(xNewLetter - xFirst)) % 2 == 0 && yNewLetter == yFirst && xNewLetter != xFirst) { //moving just OX
            isXMove = true;

        }
        // if there is need moving at the OY coordinate
        if (isYMove) {
            for (int i = Math.min(yFirst, yNewLetter) + 1; i < Math.max(yFirst, yNewLetter); i++) {
                yCoordinates.add(i);
            }
        }
        // if there is need moving at the OX coordinate
        if (isXMove) {
            for (int i = Math.min(xFirst, xNewLetter) + 1; i < Math.max(xFirst, xNewLetter); i++) {
                xCoordinates.add(i);
            }
        }

        // we compare every cell that is on the road with the current combination. If we found not used one, we can not use this road
        for (int i = 0; i < Math.max(yCoordinates.size(), xCoordinates.size()); i++) {
            if (isYMove && isXMove) {
                if (!branch.contains(keyboard[yCoordinates.get(i)][xCoordinates.get(i)])) {
                    return true;
                }
            } else if (isYMove) {
                if (!branch.contains(keyboard[yCoordinates.get(i)][xFirst])) {
                    return true;
                }
            } else if (isXMove) {
                if (!branch.contains(keyboard[yFirst][xCoordinates.get(i)])) {
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
