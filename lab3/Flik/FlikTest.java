import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik() {
        Integer a = 128;
        Integer b = 128;
        boolean c = a == b;
        assertEquals(c, Flik.isSameNumber(a, b));
    }
}