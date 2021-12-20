package model.adt;

public interface Stack<T> {
    T pop();
    void push(T element);
    T top();
    boolean isEmpty();
}
