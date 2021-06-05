import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {
    @Test
    public void testSizeOf() {
        UnionFind uf = new UnionFind(5);
        int size1 = uf.sizeOf(0);
        assertEquals(1, size1);
        uf.union(0, 1);
        int size2 = uf.sizeOf(0);
        assertEquals(2, size2);
        uf.union(2, 3);
        uf.union(2, 0);
        int size3 = uf.sizeOf(2);
        assertEquals(4, size3);
    }

    @Test
    public void testParent() {
        UnionFind uf = new UnionFind(5);
        assertEquals(-1, uf.parent(2));
        assertEquals(-1, uf.parent(1));
        uf.union(0, 1);
        assertEquals(1, uf.parent(0));
        uf.union(2, 3);
        uf.union(0, 2);
        assertEquals(3, uf.parent(1));
    }

    @Test
    public void testConnected() {
        UnionFind uf = new UnionFind(5);
        assertFalse(uf.connected(0, 1));
        uf.union(0, 1);
        assertTrue(uf.connected(0, 1));
        assertFalse(uf.connected(0, 2));
        uf.union(2, 3);
        assertFalse(uf.connected(0, 2));
        uf.union(3, 1);
        assertTrue(uf.connected(0, 1));
    }

    @Test
    public void testFind() {
        UnionFind uf = new UnionFind(5);
        assertEquals(1, uf.find(1));
        uf.union(0, 1);
        assertEquals(1, uf.find(0));
        uf.union(2, 3);
        uf.union(0, 2);
        assertEquals(3, uf.find(0));
        assertEquals(3, uf.find(1));
        assertEquals(3, uf.find(2));

    }
}
