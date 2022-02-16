package _4kyu;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class StripCommentsTest {

    private static final Random RANDOM = new Random();

    @Test
    public void stripComments() throws Exception {
        assertEquals(
                "apples, pears\ngrapes\nbananas",
                StripComments.stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" } )
        );

        assertEquals(
                "a\nc\nd",
                StripComments.stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } )
        );

    }

    @Test
    public void edges() throws Exception {
        assertEquals(
                "a\n b\nc",
                StripComments.stripComments( "a \n b \nc ", new String[] { "#", "$" } )
        );
        assertEquals(
                "a",
                StripComments.stripComments( "a", new String[] { "1" } )
        );

        assertEquals(
                "",
                StripComments.stripComments( "a", new String[] { "a" } )
        );

        assertEquals(
                "",
                StripComments.stripComments( "            ", new String[] { "#" } )
        );

        assertEquals(
                "",
                StripComments.stripComments( "# some text", new String[] { "#" } )
        );
    }

    @Test
    public void random() throws Exception {
        String[] comments = { "#", "$", "!", "-" };
        for ( int i = 0; i < 20; i++ ) {
            String test = randomString().replace( "1", comments[RANDOM.nextInt( 4 )] )
                    .replace( "0", "\n" )
                    .replaceAll( "\n+$", "" );
            assertEquals( stripComments( test, comments ), StripComments.stripComments( test, comments ) );
        }
    }

    private static String randomString() {
        return new BigInteger( 1000, RANDOM ).toString( 16 )
                .replaceAll( "[2-9]+", "\n\n" );
    }

    private static String stripComments(String text, String[] commentSymbols) {
        String pattern = String.format(
                "[ ]*([%s].*)?$",
                Arrays.stream( commentSymbols ).collect( Collectors.joining() )
        );
        return Arrays.stream( text.split( "\n" ) )
                .map( x -> x.replaceAll( pattern, "" ) )
                .collect( Collectors.joining( "\n" ) );
    }
}
