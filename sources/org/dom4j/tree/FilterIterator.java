package org.dom4j.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class FilterIterator<T> implements Iterator<T> {
    private boolean first = true;
    private T next;
    protected Iterator<T> proxy;

    /* access modifiers changed from: protected */
    public abstract boolean matches(T t);

    public FilterIterator(Iterator<T> it) {
        this.proxy = it;
    }

    public boolean hasNext() {
        if (this.first) {
            this.next = findNext();
            this.first = false;
        }
        if (this.next != null) {
            return true;
        }
        return false;
    }

    public T next() throws NoSuchElementException {
        if (hasNext()) {
            T t = this.next;
            this.next = findNext();
            return t;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public T findNext() {
        if (this.proxy != null) {
            while (this.proxy.hasNext()) {
                T next2 = this.proxy.next();
                if (next2 != null && matches(next2)) {
                    return next2;
                }
            }
            this.proxy = null;
        }
        return null;
    }
}
