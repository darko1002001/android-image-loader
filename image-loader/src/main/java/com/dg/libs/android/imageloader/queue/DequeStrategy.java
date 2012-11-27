package com.dg.libs.android.imageloader.queue;

/**
 * @author darko.grozdanovski
 * @param <E>
 */
public interface DequeStrategy<E> {

    public E get(int index);

    public void remove(int index);

    public int size();

    public void push(E element);

    public E pop();
}