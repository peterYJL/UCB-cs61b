package deque;

import java.util.Iterator;

public interface Deque<T> extends Iterable<T> {

    /**
     * Adds an item of type T to the front of the deque.
     * You can assume that item is never null.
     * @param item  the element want to add first
     */
    public void addFirst(T item);

    /**
     * Adds an item of type T to the back of the deque.
     * You can assume that item is never null.
     * @param item the element want to add last
     */
    public void addLast(T item);

    /**
     * Check whether deque is empty
     * @return Returns true if deque is empty, false otherwise.
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Size of Deque
     * @return Returns the number of items in the deque.
     */
    public int size();

    /**
     * Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been printed,
     * print out a new line.
     */
    default public void printDeque() {
        for (T item : this) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return the item at the front of the deque.
     */
    public T removeFirst();

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * @return the item at the back of the deque.
     */
    public T removeLast();

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque!
     * @param index the index of item you want to get
     * @return the item of the given index. If no such item exists,
     * returns null.
     */
    public T get(int index);

}
