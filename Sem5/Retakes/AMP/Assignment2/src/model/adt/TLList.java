package model.adt;

import exception.CustomException;

import java.util.ArrayList;
import java.util.List;

public class TLList<T> implements IList<T> {

    private final List<T> list;

    public TLList(){
        this.list = new ArrayList<T>();
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
            throw new CustomException("The list is empty!");
        return this.list.get(index);
    }

    public String toString(){
        var sb = new StringBuilder();
        sb.append("[");
        for (T obj: this.list){
            sb.append(obj.toString());
            if (!(obj.equals(this.list.get(this.list.size() - 1))))
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
