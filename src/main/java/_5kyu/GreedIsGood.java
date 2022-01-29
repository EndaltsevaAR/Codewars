package _5kyu;

/*
Description:
Greed is a dice game played with five six-sided dice. Your mission, should you choose to accept it, is to score a throw according to
these rules. You will always be given an array with five six-sided dice values.

 Three 1's => 1000 points
 Three 6's =>  600 points
 Three 5's =>  500 points
 Three 4's =>  400 points
 Three 3's =>  300 points
 Three 2's =>  200 points
 One   1   =>  100 points
 One   5   =>   50 point
A single die can only be counted once in each roll. For example, a given "5" can only count as part of a triplet (contributing to the
500 points) or as a single 50 points, but not both in the same roll.

Example scoring

 Throw       Score
 ---------   ------------------
 5 1 3 4 1   250:  50 (for the 5) + 2 * 100 (for the 1s)
 1 1 1 3 1   1100: 1000 (for three 1s) + 100 (for the other 1)
 2 4 4 5 4   450:  400 (for three 4s) + 50 (for the 5)
 */

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public class GreedIsGood {

    public static int greedy(int[] dice) {
        int sum = 0;
        Map<Integer, Long> dices = Arrays.stream(dice).boxed().collect(Collectors.groupingBy(k -> k, Collectors.counting()));
        if (dices.get(1) != null && dices.get(1) > 0) {
            if (dices.get(1) >= 3) {
                sum += 1000;
                dices.put(1, dices.get(1) - 3);
            }
            if (dices.get(1) > 0) {
                sum += dices.get(1) * 100;
            }
        }
        if (dices.get(5) != null && dices.get(5) > 0) {
            if (dices.get(5) >= 3) {
                sum += 500;
                dices.put(5, dices.get(5) - 3);
            }
            if (dices.get(5) > 0) {
                sum += dices.get(5) * 50;
            }
        }
        if (dices.get(2) != null && dices.get(2) >= 3) {
            sum += 200;
        }
        if (dices.get(3) != null && dices.get(3) >= 3) {
            sum += 300;
        }
        if (dices.get(4) != null && dices.get(4) >= 3) {
            sum += 400;
        }
        if (dices.get(6) != null && dices.get(6) >= 3) {
            sum += 600;
        }
        return sum;
    }
}
