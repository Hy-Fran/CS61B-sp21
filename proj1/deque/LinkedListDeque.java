package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private final Node sentinel;
    private int size = 0;
    private Node recursive;

    /**
     * 创建空链表
     */
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        recursive = sentinel;
    }

    /**
     * 在deque的前端添加一个类型为T的项目。你可以假设item永远不会是null
     *
     * @param item - never null
     */
    @Override
    public void addFirst(T item) {
        size++;
        Node original = sentinel.next;
        Node newNode = new Node(item, sentinel, original);
        original.prev = newNode;
        sentinel.next = newNode;
    }

    /**
     * 在deque的后端添加一个类型为T的项目。你可以假设item永远不会是null。
     *
     * @param item - nerver null
     */
    @Override
    public void addLast(T item) {
        size++;
        Node original = sentinel.prev;
        Node newNode = new Node(item, original, sentinel);
        original.next = newNode;
        sentinel.prev = newNode;
    }

    /**
     * 返回deque中的项目数
     *
     * @return 返回大小
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 从第一个到最后一个打印deque中的项目，项目之间用空格隔开。打印完所有项目后，输出一个新行。
     */
    @Override
    public void printDeque() {
        Node node = sentinel;
        while (node.next != sentinel) {
            node = node.next;
            System.out.print(node.item + " ");
        }
        System.out.println();
    }

    /**
     * 移除并返回deque前端的项目。如果没有这样的项目，返回null。
     *
     * @return remove first item, if it not exists then return null
     */
    @Override
    public T removeFirst() {
        Node first = sentinel.next;
        if (first == sentinel) {
            return null;
        } else {
            size--;
            Node newFirst = first.next;
            sentinel.next = newFirst;
            newFirst.prev = sentinel;
            return first.item;
        }
    }

    /**
     * 移除并返回deque后端的项目。如果没有这样的项目，返回null。
     *
     * @return remove last item, if it not exists then return null
     */
    @Override
    public T removeLast() {
        Node last = sentinel.prev;
        if (last == sentinel) {
            return null;
        } else {
            size--;
            Node newLast = last.prev;
            //set newLast
            sentinel.prev = newLast;
            //set newLast next to sentinel
            newLast.next = sentinel;
            return last.item;
        }
    }

    /**
     * 获取给定索引处的项目，其中0是前端，1是下一个项目，依此类推。如果没有这样的项目，返回null。调用此方法不得更改deque！
     *
     * @param index - index
     * @return 返回对应item
     */
    @Override
    public T get(int index) {
        Node node = sentinel;
        while (node.next != sentinel) {
            node = node.next;

            if (index == 0) {
                return node.item;
            }
            index -= 1;
        }
        return null;
    }

    /**
     * 使用递归获得item
     *
     * @param index - 下标
     * @return 返回对应item
     */
    public T getRecursive(int index) {
        if (index == 0) {
            Node node = recursive.next;
            recursive = sentinel;
            return node.item;
        }
        recursive = recursive.next;
        if (recursive == sentinel) {
            return null;
        }
        return getRecursive(index - 1);
    }

    /**
     * 我们将制作的Deque对象是可迭代的（即Iterable<T>），因此我们必须提供此方法以返回一个迭代器。
     *
     * @return LinkedListIterator
     * @NotNull
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * 返回参数是否等于Deque。
     * 若o是一个Deque并且包含相同的内容且顺序相同，则认为它们相等
     *
     * @param o - object
     * @return 是否相等
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> paramDeque = (Deque<T>) o;
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

    private boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            T item = get(i);
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }

    private class Node {
        private final T item;
        private Node prev;
        private Node next;

        Node(T item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node node;

        LinkedListIterator() {
            node = sentinel;
        }

        public boolean hasNext() {
            return node.next != sentinel;
        }

        public T next() {
            node = node.next;
            return node.item;
        }

    }
}
