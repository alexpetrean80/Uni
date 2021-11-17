package model.adt;

public interface IList<T> {
    void add(int index, T element);
    void addToEnd(T element);
    T get(int index);
    int length();
    String toString();

}
