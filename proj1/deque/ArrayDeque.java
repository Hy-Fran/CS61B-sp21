package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size = 0;

    public ArrayDeque() {
        this(100);
    }

    public ArrayDeque(int capacity) {
        items = (T[]) new Object[capacity];
    }

    public void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size && i < capacity; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        size += 1;
        T swap = item;
        for (int i = 0; i < size; i++) {
            T original = items[i];
            items[i] = swap;
            swap = original;
        }
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (T item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T first = items[0];

        T swap = items[size];
        for (int i = size - 1; i >= 0; i--) {
            T original = items[i];
            items[i] = swap;
            swap = original;
        }
        return first;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T item = items[size];
        items[size] = null;
        return item;
    }

    @Override
    public T get(int index) {
        return items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<T> cast = (ArrayDeque<T>) obj;
        for (int i = 0; i < size; i++) {
            if (!cast.contains(items[i])) {
                return false;
            }
        }
        return true;
    }

    public class ArrayDequeIterator implements Iterator<T> {
        private int index;

        public ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index + 1) < size;
        }

        @Override
        public T next() {
            T item = items[index];
            index += 1;
            return item;
        }
    }
}
