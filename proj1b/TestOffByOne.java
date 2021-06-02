import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(false, offByOne.equalChars('a', 'a'));
        assertEquals(true, offByOne.equalChars('b', 'a'));
        assertEquals(true, offByOne.equalChars('M', 'N'));
        assertEquals(true, offByOne.equalChars('&', '%'));
        assertEquals(false, offByOne.equalChars('a', 'B'));
    }
    // Your tests go here.
}
