package _4kyu;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SecretRecoveryTest {

    private SecretDetective detective;

    @Before public void setup() {
        detective = new SecretDetective();
    }

    @Test public void secret1() {
        char[][] triplets = {
                {'a','s','u'},
                {'m','a','f'},
                {'a','i','n'},
                {'s','u','n'},
                {'m','f','u'},
                {'a','t','h'},
                {'t','h','i'},
                {'h','i','f'},
                {'m','h','f'},
                {'a','u','n'},
                {'m','a','t'},
                {'f','u','n'},
                {'h','s','n'},
                {'a','i','s'},
                {'m','s','n'},
                {'m','s','u'}
        };
        assertEquals("whatisup", detective.recoverSecret(triplets));
    }
}