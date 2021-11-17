package model.adt;

public interface IStack<T> {
    T pop();
    void push(T element);
    T top();
    boolean isEmpty();

    IStack<T> deepCopy();
}
