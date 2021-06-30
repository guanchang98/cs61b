package bearmaps;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Map;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private T[] pq;
    private double[] priority;
    private int size;
    private Map<T, Integer> map;

    public ArrayHeapMinPQ(int initCapacity) {
        pq = (T[]) new Object[initCapacity + 1];
        priority = new double[initCapacity + 1];
        size = 0;
        map = new HashMap<>();
    }

    public ArrayHeapMinPQ() {
        this(1);
    }


    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        if (size == pq.length - 1) {
            resize(2 * pq.length);
        }
        if (map.containsKey(item)) {
            throw new IllegalArgumentException("Item is already present");
        }
        size++;
        this.priority[size] = priority;
        this.pq[size] = item;
        swim(size, item);
    }
    /* Swaps two items in the pq array.*/
    private void swap(int i, int j) {
        T tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
        double temp = priority[i];
        priority[i] = priority[j];
        priority[j] = temp;
        map.put(pq[i], i);
        map.put(pq[j], j);
    }

    private void swim(int i, T item) {
        map.put(item, i);
        while (i > 1 && priority[i] - priority[i / 2] < 0) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    private void sink(int i, T item) {
        map.put(item, i);
        while (i * 2 <= size) {
            if (i * 2 < size) {
                if (priority[2 * i] - priority[2 * i + 1] < 0) {
                    if (priority[i] - priority[2 * i] > 0) {
                        swap(i, 2 * i);
                        i = 2 * i;
                    } else {
                        break;
                    }
                } else {
                    if (priority[i] - priority[2 * i + 1] > 0) {
                        swap(i, 2 * i + 1);
                        i = 2 * i + 1;
                    } else {
                        break;
                    }
                }
            } else {
                if (priority[i] - priority[2 * i] > 0) {
                    swap(i, 2 * i);
                    i = 2 * i;
                } else {
                    break;
                }
            }
        }
    }

    private void resize(int capacity) {
        assert size < capacity;
        T[] temp1 = (T[]) new Object[capacity];
        for (int i = 1; i <= size; i++) {
            temp1[i] = pq[i];
        }
        pq = temp1;
        double[] temp2 = new double[capacity];
        for (int i = 1; i <= size; i++) {
            temp2[i] = priority[i];
        }
        priority = temp2;
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return map.containsKey(item);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return pq[1];
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("The PQ is empty");
        }
        T temp = pq[1];
        map.remove(pq[1]);
        priority[1] = priority[size];
        priority[size] = 0.0;
        pq[1] = pq[size];
        pq[size] = null;
        size--;
        if ((size > 0) && (size == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        sink(1, pq[1]);
        return temp;
    }
    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!map.containsKey(item)) {
            throw new NoSuchElementException("The item doesn't exist");
        }
        int index = map.get(item);
        this.priority[index] = priority;
        sink(index, item);
        swim(index, item);
    }
}
