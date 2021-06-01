public class ArrayDeque<T> {
    /** Creates an empty array deque*/
    private int size;
    private int first;
    private int last;
    private T[] items;

    /** First constructor of array deque*/
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        first = items.length / 2 - 1;
        last = items.length / 2;
    }

    /** Second constructor of array deque
    public ArrayDeque(ArrayDeque<T> other) {
        size = 0;
        items = (T[]) new Object[8];
        for (int i = 0; i < other.size(); i++) {
            items[i] = other.get(i);
        }
    }*/

    /** Resize the array items*/
    private void resize(int cap) {
        T[] temp = (T[]) new Object[cap];
        System.arraycopy(items, first + 1, temp, cap / 2 - items.length / 2 + first + 1, last - first - 1);
        first = cap / 2 - items.length / 2 + first;
        last = cap / 2 - items.length / 2 + last;
        items = temp;
    }

    /** Adds an item of type T to the front of the array deque*/
    public void addFirst(T item) {
        if (first < 0) {
            resize(items.length * 2);
        }
        items[first] = item;
        first--;
        size++;
    }

    /** Adds an item of type T to the back of the array deque*/
    public void addLast(T item) {
        if (last == items.length) {
            resize(items.length * 2);
        }
        items[last] = item;
        last++;
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
         for (int i = 0; i < items.length; i++) {
             System.out.print(items[i] + "\t");
         }
         System.out.println();
     }

    /** Removes the first item of the deque
     * returns removed item
     * if no such item exists, return null*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        T res = items[first + 1];
        items[first + 1] = null;
        size--;
        first++;
        return res;
    }

    /** Removes the last item of the deque
     * returns removed item
     * if no such item exists, return null*/
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        T res = items[last - 1];
        items[last - 1] = null;
        size--;
        last--;
        return res;
    }

    /** Get the item at the given index
     * if no such item exists, return null
     */
    public T get(int index) {
        return items[first + 1 + index];
    }
}