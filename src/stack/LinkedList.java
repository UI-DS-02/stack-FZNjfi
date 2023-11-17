package stack;

public class LinkedList<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;


    static class Node<E> {
        private E data;
        private Node<E> next = null;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<E> getHead() {
        if (isEmpty()) return null;
        return head;
    }

    public Node<E> getTail() {
        if (isEmpty()) return null;
        return tail;
    }

    public int getSize() {
        return size;
    }

    public void addLast(E data) {
        Node<E> newest = new Node<>(data, null);
        if (isEmpty()) head = newest;
        else tail.setNext(newest);
        tail = newest;
        size++;
    }

    public void addFirst(E data) {
        if (head != null)
            head = new Node<>(data, head);
        else
            head = new Node<>(data, null);
        if (isEmpty()) tail = head;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            tail = null;
            return null;
        }
        E answer = head.getData();
        head = head.getNext();
        size--;
        return answer;
    }

    @Override
    public String toString() {
        Node<E> current = head;
        String string = "stack.LinkedList:";
        while (current.next != null) {
            string += current.data.toString() + ',';
            current = current.next;
        }
        return string;
    }

}
