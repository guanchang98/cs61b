public class LinkedListDeque<T> {
    private ListNode first;
    private ListNode last;
    private int size;
    /* Helper ListNode class*/
    private class ListNode {
        public T item;
        public ListNode next;
        public ListNode prev;
        public ListNode(T item, ListNode next, ListNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedListDeque() {
        size = 0;
    }

    /** Gets a copy of the LinkedListDeque other*/


    /** Add an item of type T to the front of the deque*/
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
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
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
    public T removeFirst() {
        if (size == 0) return null;
        if (size == 1) {
            size--;
            return null;
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
    public T removeLast() {
        if (size == 0) return null;
        if (size == 1) {
            size--;
            return null;
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
    public T get(int index) {
        if (size <= index) return null;
        else if (index < size / 2) {
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
    public T getRecursively(int index) {
        ListNode tmp = first;
        if (tmp == null) return null;
        return helperGet(index, tmp);
    }
    public T helperGet(int index, ListNode first) {
        if (index == 0) return first.item;
        first = first.next;
        return helperGet(index - 1, first);
    }
}