package deque;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        T item;
        Node prev;
        Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private final Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (isEmpty()) {
            Node newNode = new Node(item, sentinel, sentinel);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        }
        Node tempNode = sentinel.next;
        Node newNode = new Node(item, sentinel, tempNode);
        tempNode.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (isEmpty()) {
            Node newNode = new Node(item, sentinel, sentinel);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        }
        Node tempNode = sentinel.prev;
        Node newNode = new Node(item, tempNode, sentinel);
        tempNode.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node removeNode = sentinel.next;
        sentinel.next = removeNode.next;
        removeNode.next.prev = sentinel;
        size -= 1;
        return removeNode.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node removeNode = sentinel.prev;
        sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        size -= 1;
        return removeNode.item;
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        Node tempNode = sentinel;
        while (index >= 0) {
            tempNode = tempNode.next;
            index -= 1;
        }
        return tempNode.item;
    }

    private Node getRecursiveHelper(Node tempNode, int index) {
        if (index == 0) {
            return tempNode.next;
        } else {
            return getRecursiveHelper(tempNode.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        Node tempNode = getRecursiveHelper(sentinel, index);
        return tempNode.item;
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        Deque<?> list = (Deque<?>) o;
        if (this.size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.get(i) != list.get(i)) {
                return false;
            }
        }
        return true;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        public LinkedListDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public static void main(String [] args) {
        Deque<Integer> DLList = new LinkedListDeque<>();
        DLList.addFirst(1);
        DLList.addFirst(2);
        DLList.addFirst(3);
        Deque<Integer> DLList2 = new LinkedListDeque<>();
        DLList2.addFirst(1);
        DLList2.addFirst(2);
        DLList2.addFirst(3);
        System.out.println(DLList.equals(DLList2));
        System.out.println(((LinkedListDeque<?>) DLList).getRecursive(2));
        DLList.printDeque();
    }
}
