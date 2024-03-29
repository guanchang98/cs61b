import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String a = "big";
        assertEquals(false, palindrome.isPalindrome(a));
        String b = "ala";
        assertEquals(true, palindrome.isPalindrome(b));
        String c = "sksks";
        assertEquals(true, palindrome.isPalindrome(c));
    }

    @Test
    public void testIsPalindrome2() {
        String a = "big";
        assertEquals(false, palindrome.isPalindrome(a, new OffByOne()));
        String b = "alb";
        assertEquals(true, palindrome.isPalindrome(b, new OffByOne()));
        String c = "rlsks";
        assertEquals(true, palindrome.isPalindrome(c, new OffByOne()));
    }
}
