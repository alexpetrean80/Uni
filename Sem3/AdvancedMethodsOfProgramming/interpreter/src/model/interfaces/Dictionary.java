package model.interfaces;


public interface Dictionary<K, V> {
    void add(K key, V value);

    V getValue(K key);

    boolean doesKeyExist(K key);

    void update(K key, V newValue);

    void remove(K key);

    boolean isEmpty();

    void clear();
}
