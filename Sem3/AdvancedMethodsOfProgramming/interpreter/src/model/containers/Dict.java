package model.containers;

import java.util.HashMap;

public class Dict<K, V> implements model.interfaces.Dictionary<K, V> {

    HashMap<K, V> dictionary;

    public Dict() {
        dictionary = new HashMap<>();
    }

    @Override
    public void add(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public V getValue(K key) {
        return dictionary.get(key);
    }

    @Override
    public boolean doesKeyExist(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void update(K key, V newValue) {
        dictionary.replace(key, newValue);
    }

    @Override
    public void remove(K key) {
        dictionary.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public void clear() {
        dictionary.clear();
    }
}
