package model.adt;

import java.util.Map;

public interface IHeap<V> {
    int allocate(V value);

    V lookup(int address);

    boolean containsKey(int address);

    void update(int address, V value);

    Map<Integer, V> getContent();

    void setContent(Map<Integer, V> newContent);

    void deallocate(int address);
}
