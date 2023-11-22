package stack;

import stack.Stack;

public class LinkedStack<E> implements Stack<E> {

    private LinkedList<E> list = new LinkedList<>();

    @Override
    public void push(E o) {
        list.addFirst(o);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E top() {
        return list.getHead().getData();
    }

    @Override
    public int size() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
