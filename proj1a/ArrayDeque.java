public class ArrayDeque<T> {
    /** Creates an empty array deque*/
    private int size;
    private T[] items;

    /** First constructor of array deque*/
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
    }

    /** Second constructor of array deque*/
    public ArrayDeque(ArrayDeque<T> other) {
        size = 0;
        items = (T[]) new Object[8];
        for (int i = 0; i < other.size(); i++) {
            items[i] = other.get(i);
        }
    }

    /** Resize the array items*/
    private void resize(int cap) {
        T[] temp = (T[]) new Object[cap];
        System.arraycopy(items, 0, temp, 0, size);
        items = temp;
    }

    /** Adds an item of type T to the front of the array deque*/
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        for (int i = size; i > 0; i--) {
            items[i] = items[i - 1];
        }
        items[0] = item;
        size++;
    }

    /** Adds an item of type T to the back of the array deque*/
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size++;
    }

    /** Return true if the deque is empty, false otherwise*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the size of the array deque*/
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, separated by a space
     * Once all the items have been printed, print out a new line*/
     public void printDeque() {
         for (int i = 0; i < size; i++) {
             System.out.print(items[i] + "/t");
         }
         System.out.println();
     }

    /** Removes the first item of the deque
     * returns removed item
     * if no such item exists, return null*/
    public T removeFirst() {
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        for (int i = 0; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        T res = items[size - 1];
        items[size - 1] = null;
        size--;
        return res;
    }

    /** Removes the last item of the deque
     * returns removed item
     * if no such item exists, return null*/
    public T removeLast() {
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        T res = items[size - 1];
        items[size - 1] = null;
        size--;
        return res;
    }

    /** Get the item at the given index
     * if no such item exists, return null
     */
    public T get(int index) {
        return items[index];
    }
}