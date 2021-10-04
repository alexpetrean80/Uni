package model.interfaces;

import java.util.ArrayList;

public interface List<T> {
    void add(T elem);

    boolean isEmpty();

    void clear();

    ArrayList<T> getInnerList();
}
