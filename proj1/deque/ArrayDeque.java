package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    /**
     * 这个是指向队列的头下标, 队列的首端元素
     */
    private int head;
    /**
     * 这个是指向队列的尾下标, 队列尾端元素的下一个空位
     */
    private int tail;
    private int size = 0;

    public ArrayDeque() {
        head = 0;
        tail = 0;
        items = (T[]) new Object[8];
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        /* 尾在后 */
        if (tail - head > 0) {
            try {
                System.arraycopy(items, head, newArray, 0, size);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else {
            int headToArrayEndDistance = items.length - 1 - head;
            System.arraycopy(items, head, newArray, 0, headToArrayEndDistance + 1);
            System.arraycopy(items, 0, newArray, headToArrayEndDistance + 1, tail);
        }
        head = 0;
        tail = size;
        items = newArray;
    }

    private int markForward(int mark) {
        if (mark + 1 == items.length) {
            return 0;
        }
        return mark + 1;
    }

    private int markBack(int mark) {
        if (mark - 1 == -1) {
            return items.length - 1;
        }
        return mark - 1;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            growSize();
        }
        /* 注意要先把size +=1 放前面 而不是markdownBack在前 */
        size += 1;
        int addLocation = markBack(head);
        items[addLocation] = item;
        head = addLocation;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            growSize();
        }
        items[tail] = item;
        size += 1;
        /* 注意要先把size +=1 放前面 而不是markdownForward在前 */
        tail = markForward(tail);
    }

    private void growSize() {
        resize(size * 2);
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
        if (items.length / 4 > size && size > 8) {
            decreaseSize();
        }
        T item = items[head];
        items[head] = null;
        size -= 1;
        head = markForward(head);
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length / 4 > size && size > 8) {
            decreaseSize();
        }
        size -= 1;
        tail = markBack(tail);
        T item = items[tail];
        items[tail] = null;
        return item;
    }

    private void decreaseSize() {
        resize(items.length / 4);
    }

    @Override
    public T get(int index) {
        index = head + index;
        if (index >= items.length) {
            index = index - items.length;
        }
        return items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private boolean contains(T item) {
        for (T currentItem : items) {
            if (item == null) {
                return false;
            }
            if (currentItem.equals(item)) {
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
        for (int i = 0; i < paramDeque.size(); i++) {
            if (!contains(paramDeque.get(i))) {
                return false;
            }
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = head;
        }

        @Override
        public boolean hasNext() {
            return index != tail;
        }

        @Override
        public T next() {
            T item = items[index];
            index = markForward(index);
            return item;
        }
    }
}
