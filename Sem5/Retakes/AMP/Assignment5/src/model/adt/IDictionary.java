package model.adt;

import java.util.Map;

public interface IDictionary<K, V> {
    void update(K key, V value);
    boolean containsKey(K key);
    boolean containsValue(V value);
    boolean isEmpty();
    int size();
    V lookUp(K key);
    String toString();
    void remove(K key);
    public Map<K, V> getContent();
}
