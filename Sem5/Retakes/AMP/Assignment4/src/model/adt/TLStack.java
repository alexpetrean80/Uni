package model.adt;

import exception.EmptyStackException;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class TLStack<T> implements IStack<T> {

    private final Deque<T> stack;

    public TLStack(Collection<T> initStack) {
        this.stack = new ArrayDeque<T>(initStack);
    }

    public TLStack() {
        this.stack = new ArrayDeque<>();
    }

    @Override
    public T pop() {
        if (this.stack.isEmpty())
            throw new EmptyStackException("The stack is empty.");
        return this.stack.pop();
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public T top() {
        if (this.stack.isEmpty())
            throw new EmptyStackException("The stack is empty.");
        return this.stack.peekFirst();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("(");
        for (T elem : this.stack) {
            output.append(elem.toString());
            if (!(elem.equals(this.stack.getLast())))
                output.append(" | ");
        }
        output.append(")");
        return output.toString();
    }

    @Override
    public IStack<T> deepCopy() {
        var s = new TLStack<T>();
        stack.forEach(s::push);
        return s;
    }
}
