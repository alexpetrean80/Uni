package model.adt;

import java.util.Map;

public interface Heap<V> {
    int allocate(V value);
    V lookup(int address);
    boolean containsKey(int address);
    void update(int address, V value);
    void setContent(Map<Integer, V> newContent);
    Map<Integer, V> getContent();
    void deallocate(int address);
}
