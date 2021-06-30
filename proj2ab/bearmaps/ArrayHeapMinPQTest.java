package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void sanityGenericsTest() {
        try {
            ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
            ArrayHeapMinPQ<Integer> b = new ArrayHeapMinPQ<>();
            ArrayHeapMinPQ<Double> c = new ArrayHeapMinPQ<>();
            ArrayHeapMinPQ<Boolean> e = new ArrayHeapMinPQ<>();
        } catch (Exception e) {
            System.out.println("Constructor error");
        }
    }

    @Test
    public void sizeTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        assertEquals(pq.size(), 0);
        for (int i = 1; i < 10000; i++) {
            pq.add(i, i);
            assertEquals(i, pq.size());
        }
        pq.removeSmallest();
        assertEquals(9998, pq.size());
        for (int i = 0; i < 9; i++) {
            pq.removeSmallest();
            assertEquals(9997 - i, pq.size());
        }
    }

    @Test
    public void containsTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        assertFalse(pq.contains(1));
        pq.add(1, 1);
        assertTrue(pq.contains(1));
        for (int i = 2; i < 200; i++) {
            assertFalse(pq.contains(i));
            pq.add(i, i);
            assertTrue(pq.contains(i));
        }
    }

    @Test
    public void addTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 100; i >= 1; i--) {
            pq.add(i, i);
            assertEquals(i, (int) pq.getSmallest());
        }
    }

    @Test
    public void removeSmallestTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 9; i >= 1; i--) {
            pq.add(i, i);
            assertEquals((Integer) i, pq.getSmallest());
        }
        assertEquals((Integer) 1, pq.removeSmallest());
        pq.add(1, 1);
        assertEquals((Integer) 1, pq.removeSmallest());
    }
    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 9; i >= 1; i--) {
            pq.add(i, i);
        }
        assertEquals((Integer) 1, pq.getSmallest());
        pq.changePriority(5, 0.5);
        assertEquals((Integer) 5, pq.getSmallest());
        pq.changePriority(9, 0.3);
        assertEquals((Integer) 9, pq.getSmallest());
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }
}
