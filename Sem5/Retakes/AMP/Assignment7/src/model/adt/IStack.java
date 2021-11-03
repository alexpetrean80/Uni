package model.adt;

import java.util.List;

public interface IStack<T> {
    T pop();
    void push(T element);
    T top();
    boolean isEmpty();
    List<T> toList();
}
