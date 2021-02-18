package deque;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private static final int START_SIZE = 8;
    private T[] items;
    private int nextHead;
    private int tail;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[START_SIZE];
        nextHead = 0;
        tail = 0;
        size = 0;
    }

    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        if (nextHead >= tail) {
            System.arraycopy(items, plusIndex(nextHead), newItems, 0, items.length - nextHead - 1);
            System.arraycopy(items, 0, newItems, items.length - nextHead - 1, tail+1);
        } else {
            System.arraycopy(items, plusIndex(nextHead), newItems, 0, tail - nextHead);
        }
        items = newItems;
        nextHead = minusIndex(0);
        tail = size - 1;
    }

    private int minusIndex(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    private int plusIndex(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    @Override
    public void addFirst(T item) {
        if (nextHead == tail && size == items.length) {
            resize(2 * items.length);
        }
        items[nextHead] = item;
        nextHead = minusIndex(nextHead);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (nextHead == tail && size == items.length) {
            resize(2 * items.length);
        }
        tail = plusIndex(tail);
        items[tail] = item;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        nextHead = plusIndex(nextHead);
        T removeItem = items[nextHead];
        items[nextHead] = null;
        size -= 1;
        if (size*1.0 / items.length < 0.25 && items.length > START_SIZE) {
            resize(items.length / 2);
        }
        return removeItem;
    }

    @Override
    public T removeLast() {
        T removeItem = items[tail];
        items[tail] = null;
        tail = minusIndex(tail);
        size -= 1;

        if (size*1.0 / items.length < 0.25 && items.length > START_SIZE) {
            resize(items.length / 2);
        }
        return removeItem;
    }

    @Override
    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        return this.items[index];
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wisPos;
        private int count;

        public ArrayDequeIterator() {
            wisPos = plusIndex(nextHead);
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T returnItem = items[wisPos];
            wisPos = plusIndex(wisPos);
            count += 1;
            return returnItem;
        }
    }

    public static void main(String [] args) {
        ArrayDeque<Integer> ADeque = new ArrayDeque<>();
        ADeque.addFirst(1);
        ADeque.addFirst(2);
        ADeque.addFirst(3);
        ADeque.addFirst(4);
        ADeque.addFirst(5);
        ADeque.addFirst(6);
        ADeque.addFirst(7);
        ADeque.addFirst(8);
        ADeque.addFirst(9);

        ADeque.printDeque();
        System.out.println(ADeque.size());
    }
}
