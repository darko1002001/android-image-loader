package com.dg.libs.android.imageloader.queue;

import java.util.Stack;


/**
 * @author darko.grozdanovski
 * @param <E>
 */
public class StackStrategy<E> implements DequeStrategy<E> {

    final Stack<E> stack = new Stack<E>();

    @Override
    public E get(int index) {
        return stack.get(index);
    }

    @Override
    public void remove(int index) {
        stack.remove(index);
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void push(E element) {
        stack.push(element);

    }

    @Override
    public E pop() {
        return stack.pop();
    }
}