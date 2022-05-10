package _6kyu;

import java.util.Random;

import static org.junit.Assert.*;

public class DeadFishTest {
    private Random random = new Random();
    private String ops = "iods";

    @org.junit.Test
    public void fixedTests() {
        assertArrayEquals(new int[]{8, 64}, DeadFish.parse("iiisdoso"));
        assertArrayEquals(new int[]{8, 64, 3600}, DeadFish.parse("iiisdosodddddiso"));
    }

    @org.junit.Test
    public void randomTests() {
        String deadFish;
        for (int i = 0; i < 100; i++) {
            deadFish = getRandomFish();
            assertArrayEquals(solution(deadFish), DeadFish.parse(deadFish));
        }
    }

    private String getRandomFish() {
        String deadFish = "";
        for (int i = 0; i < 7; i++) {
            deadFish += ops.charAt(random.nextInt(4));
        }
        return deadFish;
    }

    private int[] solution(String data) {
        int value = 0;
        int pointer = 0;
        int[] result = new int[(int) data.chars().filter(c -> c == 'o').count()];
        for (char c : data.toCharArray()) {
            if (c == 'i') value += 1;
            if (c == 'd') value -= 1;
            if (c == 's') value *= value;
            if (c == 'o') {
                result[pointer] = value;
                pointer++;
            }
        }
        return result;
    }
}
