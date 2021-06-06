package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(3);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(0);
        arb.enqueue(1);
        assertEquals(0, arb.dequeue());
        arb.enqueue(2);
        arb.enqueue(0);
        assertTrue(arb.isFull());
        assertEquals(1, arb.dequeue());
    }
}
