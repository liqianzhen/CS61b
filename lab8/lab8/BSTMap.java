package lab8;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by qianzhenli on 6/15/17.
 */
public class BSTMap<K extends Comparable<K> , V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        private Node(K k, V v, int s ) {
            key = k;
            val = v;
            size = s;
        }
    }


    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (get(root, key) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K k) {
        if (k == null) {
            throw new IllegalArgumentException();
        } else if (x == null) {
            return null;
        } else if (x.key.equals(k)) {
            return x.val;
        } else if (x.key.compareTo(k) < 0 ) {
            return get(x.right, k);
        } return get(x.left, k);
    }

    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }


    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        } else {
            root = put(root, key, value);
        }

    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        } else if (x.key.compareTo(key) < 0) {
            x.right = put(x.right, key, value);
        } else {
            x.left = put(x.left, key, value);
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }


    @Override
    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> allKeys = new HashSet<K>();
        return keySet(allKeys, root);
    }

    private Set<K> keySet(Set<K> allKeys, Node x) {
        if (x == null) {
            return allKeys;
        } else {
            allKeys = keySet(allKeys, x.left);
            allKeys = keySet(allKeys, x.right);
            allKeys.add(x.key);
            return allKeys;
        }
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
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
