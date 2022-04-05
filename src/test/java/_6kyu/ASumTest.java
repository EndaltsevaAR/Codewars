package _6kyu;

import static org.junit.Assert.*;
import java.util.Random;

import org.junit.Test;


public class ASumTest {

    @Test
    public void test1() {
        assertEquals(2022, ASum.findNb(4183059834009L));
    }
    @Test
    public void test2() {
        assertEquals(-1, ASum.findNb(24723578342962L));
    }
    @Test
    public void test3() {
        assertEquals(4824, ASum.findNb(135440716410000L));
    }
    @Test
    public void test4() {
        assertEquals(3568, ASum.findNb(40539911473216L));
    }
    @Test
    public void test5() {
        assertEquals(3218, ASum.findNb(26825883955641L));
    }
    @Test
    public void test6() {
        assertEquals(-1, ASum.findNb(41364076483082L));
    }
    @Test
    public void test7() {
        assertEquals(2485, ASum.findNb(9541025211025L));
    }
    @Test
    public void test8() {
        assertEquals(-1, ASum.findNb(112668204662785L));
    }
    @Test
    public void test9() {
        assertEquals(4788, ASum.findNb(131443152397956L));
    }
    @Test
    public void test10() {
        assertEquals(-1, ASum.findNb(108806345136785L));
    }
    @Test
    public void test11() {
        assertEquals(55100, ASum.findNb(2304422822859502500L));
    }
    @Test
    public void test12() {
        assertEquals(-1, ASum.findNb(2304422822859502501L));
    }

    @Test
    public void test14() {
        assertEquals(-1, ASum.findNb(4L));
    }
    @Test
    public void test15() {
        assertEquals(-1, ASum.findNb(16L));
    }

    public static long solution(long m) {
        long limit = (long)(Math.sqrt(2) * Math.pow(m, 0.25 ) + 1);
        for (long i = 0; i <= limit; i++)
            if (i * i * (i + 1) * (i + 1) / 4 == m) return i;
        return (-1L);
    }

    public static void randTests() {
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            long r = 50000 + rnd.nextInt(5000);
            long k = r * r * (r + 1) * (r + 1) / 4 + rnd.nextInt(2);
            System.out.println("Find n of : " + k);
            //System.out.println(" n " + ASum.findNb(k));
            assertEquals(solution(k), ASum.findNb(k));
        }
    }

    @Test
    public void testA() {
        randTests();
    }
}
