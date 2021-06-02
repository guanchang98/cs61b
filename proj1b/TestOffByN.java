import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertEquals(true, offByN.equalChars('a', 'f'));
        assertEquals(false, offByN.equalChars('a', 'a'));
        assertEquals(true, offByN.equalChars('f', 'a'));
        assertEquals(false, offByN.equalChars('A', 'F'));
    }
    // Your tests go here.
}

