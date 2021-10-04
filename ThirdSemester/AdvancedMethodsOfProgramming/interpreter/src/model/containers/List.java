package model.containers;

import java.util.ArrayList;

public class List<T> implements model.interfaces.List<T> {

    ArrayList<T> list;

    public List() {
        list = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    public ArrayList<T> getInnerList() {
        return list;
    }
}
