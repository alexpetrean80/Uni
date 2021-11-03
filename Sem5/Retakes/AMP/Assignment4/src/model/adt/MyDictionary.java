package model.adt;

import exception.MyException;
import exception.UnknownKeyException;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements IDictionary<K, V> {
    private final Map<K, V> map;

    public MyDictionary(){
        this.map = new HashMap<K, V>();
    }

    @Override
    public boolean containsKey(K key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(V object) {
        return this.map.containsValue(object);
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public void update(K key, V value) { this.map.put(key, value);}

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public V lookUp(K key) {
        V value = this.map.get(key);
        if(value == null)
            throw new UnknownKeyException();
        return value;
    }

    @Override
    public Map<K, V> getContent(){
        return this.map;
    }

    @Override
    public void remove(K key){
        if (!(this.containsKey(key))){
            throw new MyException("The key is not in the dictionary,");
        }
        this.map.remove(key);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("{ ");
        for (K key: this.map.keySet() ){
            if (this.map.get(key) instanceof BufferedReader) {
                str.append(key.toString());
                str.append(",");
            }
            else {
                str.append(key.toString());
                str.append("->");
                str.append(this.map.get(key).toString());
                str.append(",");
            }
        }
        str = str.replace(str.length()-1, str.length(), "}");
        return str.toString();
    }
}
