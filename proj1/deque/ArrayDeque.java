package deque;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size = 0;

    public ArrayDeque() {
        items = (T[]) new Object[8];
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
            growSize();
        }
        T[] newItems = (T[]) new Object[items.length];
        System.arraycopy(items, 0, newItems, 1, size);
        newItems[0] = item;
        items = newItems;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            growSize();
        }
        items[size] = item;
        size += 1;
    }

    private void growSize(){
        resize((int) (size * (1.25)));
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
        if (items.length / 4 > size && size > 4) {
            decreaseSize();
        }
        T[] newItems = (T[]) new Object[items.length];
        size -= 1;
        System.arraycopy(items, 1, newItems, 0, size);
        T first = items[0];
        items = newItems;
        return first;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length / 4 > size && size > 4) {
            decreaseSize();
        }
        size -= 1;
        T item = items[size];
        items[size] = null;
        return item;
    }

    private void decreaseSize(){
        resize(items.length / 4);
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
        if (!(obj instanceof Deque)) {
            return false;
        }
        Deque<T> paramDeque = (Deque<T>) obj;
        if (paramDeque.size() != size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!contains(paramDeque.get(i))) {
                return false;
            }
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
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
