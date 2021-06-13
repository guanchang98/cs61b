import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private int initialSize = 16;
    private double loadFactor = 0.75;
    private int size = 0;
    private ArrayList<ArrayList<Entry>> map;
    private Set<K> keySet;

    public MyHashMap() {
        map = new ArrayList<>(initialSize);
        keySet = new HashSet<>();
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        map = new ArrayList<>(initialSize);
        keySet = new HashSet<>();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        map = new ArrayList<>(initialSize);
        keySet = new HashSet<>();
    }

    private class Entry {
        Entry(K k, V v) {
            key = k;
            val = v;
        }
        K key;
        V val;
    }

    /**
     * Gets the hash index of the ArrayList<Entry></>
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % initialSize;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        map = new ArrayList<>(16);
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (map == null) {
            return false;
        }
        return get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        ArrayList<Entry> cur = map.get(index);
        for (Entry e : cur) {
            if (e.key == key) {
                return e.val;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int index = hash(key);
        Entry cur = new Entry(key, value);
        if (!keySet.contains(key)) {
            map.get(index).add(cur);
            keySet.add(key);
            size++;
        } else {
            ArrayList<Entry> list = map.get(index);
            for (Entry e : list) {
                if (e.key == key) {
                    e.val = value;
                }
            }
        }
        if (size / initialSize >= loadFactor) {
            resize();
        }
    }

    /**
     * Resizes ArrayList if exceeds loadFactor
     */
    private void resize() {
        initialSize = 2 * initialSize;
        ArrayList<ArrayList<Entry>> newMap = new ArrayList<>(initialSize);
        for (ArrayList<Entry> bucket : map) {
            for (Entry e : bucket) {
                int index = hash(e.key);
                Entry cur = new Entry(e.key, e.val);
                newMap.get(index).add(cur);
            }
        }
        map = newMap;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Iterator of MyHashMap
     */
    @Override
    public Iterator<K> iterator() {
        return new myHashMapIterator();
    }

    private class myHashMapIterator implements Iterator<K> {
        @Override
        public boolean hasNext() {
            return keySet.iterator().hasNext();
        }

        @Override
        public K next() {
            return keySet.iterator().next();
        }
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

}
