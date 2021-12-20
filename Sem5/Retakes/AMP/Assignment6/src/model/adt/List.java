package model.adt;

public interface List<T> {
    void add(int index, T element);
    void addToEnd(T element);
    T get(int index);
    String toString();

}
