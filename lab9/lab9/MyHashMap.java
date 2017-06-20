package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;

/**
 * Created by qianzhenli on 6/19/17.
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize;
    private int mapSize;
    private double loadFactor;
    private HashSet allKeys;
    private LinkedList<Node> [] root;

    public MyHashMap(){
        initialSize = 10;
        loadFactor = 2;
        mapSize = 0;
        root = new LinkedList [10];
        allKeys = new HashSet();
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        loadFactor = 2;
        mapSize = 0;
        root = new LinkedList [10];
        allKeys = new HashSet();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        mapSize = 0;
        root = new LinkedList [10];
        allKeys = new HashSet();
    }


    private class Node {
        private K key;
        private V val;

        private Node(K k, V v) {
            key = k;
            val = v;
        }
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = new LinkedList [10];
        allKeys = new HashSet();
        mapSize = 0;
    }

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        } else if (get(key) != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key == null) {
            return null;
        } else {
            int location = locationCalculator(key, root.length);
            LinkedList<Node> x = root[location];
            if (x != null) {
                V val = null;
                for (Node i : x) {
                    if (i.key.equals(key)) {
                        val = i.val;
                        break;
                    }
                }
                return val;
            } else {
                return null;
            }
        }
    }

    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return mapSize;
    }

    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        } else {
            root = putHelper(root, key, value);
            mapSize += 1;
            allKeys.add(key);
            if (mapSize/root.length >= loadFactor ) {
                resize();
            }
        }
    }

    private void resize() {
        LinkedList<Node> [] a = new LinkedList [2 * root.length];
        for (LinkedList<Node> x : root) {
            if (x != null) {
                for (Node y : x) {
                    a = putHelper(a, y.key, y.val);
                }
            }
        }
        root = a;
    }

    private LinkedList<Node> [] putHelper(LinkedList<Node> [] x, K k, V v ) {
        int location = locationCalculator(k, x.length);
        if (x[location] == null) {
            x[location] = new LinkedList<Node>();
        }
        x[location].add(new Node(k, v));
        return x;
    }

    private int locationCalculator(K k, int length) {
        int location = k.hashCode()%length;
        if (location < 0) {
            location += length;
            location %= length;
        }
        return location;
    }

    @Override
    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return allKeys;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {return allKeys.iterator();}

    /*
        private class HashMapIterator implements Iterator {
        private int ptr;
        public HashMapIterator() {
            ptr = 0;
        }

        public boolean hasNext() {
            return (ptr != allKeys.size());
        }

        public K next() {
            K returnItem = allKeys.

        }
    }
     */


}
