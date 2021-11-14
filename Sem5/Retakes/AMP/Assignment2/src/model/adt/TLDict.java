package model.adt;

import exception.CustomException;
import exception.UnknownKeyException;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class TLDict<K, V> implements IDictionary<K, V> {
    private final Map<K, V> map;

    public TLDict(){
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
        var value = this.map.get(key);
        if(value == null)
            throw new UnknownKeyException();
        return value;
    }

    @Override
    public void remove(K key){
        if (!(this.containsKey(key))){
            throw new CustomException("The key is not in the dictionary,");
        }
        this.map.remove(key);
    }

    @Override
    public String toString(){
        var sb = new StringBuilder();
        sb.append("{ ");
        for (K key: this.map.keySet() ){
            if (this.map.get(key) instanceof BufferedReader) {
                sb.append(key.toString());
                sb.append(",");
            }
            else {
                sb.append(key.toString());
                sb.append("->");
                sb.append(this.map.get(key).toString());
                sb.append(",");
            }
        }
        sb = sb.replace(sb.length()-1, sb.length(), "}");
        return sb.toString();
    }
}
