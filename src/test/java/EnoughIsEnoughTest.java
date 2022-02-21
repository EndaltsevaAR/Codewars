import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import _6kyu.EnoughIsEnough;
import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class EnoughIsEnoughTest {
    @Test
    public void deleteNth() throws Exception {
        assertArrayEquals(
                new int[] { 20, 37, 21 },
                EnoughIsEnough.deleteNth( new int[] { 20, 37, 20, 21 }, 1 )
        );
        assertArrayEquals(
                new int[] { 1, 1, 3, 3, 7, 2, 2, 2 },
                EnoughIsEnough.deleteNth( new int[] { 1, 1, 3, 3, 7, 2, 2, 2, 2 }, 3 )

        );
        assertArrayEquals(
                new int[] { 1, 2, 3, 1, 1, 2, 2, 3, 3, 4, 5 },
                EnoughIsEnough.deleteNth( new int[] { 1, 2, 3, 1, 1, 2, 1, 2, 3, 3, 2, 4, 5, 3, 1 }, 3 )
        );
        assertArrayEquals(
                new int[] { 1, 1, 1, 1, 1 },
                EnoughIsEnough.deleteNth( new int[] { 1, 1, 1, 1, 1 }, 5 )
        );
        assertArrayEquals(
                new int[] { },
                EnoughIsEnough.deleteNth( new int[] { }, 5 )
        );

    }

    @Test
    public void random() throws Exception {
        Random random = new Random();
        Map<Integer, Integer> cache = new HashMap<>();
        for ( int i = 0; i < 100; i++ ) {
            int maxOccurrences = random.nextInt( 10 );
            int size = random.nextInt( 100 );
            int[] testData = IntStream.generate( () -> random.nextInt( size ) ).limit( size ).toArray();
            int[] copyOfTestData = Arrays.copyOf( testData, testData.length );

            cache.clear();

            assertArrayEquals(
                    Arrays.stream( copyOfTestData )
                            .filter( el -> cache.merge( el, 1, (a, b) -> a + b ) <= maxOccurrences )
                            .toArray(),
                    EnoughIsEnough.deleteNth( testData, maxOccurrences )
            );
        }
    }
}