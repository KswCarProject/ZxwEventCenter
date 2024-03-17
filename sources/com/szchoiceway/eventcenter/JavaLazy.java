package com.szchoiceway.eventcenter;

import com.szchoiceway.eventcenter.function.Function0;

public class JavaLazy<T> {
    private Function0<T> initializationAction;
    private volatile T instance;

    public JavaLazy(Function0<T> function0) {
        this.initializationAction = function0;
    }

    public T get() {
        if (this.instance == null) {
            synchronized (this) {
                if (this.instance == null) {
                    this.instance = this.initializationAction.invoke();
                    this.initializationAction = null;
                }
            }
        }
        return this.instance;
    }

    public boolean equals(Object obj) {
        if (this.instance == null) {
            return false;
        }
        return this.instance.equals(obj);
    }

    public int hashCode() {
        if (this.instance == null) {
            return 0;
        }
        return this.instance.hashCode();
    }
}
