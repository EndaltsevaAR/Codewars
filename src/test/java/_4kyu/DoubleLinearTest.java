package _4kyu;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;


public class DoubleLinearTest {

    private static void testing(int actual, int expected) {
        assertEquals(expected, actual);
    }
    private static int randInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    private static int dblLinearSol (int n) {
        Deque<Integer>  deque2 = new ArrayDeque<Integer> ((int)n /2);
        Deque<Integer>  deque3 = new ArrayDeque<Integer> ((int)n /2);
        int cnt = 0, h = 1;
        while (true) {
            if (cnt >= n) return h;
            deque2.addLast(2 * h + 1);
            deque3.addLast(3 * h + 1);
            h = Math.min(deque2.peekFirst(), deque3.peekFirst());
            if (h == deque2.peekFirst())
                deque2.removeFirst();
            if (h == deque3.peekFirst())
                deque3.removeFirst();
            cnt++;
        }
    }
    @Test
    public void test() {
        System.out.println("Fixed Tests dblLinear");
        testing(DoubleLinear.dblLinear(10), 22);
        testing(DoubleLinear.dblLinear(20), 57);
        testing(DoubleLinear.dblLinear(30), 91);
        testing(DoubleLinear.dblLinear(50), 175);
        testing(DoubleLinear.dblLinear(100), 447);
        testing(DoubleLinear.dblLinear(500), 3355);
        testing(DoubleLinear.dblLinear(1000), 8488);
        testing(DoubleLinear.dblLinear(2000), 19773);
        testing(DoubleLinear.dblLinear(6000), 80914);
        testing(DoubleLinear.dblLinear(60000), 1511311);
    }
    @Test
    public void test1() {
        System.out.println("100 Random Tests");
        for (int i = 0; i < 100; i++) {
            int n = randInt(200, 15000);
            int r = dblLinearSol(n);
            //System.out.println("n " + n + " --> " + r);
            //System.out.println("****");
            testing(DoubleLinear.dblLinear(n), r);
        }
    }
}
