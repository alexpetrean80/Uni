package model.adt;

import java.util.Map;

public interface Dict<K, V> {
    void update(K key, V value);
    boolean containsKey(K key);
    boolean containsValue(V value);
    boolean isEmpty();
    int size();
    V lookup(K key);
    String toString();
    void remove(K key);
    public Map<K, V> getContent();
    public void setContent(Map<K, V> newContent);
    //public IDictionary<K,V> deepCopy();
}
