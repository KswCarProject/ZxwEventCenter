package org.dom4j.tree;

import java.util.Iterator;

public class SingleIterator<T> implements Iterator<T> {
    private boolean first = true;
    private T object;

    public SingleIterator(T t) {
        this.object = t;
    }

    public boolean hasNext() {
        return this.first;
    }

    public T next() {
        T t = this.object;
        this.object = null;
        this.first = false;
        return t;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported by this iterator");
    }
}
