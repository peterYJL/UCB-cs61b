package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }

        T maxElement = get(0);
        for (T item : this) {
            if (comparator.compare(maxElement, item) < 0) {
                maxElement = item;
            }
        }
        return maxElement;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }

        T maxElement = get(0);
        for (T item : this) {
            if (c.compare(maxElement, item) < 0) {
                maxElement = item;
            }
        }
        return maxElement;
    }
}
