package model.adt;

import exception.MyException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T> {

    private final List<T> list;

    public MyList(){
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
            throw new MyException("The list is empty!");
        return this.list.get(index);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (T obj: this.list){
            str.append(obj.toString());
            if (!(obj.equals(this.list.get(this.list.size() - 1))))
                str.append(",");
        }
        str.append("]");
        return str.toString();
    }
}
