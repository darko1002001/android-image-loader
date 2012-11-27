package com.dg.libs.android.imageloader.queue;

import java.util.EmptyStackException;
import java.util.Vector;


/**
 * @author darko.grozdanovski
 * @param <E>
 */
public class QueueStrategy<E> implements DequeStrategy<E> {

    final Vector<E> queue = new Vector<E>();

    @Override
    public E get(int index) {
        return queue.get(index);
    }

    @Override
    public void remove(int index) {
        queue.remove(index);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void push(E element) {
        queue.addElement(element);
    }

    @Override
    public E pop() {
        E obj;
        obj = peek();
        queue.removeElementAt(0);
        return obj;
    }

    public synchronized E peek() {
        int     len = size();
        if (len == 0) {
            throw new EmptyStackException();
        }
        return queue.elementAt(0);
    }
}