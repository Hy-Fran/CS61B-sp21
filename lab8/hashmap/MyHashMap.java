package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private int size;
    private double maxLoadFactor;

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = 0;
        maxLoadFactor = maxLoad;
        buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i].clear();
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(buckets, key);
        Collection<Node> bucket = buckets[index];
        return findNode(key) != null;
    }

    private Node findNode(K key) {
        for (Node node : buckets[calculateIndex(buckets, key)]) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = findNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if ((double) size / buckets.length >= maxLoadFactor) {
            resize(2);
        }
        Node node = findNode(key);
        if (node != null) {
            node.value = value;
        }
        else {
            noResizePut(buckets, createNode(key, value));
            size += 1;
        }
    }

    private void noResizePut(Collection<Node>[] buckets, Node node) {
        int mod = calculateIndex(buckets, node.key);
        buckets[mod].add(node);
    }

    private int calculateIndex(Collection<Node>[] buckets, K key) {
        int index = key.hashCode() % buckets.length;
        if (index < 0) {
            index = buckets.length + index;
        }
        return index;
    }

    private void resize(double factor) {
        Collection<Node>[] newBuckets = createTable((int) (buckets.length * factor));
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                noResizePut(newBuckets, node);
            }
        }
        buckets = newBuckets;
    }



    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                set.add(node.key);
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        if (size <= (double) buckets.length / 2 * maxLoadFactor) {
            resize(0.5);
        }
        int index = calculateIndex(buckets, key);
        Collection<Node> bucket = buckets[index];
        Iterator<Node> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node.key == key) {
                size--;
                iterator.remove();
                return node.value;
            }
        }
        return null;
    }

    public V remove(K key, V value) {
        Node node = findNode(key);
        if (node != null && node.value == value) {
            return remove(key);
        }
        return null;
    }

    private class MyHashMapIterator implements Iterator<K>{
        private Set<K> keySet;
        private Iterator<K> iterator;

        public MyHashMapIterator() {
            keySet = keySet();
            iterator = keySet.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            return iterator().next();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

}
