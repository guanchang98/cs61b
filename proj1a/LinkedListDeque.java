public class LinkedListDeque<T> implements Deque<T> {
    /** Creates an empty class of array deque*/
    private ListNode first;
    private ListNode last;
    private int size;
    /* Helper ListNode class*/
    private class ListNode {
        private T item;
        private ListNode next;
        private ListNode prev;
        private ListNode(T item, ListNode next, ListNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    /** First constructor of the deque*/
    public LinkedListDeque() {
        size = 0;
    }

    /** Gets a copy of the LinkedListDeque other
    public LinkedListDeque(LinkedListDeque<T> other) {
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }
    */


    /** Add an item of type T to the front of the deque*/
    @Override
    public void addFirst(T item) {
        ListNode temp = new ListNode(item, first, null);
        if (first == null) {
            first = temp;
        } else {
            first.prev = temp;
            first = temp;
        }
        if (last == null) {
            last = temp;
        }
        size++;
    }

    /** Add an item of type T to the back of the deque*/
    @Override
    public void addLast(T item) {
        ListNode temp = new ListNode(item, null, last);
        if (last == null) {
            last = temp;
        } else {
            last.next = temp;
            last = temp;
        }
        if (first == null) {
            first = temp;
        }
        size++;
    }

    /** Return true if deque is empty, false otherwise*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque*/
    @Override
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    @Override
    public void printDeque() {
        ListNode temp = first;
        while (temp != null) {
            System.out.print(temp.item + "\t");
            temp = temp.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, return null.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T res = first.item;
            size--;
            first = null;
            last = null;
            return res;
        }
        ListNode temp = first;
        first = first.next;
        first.prev = null;
        size--;
        return temp.item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, return null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T res = last.item;
            size--;
            first = null;
            last = null;
            return res;
        }
        ListNode temp = last;
        last = last.prev;
        last.next = null;
        size--;
        return temp.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth
     * If no such item exists, return null.
     */
    @Override
    public T get(int index) {
        if (size <= index) {
            return null;
        } else if (index < size / 2) {
            ListNode temp = first;
            while (index > 0) {
                temp = temp.next;
                index--;
            }
            return temp.item;
        } else {
            ListNode temp = last;
            while (size - index > 1) {
                temp = temp.prev;
                index++;
            }
            return temp.item;
        }
    }
    /** Gets the item at the given index recursively*/
    public T getRecursive(int index) {
        ListNode tmp = first;
        if (tmp == null) {
            return null;
        }
        return helperGet(index, tmp);
    }
    private T helperGet(int index, ListNode f) {
        if (index == 0) {
            return f.item;
        }
        f = f.next;
        return helperGet(index - 1, f);
    }
}
