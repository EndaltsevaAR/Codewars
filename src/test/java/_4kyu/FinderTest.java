package _4kyu;

import java.util.*;
import java.util.stream.*;
import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FinderTest {

    @Test public void fixedTests() {

        String a = ".W.\n"+
                ".W.\n"+
                "...",

                b = ".W.\n"+
                        ".W.\n"+
                        "W..",

                c = "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        "......",

                d = "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        ".....W\n"+
                        "....W.";

        assertEquals(true,  Finder.pathFinder(a));
        assertEquals(false, Finder.pathFinder(b));
        assertEquals(true,  Finder.pathFinder(c));
        assertEquals(false, Finder.pathFinder(d));
    }


    @Test public void moreTests() {

        String a =
                ".W...\n"+
                        ".W...\n"+
                        ".W.W.\n"+
                        "...W.\n"+
                        "...W.";
        assertEquals(true, Finder.pathFinder(a));

        a = ".W...\n"+
                ".W...\n"+
                ".W.W.\n"+
                "...WW\n"+
                "...W.";
        assertEquals(false, Finder.pathFinder(a));

        a = "..W\n"+
                ".W.\n"+
                "W..";
        assertEquals(false, Finder.pathFinder(a));

        a = ".WWWW\n"+
                ".W...\n"+
                ".W.W.\n"+
                ".W.W.\n"+
                "...W.";
        assertEquals(true, Finder.pathFinder(a));

        a = ".W...\n"+
                "W....\n"+
                ".....\n"+
                ".....\n"+
                ".....";
        assertEquals(false, Finder.pathFinder(a));

        a = ".W\n"+
                "W.";
        assertEquals(false, Finder.pathFinder(a));

        a = ".";
        assertEquals(true, Finder.pathFinder(a));
    }




    private static boolean DEBUG = false;

    private Random rnd = new Random();

    private int rand(int n)        { return rnd.nextInt(n); }
    private int rand(int n, int m) { return n + rnd.nextInt(m-n+1); }

    @Test
    public void randomTests() {

        int count = 0,
                TIMES = 20,
                RNG   = 100;

        for (int nTimes=0 ; nTimes < TIMES ; nTimes++) {
            for (int n=1 ; n < RNG+1 ; n++) {

                String maze = generateMaze(n);

                boolean expected = KikicestKiTrouveLaSolution.pathFinder(maze);
                assertEquals(expected, Finder.pathFinder(maze));

                count += expected ? 1:0;
            }
        }

        if (DEBUG) System.out.println(""+count+"/"+(RNG*TIMES));
    }


    private String generateMaze(int n) {

        int size   = n*n,
                nWalls = rand(size/4, size/3);

        Set<Integer> posWalls = new HashSet<>();
        while (posWalls.size() < nWalls) posWalls.add(rand(size));

        final StringBuilder[] sbArr = IntStream.range(0,n)
                .mapToObj( x -> new StringBuilder( IntStream.range(0,n)
                        .mapToObj( y -> "." )
                        .collect(Collectors.joining()) ) )
                .toArray(StringBuilder[]::new);

        posWalls.forEach( w -> { int x = w/n, y = w%n;
            sbArr[x].setCharAt(y,'W'); } );

        sbArr[n-1].setCharAt(n-1, '.');        // first and last tile always free
        sbArr[0].setCharAt(0, '.');

        return Arrays.stream(sbArr)
                .map( sb -> sb.toString() )
                .collect(Collectors.joining("\n"));
    }



    private static final class KikicestKiTrouveLaSolution {

        final private static List<Point> MOVES = Arrays.asList(new Point(1,0), new Point(0,1), new Point(0,-1), new Point(-1,0));


        private static boolean pathFinder(String maze) {

            int S = (int) Math.sqrt(maze.length()) - 1;
            if (S == 0) return true;

            final Set<Point> bag  = new HashSet<>();
            int x = -1;
            for (String line: maze.split("\n")) { x++;
                for (int y=0 ; y < line.length() ; y++)
                    if (line.charAt(y)=='.') bag.add(new Point(x,y));
            }
            bag.remove(new Point(0,0));

            final Point     end    = new Point(S,S);
            final boolean[] hasEnd = {false};
            Set<Point>      look   = new HashSet<>(Arrays.asList(new Point(0,0)));

            while (!look.isEmpty()) {
                if (hasEnd[0]) return true;
                look = look.stream()
                        .flatMap( p -> MOVES.stream().map( d -> new Point(p.x+d.x, p.y+d.y) ))
                        .distinct()
                        .filter(  p -> { if (p.equals(end)) hasEnd[0] = true;
                            if (bag.contains(p)) {
                                bag.remove(p);
                                return true;
                            } else return false; })
                        .collect(Collectors.toSet());
            }
            return false;
        }
    }
}