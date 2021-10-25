package model.interfaces;

public interface Stack<T> {
    void push(T elem);
    T top();
    T pop();

    boolean isEmpty();
}
