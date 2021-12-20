package model.adt;

import exception.MyException;

import java.util.ArrayList;
import java.util.Collections;

public class TLList<T> implements List<T> {

    private final java.util.List<T> list;

    public TLList(){
        this.list = Collections.synchronizedList(new ArrayList<T>());
    }

    @Override
    public void add(int index, T element){
        this.list.add(index, element);
    }

    @Override
    public void addToEnd(T element) {
        this.list.add(element);
    }

    public T get(int index){
        if (this.list.isEmpty())
            throw new MyException("The list is empty!");
        return this.list.get(index);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (T obj: this.list){
            str.append(obj.toString());
            str.append(",");
        }
        str.replace(str.length()-1, str.length(), "]");
        return str.toString();
    }
}
