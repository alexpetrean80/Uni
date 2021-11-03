package model.adt;

import exception.MyException;
import model.value.Value;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class MyHeap<V> implements IHeap<V>{
    private Map<Integer, V> heap;
    private AtomicInteger nextFreeLocation;

    public MyHeap(){
        this.heap = new HashMap<Integer, V>();
        this.nextFreeLocation = new AtomicInteger(0);
        }

    @Override
    public int allocate(V value) {
        int address;
        address = this.nextFreeLocation.incrementAndGet();

        this.heap.put(address, value);

        return address;
    }

    @Override
    public V lookup(int address) {
        V value = this.heap.get(address);
        if (value == null){
            throw new MyException("Undefined address.");
        }
        return value;
    }

    @Override
    public boolean containsKey(int address) {
        return this.heap.containsKey(address);
    }

    @Override
    public void update(int address, V value) {
        this.heap.put(address, value);
    }

    @Override
    public void deallocate(int address) {
        this.heap.remove(address);
    }

    @Override
    public void setContent(Map<Integer, V> newContent) {
        this.heap = newContent;
    }

    @Override
    public Map<Integer, V> getContent() {
        return this.heap;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append("{ ");
        for (Integer key: this.heap.keySet() ){
                str.append(key.toString());
                str.append("->");
                str.append(this.heap.get(key).toString());
                str.append(",");
        }
        str = str.replace(str.length()-1, str.length(), "}");
        return str.toString();
    }
}
