import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void TestEqualChars() {
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(false, offByOne.equalChars('a', 'a'));
        assertEquals(true, offByOne.equalChars('b', 'a'));
        assertEquals(false, offByOne.equalChars('a', 'z'));
    }
    // Your tests go here.
}
