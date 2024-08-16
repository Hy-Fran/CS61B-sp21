package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private int size = 0;
    private BSTNode root;

    public class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        return get(root, key);
    }

    public V get(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp == 0) {
            return node.value;
        } else {
            return get(node.right, key);
        }
    }

    public V iterationGet(K key) {
        BSTNode node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp == 0) {
                return node.value;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null) {
            node = new BSTNode(key, value);
            size += 1;
            return node;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp == 0) {
            node.value = value;
        } else {
            node.right = put(node.right, key, value);
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {

    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        return find(root, key);
    }

    private boolean find(BSTNode node, K key) {
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return find(node.left, key);
        } else if (cmp == 0) {
            return true;
        } else {
            return find(node.right, key);
        }
    }

    private boolean iterationFind(K key) {
        BSTNode node = root;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp == 0) {
                return true;
            } else {
                node = node.right;
            }
        }
        return false;
    }

}
