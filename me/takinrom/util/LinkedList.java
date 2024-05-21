package me.takinrom.util;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private Node<E> backup = null;


    public void add(E value) {
        if (size == 0) {
            first = new Node<>(value);
            last = first;
        } else {
            last.next = new Node<>(value);
            last.next.prev = last;
            last = last.next;
        }
        size++;
    }

    public void concat(LinkedList<E> list) {
        if (size == 0) {
            first = list.getFirstNode();
            last = list.getLastNode();
            size = list.getSize();
            return;
        }
        size += list.getSize();
        last.next = list.getFirstNode();
        list.getFirstNode().prev = last;
        last = list.getLastNode();
    }

    public Node<E> getFirstNode() {
        return first;
    }

    public Node<E> getLastNode() {
        return last;
    }

    public E getFirst() {
        return first.value;
    }

    public E getLast() {
        return last.value;
    }

    public int getSize() {
        return size;
    }

    @Override
    public LinkedListIterator iterator() {
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<E> {
        private int iter;
        private Node<E> current;

        public LinkedListIterator() {
            current = first;
            iter = 0;
        }

        @Override
        public boolean hasNext() {
            return iter < size;
        }

        @Override
        public E next() {
            iter++;
            if (current == null) {
                current = backup.next;
            }
            E val = current.value;
            if (current.next == null) {
                backup = current;
            }
            current = current.next;
            return val;
        }
    }

    public static class Node<E> {
        public Node<E> prev = null;
        public Node<E> next = null;
        public E value;

        public Node(E value) {
            this.value = value;
        }

        public Node() {
            this(null);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "()";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        LinkedListIterator iter = this.iterator();
        int n = size - 1;
        for (int i = 0; i < n; i++) {
            builder.append(iter.next());
            builder.append(", ");
        }
        builder.append(iter.next());
        builder.append(")");

        return builder.toString();
    }
}
